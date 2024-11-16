package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.blocks.entity.GoldChalkBlockEntity;
import net.favouriteless.enchanted.common.items.EItems;
import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.favouriteless.enchanted.common.rites.RiteManager;
import net.favouriteless.enchanted.common.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Rite {

    private final ResourceLocation type;
    private final ServerLevel level;
    private final int tickPower;
    private final List<ItemStack> consumedItems;

    private BlockPos pos;

    protected UUID casterUUID;
    protected UUID targetUUID;
    protected int ticks = 0;

    private final Map<UUID, WeakReference<Entity>> entityCache = new HashMap<>();

    protected Rite(BaseRiteParams params) {
        this.type = params.type;
        this.level = params.level;
        this.pos = params.pos;
        this.casterUUID = params.caster;
        this.tickPower = params.tickPower;
        this.consumedItems = params.consumedItems;

        for(ItemStack stack : consumedItems) {
            if(stack.has(EDataComponents.ENTITY_REF.get())) {
                targetUUID = stack.get(EDataComponents.ENTITY_REF.get()).uuid();
                break;
            }
        }
    }

    /**
     * Called when a rite using this behaviour first starts executing. Rite will stop if this returns false.
     *
     * @param level         level the rite was cast in.
     * @param pos           position of the gold chalk for the rite was cast with.
     * @param caster        player who cast the rite. null if they couldn't be found (e.g. logged off).
     * @param targetUUID    the target of the rite. null if they couldn't be found (e.g. logged off).
     * @param consumedItems the items used to start the rite.
     */
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        return true;
    }

    /**
     * Called when a rite using this behaviour ticks. Will not be called if there was not enough power to tick. Rite
     * will stop if this returns false.
     *
     * @param level         level the rite was cast in.
     * @param pos           position of the gold chalk for the rite was cast with.
     * @param caster        the target of the rite. null if they couldn't be found (e.g. logged off).
     * @param consumedItems the items used to start the rite.
     */
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                             @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        return false;
    }

    /**
     * Called when a rite using this behaviour stops executing. Will NOT be called if the Rite was cancelled.
     *
     * @param level         level the rite was cast in.
     * @param pos           position of the gold chalk for the rite was cast with.
     * @param caster        player who cast the rite. null if they couldn't be found (e.g. logged off).
     * @param targetUUID    the target of the rite. null if they couldn't be found (e.g. logged off).
     * @param consumedItems the items used to start the rite.
     */
    protected void onStop(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable UUID targetUUID,
                          List<ItemStack> consumedItems) {
    }

    /**
     * Refund the items used to start this rite and detatch from chalk, onStop will NOT be called.
     */
    protected boolean cancel() {
        level.playSound(null, pos, SoundEvents.NOTE_BLOCK_SNARE.value(), SoundSource.MASTER, 1.0f, 1.0f);
        for(ItemStack stack : consumedItems) {
            ItemEntity entity = new ItemEntity(level, pos.getX()+0.5d, pos.getY()+0.5d, pos.getZ()+0.5d, stack);
            level.addFreshEntity(entity);
        }
        if(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk)
            chalk.detatch();
        return false;
    }

    protected void saveAdditional(CompoundTag tag, ServerLevel level) {}

    protected void loadAdditional(CompoundTag tag, ServerLevel level) {}

    protected void consumeItem(ItemEntity entity) {
        if(!entity.getItem().is(EItems.ATTUNED_STONE_CHARGED.get())) {
            consumedItems.add(entity.getItem());
            entity.discard();
        }
        else {
            entity.setItem(new ItemStack(EItems.ATTUNED_STONE.get(), entity.getItem().getCount()));
        }

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CHICKEN_EGG,
                SoundSource.MASTER, 1.0f, 1.0f);
        level.sendParticles(ParticleTypes.CLOUD, entity.getX(), entity.getY(), entity.getZ(), 1,
                0, 0, 0, 0);
    }

    protected @Nullable Entity findEntity(UUID uuid) {
        Entity out;

        if(entityCache.containsKey(uuid)) { // Cache our entities first since we're usually trying to grab the same one anyway
            out = entityCache.get(uuid).get();
            if(out != null)
                return out;
            entityCache.remove(uuid);
        }

        out = level.getServer().getPlayerList().getPlayer(uuid);
        if(out != null)
            return cacheAndReturn(uuid, out);

        for(ServerLevel dim : level.getServer().getAllLevels()) {
            out = dim.getEntity(uuid);
            if(out != null)
                return cacheAndReturn(uuid, out);
        }
        return null;
    }

    // ----------------------------------- NON-API IMPLEMENTATIONS BELOW THIS POINT -----------------------------------

    /**
     * Mods should not directly be calling tick.
     */
    public boolean tick() {
        ticks++;
        if(level.isLoaded(pos)) {
            if(tickPower > 0) {
                if(!(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk) || !chalk.tryConsumePower(tickPower))
                    return stop();
            }

            if(!onTick(level, pos, getCaster(), targetUUID, consumedItems))
                return stop();
        }
        return true;
    }

    public void start() {
        if(!onStart(level, pos, getCaster(), targetUUID, consumedItems)) {
            stop();
            RiteManager.removeRite(level, this);
        }
    }

    public CompoundTag save(ServerLevel level) {
        CompoundTag tag = new CompoundTag();
        tag.put("type", ResourceLocation.CODEC.encodeStart(NbtOps.INSTANCE, type).getOrThrow());
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        tag.putUUID("caster", casterUUID);
        if(targetUUID != null)
            tag.putUUID("target", targetUUID);
        ItemUtil.saveAllItems(tag, consumedItems, level.registryAccess());
        tag.putInt("ticks", ticks);
        saveAdditional(tag, level);
        return tag;
    }

    public void load(CompoundTag tag, ServerLevel level) {
        pos = new BlockPos(
                tag.getInt("x"),
                tag.getInt("y"),
                tag.getInt("z")
        );
        casterUUID = tag.getUUID("caster");
        if(tag.hasUUID("target"))
            targetUUID = tag.getUUID("target");
        ItemUtil.loadAllItems(tag, consumedItems, level.registryAccess());
        ticks = tag.getInt("ticks");
        loadAdditional(tag, level);
    }


    public boolean stop() {
        onStop(level, pos, getCaster(), targetUUID, consumedItems);

        if(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk)
            chalk.detatch();
        return false;
    }

    private @Nullable ServerPlayer getCaster() {
        return level.getServer().getPlayerList().getPlayer(casterUUID);
    }

    public BlockPos getPos() {
        return pos;
    }

    protected void randomParticles(ParticleOptions options) {
        level.sendParticles(options, pos.getX(), pos.getY(), pos.getZ(), 25, 1.5D, 1.5D, 1.5D, 0.0D);
    }

    private Entity cacheAndReturn(UUID uuid, Entity entity) {
        entityCache.put(uuid, new WeakReference<>(entity));
        return entity;
    }


    public record BaseRiteParams(ResourceLocation type, int tickPower, ServerLevel level, BlockPos pos,
                                 @Nullable UUID caster, List<ItemStack> consumedItems) { }

}

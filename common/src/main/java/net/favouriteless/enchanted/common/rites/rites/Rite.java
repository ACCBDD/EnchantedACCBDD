package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.blocks.entity.GoldChalkBlockEntity;
import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.favouriteless.enchanted.common.rites.RiteManager;
import net.favouriteless.enchanted.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public abstract class Rite {

    private final ResourceLocation type;
    private final ServerLevel level;
    private final int tickPower;

    private BlockPos pos;
    private List<ItemStack> consumedItems;

    protected UUID casterUUID;
    protected UUID targetUUID;
    protected int ticks = 0;

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
     * @param level level the rite was cast in.
     * @param pos position of the gold chalk for the rite was cast with.
     * @param caster player who cast the rite. null if they couldn't be found (e.g. logged off).
     * @param target the target of the rite. null if they couldn't be found (e.g. logged off).
     * @param consumedItems the items used to start the rite.
     */
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        return false;
    }

    /**
     * Called when a rite using this behaviour ticks. Will not be called if there was not enough power to tick. Rite
     * will stop if this returns false.
     *
     * @param level level the rite was cast in.
     * @param pos position of the gold chalk for the rite was cast with.
     * @param caster the target of the rite. null if they couldn't be found (e.g. logged off).
     * @param consumedItems the items used to start the rite.
     */
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                             @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        return false;
    }

    /**
     * Called when a rite using this behaviour stops executing. Will NOT be called if the Rite was cancelled.
     *
     * @param level level the rite was cast in.
     * @param pos position of the gold chalk for the rite was cast with.
     * @param caster player who cast the rite. null if they couldn't be found (e.g. logged off).
     * @param target the target of the rite. null if they couldn't be found (e.g. logged off).
     * @param consumedItems the items used to start the rite.
     */
    protected void onStop(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target,
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

    protected void saveAdditional(CompoundTag tag, Provider registries) {}

    protected void loadAdditional(CompoundTag tag, Provider registries) {}

    // ----------------------------------- NON-API IMPLEMENTATIONS BELOW THIS POINT -----------------------------------

    public boolean tick() {
        ticks++;
        if(level.isLoaded(pos)) {
            if(tickPower > 0) {
                if(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk) {
                    if(!chalk.tryConsumePower(tickPower)) {
                        return stop();
                    }
                }
            }

            if(!onTick(level, pos, getCaster(), getTarget(), consumedItems)) {
                return stop();
            }
        }
        return true;
    }

    public void start() {
        if(!onStart(level, pos, getCaster(), getTarget(), consumedItems)) {
            stop();
            RiteManager.removeRite(level, this);
        }
    }

    public CompoundTag save(Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.put("type", ResourceLocation.CODEC.encodeStart(NbtOps.INSTANCE, type).getOrThrow());
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        tag.putUUID("caster", casterUUID);
        if(targetUUID != null)
            tag.putUUID("target", targetUUID);
        ItemUtil.saveAllItems(tag, consumedItems, registries);
        tag.putInt("ticks", ticks);
        saveAdditional(tag, registries);
        return tag;
    }

    public void load(CompoundTag tag, Provider registries) {
        pos = new BlockPos(
                tag.getInt("x"),
                tag.getInt("y"),
                tag.getInt("z")
        );
        casterUUID = tag.getUUID("caster");
        if(tag.hasUUID("target"))
            targetUUID = tag.getUUID("target");
        ItemUtil.loadAllItems(tag, consumedItems, registries);
        ticks = tag.getInt("ticks");
        loadAdditional(tag, registries);
    }


    public boolean stop() {
        onStop(level, pos, getCaster(), getTarget(), consumedItems);

        if(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk)
            chalk.detatch();
        return false;
    }

    private @Nullable ServerPlayer getCaster() {
        return level.getServer().getPlayerList().getPlayer(casterUUID);
    }

    private @Nullable ServerPlayer getTarget() {
        return level.getServer().getPlayerList().getPlayer(targetUUID);
    }

    public BlockPos getPos() {
        return pos;
    }

    protected void randomParticles(ParticleOptions options) {
        level.sendParticles(options, pos.getX(), pos.getY(), pos.getZ(), 25, 1.5D, 1.5D, 1.5D, 0.0D);
    }

    public record BaseRiteParams(ResourceLocation type, int tickPower, ServerLevel level, BlockPos pos,
                                 @Nullable UUID caster, List<ItemStack> consumedItems) { }

}

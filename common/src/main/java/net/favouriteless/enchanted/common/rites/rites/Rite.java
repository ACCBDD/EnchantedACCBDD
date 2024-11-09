package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.blocks.entity.GoldChalkBlockEntity;
import net.favouriteless.enchanted.common.rites.RiteManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class Rite {

    private final ResourceLocation type;
    private final ServerLevel level;
    private final int tickPower;

    private BlockPos pos;
    private UUID caster;
    private UUID target;

    protected Rite(ResourceLocation type, int tickPower, ServerLevel level, BlockPos pos, UUID caster, UUID target) {
        this.type = type;
        this.level = level;
        this.pos = pos;
        this.caster = caster;
        this.target = target;
        this.tickPower = tickPower;
    }

    /**
     * Called when a rite using this behaviour first starts executing. Rite will stop if this returns false.
     *
     * @param level level the rite was cast in.
     * @param pos position of the gold chalk for the rite was cast with.
     * @param caster player who cast the rite. null if they couldn't be found (e.g. logged off).
     * @param target the target of the rite. null if they couldn't be found (e.g. logged off).
     */
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target) {
        return false;
    }

    /**
     * Called when a rite using this behaviour ticks. Will not be called if there was not enough power to tick. Rite
     * will stop if this returns false.
     *
     * @param level level the rite was cast in.
     * @param pos position of the gold chalk for the rite was cast with.
     * @param caster the target of the rite. null if they couldn't be found (e.g. logged off).
     */
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target) {
        return false;
    }

    /**
     * Called when a rite using this behaviour stops executing. Will NOT be called if the Rite was cancelled.
     *
     * @param level level the rite was cast in.
     * @param pos position of the gold chalk for the rite was cast with.
     * @param caster player who cast the rite. null if they couldn't be found (e.g. logged off).
     * @param caster the target of the rite. null if they couldn't be found (e.g. logged off).
     */
    protected void onStop(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target) {
    }

    protected void saveAdditional(CompoundTag tag, Provider registries) {}

    protected void loadAdditional(CompoundTag tag, Provider registries) {}

    // ----------------------------------- NON-API IMPLEMENTATIONS BELOW THIS POINT -----------------------------------

    public void tick() {
        if(level.isLoaded(pos)) {
            if(tickPower > 0) {
                if(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk) {
                    if(!chalk.tryConsumePower(tickPower)) {
                        stop();
                        return;
                    }
                }
            }

            if(!onTick(level, pos, getCaster(), getTarget()))
                stop();
        }
    }

    public void start() {
        if(!onStart(level, pos, getCaster(), getTarget()))
            stop();
    }

    public CompoundTag save(Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.put("type", ResourceLocation.CODEC.encodeStart(NbtOps.INSTANCE, type).getOrThrow());
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        tag.putUUID("caster", caster);
        if(target != null)
            tag.putUUID("target", target);
        saveAdditional(tag, registries);
        return tag;
    }

    public void load(CompoundTag tag, Provider registries) {
        pos = new BlockPos(
                tag.getInt("x"),
                tag.getInt("y"),
                tag.getInt("z")
        );
        caster = tag.getUUID("caster");
        if(tag.hasUUID("target"))
            target = tag.getUUID("target");
        loadAdditional(tag, registries);
    }


    public void stop() {
        onStop(level, pos, getCaster(), getTarget());

        if(level.getBlockEntity(pos) instanceof GoldChalkBlockEntity chalk)
            chalk.detatch();

        RiteManager.removeRite(level, this);
    }

    private @Nullable ServerPlayer getCaster() {
        return level.getServer().getPlayerList().getPlayer(caster);
    }

    private @Nullable ServerPlayer getTarget() {
        return level.getServer().getPlayerList().getPlayer(target);
    }

    public BlockPos getPos() {
        return pos;
    }

}

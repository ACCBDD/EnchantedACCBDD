package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

public class TransposeIronRite extends Rite {

    public static final double CIRCLE_RADIUS = 7.5D;
    private int progress = 0;

    public TransposeIronRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected boolean onTick(RiteParams params) {

        return true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, ServerLevel level) {
        tag.putInt("Progress", progress);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, ServerLevel level) {
        progress = tag.getInt("Progress");
    }

}

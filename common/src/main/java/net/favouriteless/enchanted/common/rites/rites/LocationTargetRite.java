package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

public abstract class LocationTargetRite extends Rite {

    protected ServerLevel targetLevel = null;
    protected BlockPos targetPos = null;

    protected LocationTargetRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, ServerLevel level) {
        tag.putInt("xT", targetPos.getX());
        tag.putInt("yT", targetPos.getY());
        tag.putInt("zT", targetPos.getZ());
        tag.putString("targetLevel", targetLevel.dimension().location().toString());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, ServerLevel level) {
        targetPos = new BlockPos(
                tag.getInt("xT"),
                tag.getInt("yT"),
                tag.getInt("zT")
        );
        targetLevel = level.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(tag.getString("targetLevel"))));
    }


}

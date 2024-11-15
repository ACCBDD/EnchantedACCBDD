package net.favouriteless.enchanted.common.rites;

import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public abstract class TransposeEntityRite extends Rite {

    protected ServerLevel targetLevel = null;
    protected BlockPos targetPos = null;

    public TransposeEntityRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        Entity transposee = getTransposee(level, pos, caster, targetUUID, consumedItems);
        if(transposee == null)
            return cancel();

        findDestination(level, pos, consumedItems, targetUUID, transposee);
        if(targetLevel == null || targetPos == null)
            return cancel();

        portalParticles((ServerLevel)transposee.level(), transposee.blockPosition());
        portalParticles(targetLevel, targetPos);

        transposee.level().playSound(null, transposee.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1, 1);
        level.playSound(null, targetPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1, 1);

        Vec3 destination = targetPos.getCenter().add(0, 0.01d, 0);
        if(targetLevel != transposee.level())
            transposee.changeDimension(new DimensionTransition(targetLevel, destination, Vec3.ZERO, 0, 0, DimensionTransition.DO_NOTHING));
        else
            transposee.teleportTo(destination.x, destination.y, destination.z);

        return false;
    }

    protected abstract Entity getTransposee(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                                            @Nullable UUID targetUUID, List<ItemStack> consumedItems);

    protected abstract void findDestination(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems,
                                            @Nullable UUID targetUUID, Entity transposee);

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

    protected void portalParticles(ServerLevel level, BlockPos pos) {
        for(int i = 0; i < 25; i++) {
            double dx = pos.getX() + (Math.random() * 1.5D);
            double dy = pos.getY() + (Math.random() * 2.0D);
            double dz = pos.getZ() + (Math.random() * 1.5D);
            level.sendParticles(ParticleTypes.PORTAL, dx, dy, dz, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

}

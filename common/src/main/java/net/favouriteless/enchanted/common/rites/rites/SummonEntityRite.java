package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.rites.TransposeEntityRite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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

public class SummonEntityRite extends TransposeEntityRite {

    public SummonEntityRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected Entity getTransposee(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                                   @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        return findEntity(targetUUID);
    }

    @Override
    protected void findDestination(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems,
                                   @Nullable UUID targetUUID, Entity transposee) {
        targetLevel = level;
        targetPos = pos;
    }

}

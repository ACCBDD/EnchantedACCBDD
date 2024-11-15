package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
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

public class WaystoneTransposeCasterRite extends TransposeEntityRite {

    public WaystoneTransposeCasterRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected Entity getTransposee(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        return caster;
    }

    @Override
    protected void findDestination(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems,
                                   @Nullable UUID targetUUID, Entity transposee) {
        for(ItemStack item : consumedItems) {
            if(item.has(EDataComponents.LEVEL_KEY.get()) && item.has(EDataComponents.BLOCK_POS.get())) {
                targetLevel = level.getServer().getLevel(item.get(EDataComponents.LEVEL_KEY.get()));
                targetPos = item.get(EDataComponents.BLOCK_POS.get());
                return;
            }
        }
    }

}

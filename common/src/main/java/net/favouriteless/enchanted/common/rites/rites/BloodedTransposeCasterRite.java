package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.rites.TransposeEntityRite;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class BloodedTransposeCasterRite extends TransposeEntityRite {

    public BloodedTransposeCasterRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected Entity getTransposee(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                                   @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        return caster;
    }

    @Override
    protected void findDestination(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems,
                                   @Nullable UUID targetUUID, Entity transposee) {
        Entity target = findEntity(targetUUID);
        if(target != null) {
            targetLevel = (ServerLevel)target.level();
            targetPos = target.blockPosition();
        }
    }


}

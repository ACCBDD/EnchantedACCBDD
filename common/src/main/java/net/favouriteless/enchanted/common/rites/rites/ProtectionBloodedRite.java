package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ProtectionBloodedRite extends ProtectionRite {

    public ProtectionBloodedRite(BaseRiteParams params, int radius, int duration, boolean blocking) {
        super(params, radius, duration, blocking);
    }

    @Override
    protected void findTargetLocation(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems) {
        if(targetUUID != null) {
            Entity target = findEntity(targetUUID);
            if(target == null)
                return;

            targetLevel = (ServerLevel)target.level();
            targetPos = target.blockPosition();
        }
    }

}

package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class WaystoneProtectionRite extends ProtectionRite {

    public WaystoneProtectionRite(BaseRiteParams params, int radius, int duration, boolean blocking) {
        super(params, radius, duration, blocking);
    }

    protected void findTargetLocation(ServerLevel level, BlockPos pos) {
        AABB bounds = new AABB(
                pos.getBottomCenter().subtract(5, 0, 5),
                pos.getBottomCenter().add(5, 1, 5)
        );
        List<ItemEntity> entities = level.getEntitiesOfClass(ItemEntity.class, bounds);

        if(targetUUID != null) {
            Entity target = findEntity(targetUUID);
            if(target == null)
                return;

            targetLevel = (ServerLevel)target.level();
            targetPos = target.blockPosition();
            return;
        }

        for(ItemEntity itemEntity : entities) {
            ItemStack item = itemEntity.getItem();
            if(item.has(EDataComponents.LEVEL_KEY.get()) && item.has(EDataComponents.BLOCK_POS.get())) {
                targetLevel = level.getServer().getLevel(item.get(EDataComponents.LEVEL_KEY.get()));
                targetPos = item.get(EDataComponents.BLOCK_POS.get());
                consumeItem(itemEntity);
                return;
            }
        }
    }

}

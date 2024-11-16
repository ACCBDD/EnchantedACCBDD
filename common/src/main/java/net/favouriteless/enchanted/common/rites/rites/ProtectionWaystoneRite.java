package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ProtectionWaystoneRite extends ProtectionRite {

    public ProtectionWaystoneRite(BaseRiteParams params, int radius, int duration, boolean blocking) {
        super(params, radius, duration, blocking);
    }

    @Override
    protected void findTargetLocation(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems) {
        for(ItemStack item : consumedItems) {
            if(item.has(EDataComponents.LEVEL_KEY.get()) && item.has(EDataComponents.BLOCK_POS.get())) {
                targetLevel = level.getServer().getLevel(item.get(EDataComponents.LEVEL_KEY.get()));
                targetPos = item.get(EDataComponents.BLOCK_POS.get());
                return;
            }
        }
    }

}

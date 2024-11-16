package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SkyWrathWaystoneRite extends SkyWrathRite {

    public SkyWrathWaystoneRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected void findLocation(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems, @Nullable UUID targetUUID) {
        for(ItemStack item : consumedItems) {
            if(item.has(EDataComponents.LEVEL_KEY.get()) && item.has(EDataComponents.BLOCK_POS.get())) {
                targetLevel = level.getServer().getLevel(item.get(EDataComponents.LEVEL_KEY.get()));
                targetPos = item.get(EDataComponents.BLOCK_POS.get());
                return;
            }
        }
    }

}

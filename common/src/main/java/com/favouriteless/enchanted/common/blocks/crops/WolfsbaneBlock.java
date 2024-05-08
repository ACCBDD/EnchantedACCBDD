package com.favouriteless.enchanted.common.blocks.crops;

import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class WolfsbaneBlock extends CropsBlockAgeFive {

    public WolfsbaneBlock() {
        super(Properties.copy(Blocks.WHEAT));
    }

    protected ItemLike getBaseSeedId() {
        return EnchantedItems.WOLFSBANE_SEEDS.get();
    }

}

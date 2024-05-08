package com.favouriteless.enchanted.common.blocks.crops;

import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class BelladonnaBlock extends CropsBlockAgeFive {

    public BelladonnaBlock() {
        super(Properties.copy(Blocks.WHEAT));
    }

    protected ItemLike getBaseSeedId() {
        return EnchantedItems.BELLADONNA_SEEDS.get();
    }

}

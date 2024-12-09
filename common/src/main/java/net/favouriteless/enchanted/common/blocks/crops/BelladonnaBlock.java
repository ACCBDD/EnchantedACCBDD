package net.favouriteless.enchanted.common.blocks.crops;

import net.favouriteless.enchanted.common.init.registry.EItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class BelladonnaBlock extends CropsBlockAgeFive {

    public BelladonnaBlock() {
        super(Properties.copy(Blocks.WHEAT));
    }

    protected ItemLike getBaseSeedId() {
        return EItems.BELLADONNA_SEEDS.get();
    }

}

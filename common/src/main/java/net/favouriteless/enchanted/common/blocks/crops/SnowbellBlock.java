package net.favouriteless.enchanted.common.blocks.crops;

import net.favouriteless.enchanted.common.items.EItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class SnowbellBlock extends CropsBlockAgeFive {

    public SnowbellBlock() {
        super(Properties.copy(Blocks.WHEAT));
    }

    protected ItemLike getBaseSeedId() {
        return EItems.SNOWBELL_SEEDS.get();
    }

}

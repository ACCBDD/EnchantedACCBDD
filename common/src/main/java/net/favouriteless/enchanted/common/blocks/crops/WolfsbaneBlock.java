package net.favouriteless.enchanted.common.blocks.crops;

import net.favouriteless.enchanted.common.init.registry.EItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class WolfsbaneBlock extends CropsBlockAgeFive {

    public WolfsbaneBlock() {
        super(Properties.copy(Blocks.WHEAT));
    }

    protected ItemLike getBaseSeedId() {
        return EItems.WOLFSBANE_SEEDS.get();
    }

}
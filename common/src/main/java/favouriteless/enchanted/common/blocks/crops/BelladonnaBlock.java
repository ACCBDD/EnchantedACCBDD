package favouriteless.enchanted.common.blocks.crops;

import favouriteless.enchanted.common.items.EItems;
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

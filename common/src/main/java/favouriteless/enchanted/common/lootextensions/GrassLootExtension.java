package favouriteless.enchanted.common.lootextensions;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.api.LootExtension;
import favouriteless.enchanted.common.CommonConfig;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class GrassLootExtension extends LootExtension {

    public GrassLootExtension() {
        super(Enchanted.id("extensions/blocks/grass_seeds"));
        addType(Blocks.GRASS_BLOCK);
        addType(Blocks.TALL_GRASS);
    }

    @Override
    public boolean test(LootContext context) {
        return !(CommonConfig.HOE_ONLY_SEEDS.get() && !(context.getParam(LootContextParams.TOOL).getItem() instanceof HoeItem));
    }

}

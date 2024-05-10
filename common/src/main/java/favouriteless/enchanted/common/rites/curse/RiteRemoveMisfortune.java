package favouriteless.enchanted.common.rites.curse;

import favouriteless.enchanted.api.rites.AbstractRemoveCurseRite;
import favouriteless.enchanted.common.init.registry.CurseTypes;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.rites.CirclePart;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Items;

import java.util.UUID;

public class RiteRemoveMisfortune extends AbstractRemoveCurseRite {

    public RiteRemoveMisfortune(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, 2000, 0, CurseTypes.MISFORTUNE); // Power, power per tick, curse type
        CIRCLES_REQUIRED.put(CirclePart.SMALL, EnchantedBlocks.RITUAL_CHALK.get());
        ITEMS_REQUIRED.put(EnchantedItems.TAGLOCK_FILLED.get(), 1);
        ITEMS_REQUIRED.put(EnchantedItems.BREATH_OF_THE_GODDESS.get(), 1);
        ITEMS_REQUIRED.put(Items.SPIDER_EYE, 1);
        ITEMS_REQUIRED.put(Items.GUNPOWDER, 1);
        ITEMS_REQUIRED.put(EnchantedItems.BREW_OF_LOVE.get(), 1);
    }

}

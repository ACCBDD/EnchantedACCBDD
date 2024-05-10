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

public class RiteRemoveSinking extends AbstractRemoveCurseRite {

    public RiteRemoveSinking(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, 2000, 0, CurseTypes.SINKING); // Power, power per tick, curse type
        CIRCLES_REQUIRED.put(CirclePart.SMALL, EnchantedBlocks.RITUAL_CHALK.get());
        ITEMS_REQUIRED.put(EnchantedItems.TAGLOCK_FILLED.get(), 1);
        ITEMS_REQUIRED.put(EnchantedItems.BREATH_OF_THE_GODDESS.get(), 1);
        ITEMS_REQUIRED.put(Items.BONE_MEAL, 1);
        ITEMS_REQUIRED.put(Items.NETHER_WART, 1);
        ITEMS_REQUIRED.put(EnchantedItems.BREW_OF_THE_DEPTHS.get(), 1);
    }


}

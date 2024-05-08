package favouriteless.enchanted.common.rites.curse;

import favouriteless.enchanted.api.rites.AbstractCurseRite;
import favouriteless.enchanted.common.init.registry.CurseTypes;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.rites.CirclePart;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Items;

import java.util.UUID;

public class RiteCurseOverheating extends AbstractCurseRite {

    public RiteCurseOverheating(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, 2000, CurseTypes.OVERHEATING); // Power, curse type
        CIRCLES_REQUIRED.put(CirclePart.MEDIUM, EnchantedBlocks.CHALK_RED.get());
        ITEMS_REQUIRED.put(EnchantedItems.TAGLOCK_FILLED.get(), 1);
        ITEMS_REQUIRED.put(EnchantedItems.EXHALE_OF_THE_HORNED_ONE.get(), 1);
        ITEMS_REQUIRED.put(EnchantedItems.DEMONIC_BLOOD.get(), 1);
        ITEMS_REQUIRED.put(EnchantedItems.BREW_OF_THE_GROTESQUE.get(), 1);
        ITEMS_REQUIRED.put(Items.BLAZE_ROD, 1);
    }

}

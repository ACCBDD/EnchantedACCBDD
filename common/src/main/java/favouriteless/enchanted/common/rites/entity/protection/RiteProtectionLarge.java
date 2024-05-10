package favouriteless.enchanted.common.rites.entity.protection;

import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.rites.CirclePart;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Items;

import java.util.UUID;

public class RiteProtectionLarge extends RiteProtection {

    public RiteProtectionLarge(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, 1000, 6, 6, EnchantedBlocks.PROTECTION_BARRIER.get()); // Power, power per tick, radius
        CIRCLES_REQUIRED.put(CirclePart.MEDIUM, EnchantedBlocks.RITUAL_CHALK.get());
        ITEMS_REQUIRED.put(Items.OBSIDIAN, 1);
        ITEMS_REQUIRED.put(Items.GLOWSTONE_DUST, 1);
    }

}

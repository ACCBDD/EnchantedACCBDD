package favouriteless.enchanted.common.rites.processing;

import favouriteless.enchanted.api.rites.AbstractCreateItemRite;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.rites.CirclePart;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.UUID;

public class RiteChargingStone extends AbstractCreateItemRite {

    public RiteChargingStone(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, 2000, SoundEvents.ZOMBIE_VILLAGER_CURE, new ItemStack(EnchantedItems.ATTUNED_STONE_CHARGED.get(), 1)); // Power, power per tick
        CIRCLES_REQUIRED.put(CirclePart.SMALL, EnchantedBlocks.RITUAL_CHALK.get());
        CIRCLES_REQUIRED.put(CirclePart.MEDIUM, EnchantedBlocks.RITUAL_CHALK.get());
        ITEMS_REQUIRED.put(EnchantedItems.ATTUNED_STONE.get(), 1);
        ITEMS_REQUIRED.put(Items.GLOWSTONE_DUST, 1);
        ITEMS_REQUIRED.put(Items.REDSTONE, 1);
        ITEMS_REQUIRED.put(EnchantedItems.WOOD_ASH.get(), 1);
        ITEMS_REQUIRED.put(EnchantedItems.QUICKLIME.get(), 1);
    }

}

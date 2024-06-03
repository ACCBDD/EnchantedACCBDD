package favouriteless.enchanted.common.poppet;

import favouriteless.enchanted.common.init.EnchantedTags.Items;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.items.poppets.AbstractDeathPoppetItem;
import favouriteless.enchanted.common.poppet.PoppetShelfSavedData.PoppetEntry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Queue;

public class PoppetEvents {

	/**
	 * @return True if event should be cancelled.
	 */
	public static boolean onLivingEntityHurt(LivingEntity entity, float amount, DamageSource source) {
		if(entity instanceof Player player) {
			if(amount >= player.getHealth()) { // Player would be killed by damage
				Queue<ItemStack> poppetQueue = PoppetHelper.getPoppetQueue(player,
						item -> item instanceof AbstractDeathPoppetItem poppet && poppet.protectsAgainst(source));
				boolean cancel = PoppetHelper.tryUseDeathPoppetQueue(poppetQueue, player);

				if(!cancel) {
					Queue<PoppetEntry> poppetEntryQueue = PoppetHelper.getPoppetQueue(PoppetShelfManager.getEntriesFor(player),
							entry -> entry.item().getItem() instanceof AbstractDeathPoppetItem poppet && poppet.protectsAgainst(source));
					cancel = PoppetHelper.tryUseDeathPoppetEntryQueue(poppetEntryQueue, player);
				}

				return cancel;
			}
		}
		return false;
	}

	public static void onPlayerItemBreak(Player player, ItemStack item, InteractionHand hand) {
		if(item.is(Items.TOOL_POPPET_WHITELIST) && !item.is(Items.TOOL_POPPET_BLACKLIST)) {
			Queue<ItemStack> poppetQueue = PoppetHelper.getPoppetQueue(player, EnchantedItems::isToolPoppet);
			boolean canceled = PoppetHelper.tryUseItemProtectionPoppetQueue(poppetQueue, player, item);

			if(!canceled) {
				Queue<PoppetEntry> poppetEntryQueue = PoppetHelper.getPoppetQueue(PoppetShelfManager.getEntriesFor(player), entry -> EnchantedItems.isToolPoppet(entry.item().getItem()));
				canceled = PoppetHelper.tryUseItemProtectionPoppetEntryQueue(poppetEntryQueue, player, item);
			}

			if(canceled)
				player.setItemInHand(hand, item);
		}
	}

	public static void onLivingEntityBreak(LivingEntity entity, EquipmentSlot slot) {
		if(entity instanceof Player player) {
			ItemStack item = entity.getItemBySlot(slot).copy();

			if(item.is(Items.ARMOR_POPPET_WHITELIST) && !item.is(Items.ARMOR_POPPET_BLACKLIST)) {
				Queue<ItemStack> poppetQueue = PoppetHelper.getPoppetQueue(player, EnchantedItems::isArmourPoppet);
				boolean canceled = PoppetHelper.tryUseItemProtectionPoppetQueue(poppetQueue, player, item);

				if(!canceled) {
					Queue<PoppetEntry> poppetEntryQueue = PoppetHelper.getPoppetQueue(PoppetShelfManager.getEntriesFor(player), entry -> EnchantedItems.isArmourPoppet(entry.item().getItem()));
					canceled = PoppetHelper.tryUseItemProtectionPoppetEntryQueue(poppetEntryQueue, player, item);
				}

				if(canceled)
					player.setItemSlot(slot, item);
			}
		}
	}

}

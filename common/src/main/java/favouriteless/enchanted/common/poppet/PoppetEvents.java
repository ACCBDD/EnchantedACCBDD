package favouriteless.enchanted.common.poppet;

import favouriteless.enchanted.common.init.EnchantedTags.Items;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.items.poppets.AbstractDeathPoppetItem;
import favouriteless.enchanted.common.items.poppets.AbstractPoppetItem;
import favouriteless.enchanted.common.items.poppets.ItemProtectionPoppetItem;
import favouriteless.enchanted.common.poppet.PoppetShelfSavedData.PoppetEntry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PoppetEvents {

	/**
	 * @return True if event should be cancelled.
	 */
	public static boolean onLivingEntityHurt(LivingEntity entity, float amount, DamageSource source) {
		if(entity instanceof Player player) {
			if(amount >= player.getHealth()) { // Player would be killed by damage
				Queue<ItemStack> poppetQueue = new PriorityQueue<>(new PoppetComparator());
				for(ItemStack itemStack : player.getInventory().items)
					if(itemStack.getItem() instanceof AbstractDeathPoppetItem)
						if(((AbstractDeathPoppetItem)itemStack.getItem()).protectsAgainst(source))
							poppetQueue.add(itemStack);

				boolean cancel = PoppetHelper.tryUseDeathPoppetQueue(poppetQueue, player);
				if(!cancel) {
					Queue<PoppetEntry> poppetEntryQueue = new PriorityQueue<>(new PoppetEntryComparator());
					for(PoppetEntry entry : PoppetShelfManager.getEntriesFor(player))
						if(entry.item().getItem() instanceof AbstractDeathPoppetItem)
							if(((AbstractDeathPoppetItem)entry.item().getItem()).protectsAgainst(source))
								poppetEntryQueue.add(entry);
					cancel = PoppetHelper.tryUseDeathPoppetEntryQueue(poppetEntryQueue, player);
				}

				return cancel;
			}
		}
		return false;
	}

	public static void onPlayerItemBreak(Player player, ItemStack item, InteractionHand hand) {
		if(item.is(Items.TOOL_POPPET_WHITELIST) && !item.is(Items.TOOL_POPPET_BLACKLIST)) {
			Queue<ItemStack> poppetQueue = new PriorityQueue<>(new PoppetComparator());
			for(ItemStack itemStack : player.getInventory().items)
				if(EnchantedItems.isToolPoppet(itemStack.getItem()))
					poppetQueue.add(itemStack);

			boolean canceled = PoppetHelper.tryUseItemProtectionPoppetQueue(poppetQueue, player, item);
			if(!canceled) {
				Queue<PoppetEntry> poppetEntryQueue = new PriorityQueue<>(new PoppetEntryComparator());
				for(PoppetEntry entry : PoppetShelfManager.getEntriesFor(player))
					if(EnchantedItems.isToolPoppet(entry.item().getItem()))
						poppetEntryQueue.add(entry);
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
				Queue<ItemStack> poppetQueue = new PriorityQueue<>(new PoppetComparator());
				for(ItemStack itemStack : player.getInventory().items)
					if(itemStack.getItem() instanceof ItemProtectionPoppetItem)
						if(EnchantedItems.isArmourPoppet(itemStack.getItem()))
							poppetQueue.add(itemStack);

				boolean canceled = PoppetHelper.tryUseItemProtectionPoppetQueue(poppetQueue, player, item);
				if(!canceled) {
					Queue<PoppetEntry> poppetEntryQueue = new PriorityQueue<>(new PoppetEntryComparator());
					for(PoppetEntry entry : PoppetShelfManager.getEntriesFor(player))
						if(EnchantedItems.isArmourPoppet(entry.item().getItem()))
							poppetEntryQueue.add(entry);
					canceled = PoppetHelper.tryUseItemProtectionPoppetEntryQueue(poppetEntryQueue, player, item);
				}

				if(canceled)
					player.setItemSlot(slot, item);
			}
		}
	}

	private static class PoppetComparator implements Comparator<ItemStack> {
		@Override
		public int compare(ItemStack o1, ItemStack o2) {
			if(!(o1.getItem() instanceof AbstractPoppetItem) || !(o1.getItem() instanceof AbstractPoppetItem))
				throw new IllegalStateException("Non-poppet item inside the poppet use queue");
			return Math.round(Math.signum(((AbstractPoppetItem)o1.getItem()).failRate - ((AbstractPoppetItem)o2.getItem()).failRate));
		}
	}

	private static class PoppetEntryComparator implements Comparator<PoppetEntry> {
		@Override
		public int compare(PoppetEntry o1, PoppetEntry o2) {
			return Math.round(Math.signum(((AbstractPoppetItem)o1.item().getItem()).failRate - ((AbstractPoppetItem)o2.item().getItem()).failRate));
		}
	}

}

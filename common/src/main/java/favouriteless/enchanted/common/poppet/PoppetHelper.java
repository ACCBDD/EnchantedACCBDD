package favouriteless.enchanted.common.poppet;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.items.poppets.AbstractDeathPoppetItem;
import favouriteless.enchanted.common.items.poppets.AbstractPoppetItem;
import favouriteless.enchanted.common.items.poppets.ItemProtectionPoppetItem;
import favouriteless.enchanted.common.network.packets.EnchantedPoppetAnimationPacket;
import favouriteless.enchanted.common.poppet.PoppetShelfSavedData.PoppetEntry;
import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.function.Predicate;

public class PoppetHelper {

	/**
	 * Get a queue of Poppets in a {@link Player}'s inventory, sorted by failure rate.
	 *
	 * @param player The {@link Player} to check.
	 * @param validPoppet A {@link Predicate} to filter the poppets found.
	 *
	 * @return A {@link Queue} of {@link ItemStack}s
	 */
	public static Queue<ItemStack> getPoppetQueue(Player player, Predicate<AbstractPoppetItem> validPoppet) {
		Queue<ItemStack> poppetQueue = new PriorityQueue<>(new PoppetComparator());
		for(ItemStack stack : player.getInventory().items) {
			if(stack.getItem() instanceof AbstractPoppetItem poppet && validPoppet.test(poppet))
				poppetQueue.add(stack);
		}
		return poppetQueue;
	}

	/**
	 * Get a queue of {@link PoppetEntry}s from a Poppet Shelf, sorted by failure rate.
	 *
	 * @param entries The {@link List} to check.
	 * @param validPoppet A {@link Predicate} to filter the poppets found.
	 *
	 * @return A {@link Queue} of {@link ItemStack}s
	 */
	public static Queue<PoppetEntry> getPoppetQueue(List<PoppetEntry> entries, Predicate<PoppetEntry> validPoppet) {
		Queue<PoppetEntry> poppetQueue = new PriorityQueue<>(new PoppetEntryComparator());
		for(PoppetEntry entry : entries) {
			if(validPoppet.test(entry))
				poppetQueue.add(entry);
		}
		return poppetQueue;
	}

	/**
	 * @return true if item is a bound {@link AbstractPoppetItem}
	 */
	public static boolean isBound(ItemStack item) {
		if(item.hasTag())
			return item.getItem() instanceof AbstractPoppetItem && item.getTag().hasUUID("boundPlayer");
		return false;
	}

	/**
	 * Check if a poppet "belongs" to a player.
	 *
	 * @param item The {@link ItemStack} to check.
	 * @param player The {@link Player} to check for.
	 *
	 * @return true if item is bound to player.
	 */
	public static boolean belongsTo(ItemStack item, Player player) {
		return belongsTo(item, player.getUUID());
	}

	/**
	 * Check if a poppet "belongs" to a UUID.
	 *
	 * @param item The {@link ItemStack} to check.
	 * @param uuid The {@link UUID} to check for.
	 *
	 * @return true if item is bound to uuid.
	 */
	public static boolean belongsTo(ItemStack item, UUID uuid) {
		if(item.hasTag() && item.getItem() instanceof AbstractPoppetItem) {
			CompoundTag tag = item.getTag();
			if(tag.hasUUID("boundPlayer"))
				return tag.getUUID("boundPlayer").equals(uuid);
		}
		return false;
	}


	/**
	 * @param item The {@link ItemStack} to check.
	 * @param level An instance of {@link ServerLevel} to grab the player list from.
	 *
	 * @return The player item is bound to.
	 */
	public static Player getBoundPlayer(ItemStack item, ServerLevel level) {
		if(isBound(item))
			return level.getServer().getPlayerList().getPlayer(item.getTag().getUUID("boundPlayer"));
		return null;
	}

	/**
	 * Get the name of the player a poppet is bound to.
	 *
	 * @param item The {@link ItemStack} to check.
	 *
	 * @return The name of the bound player, or "None" if no player was found.
	 */
	public static String getBoundName(ItemStack item) {
		if(isBound(item))
			return item.getTag().getString("boundName");
		return "None";
	}

	public static void bind(ItemStack item, Player player) {
		if(item.getItem() instanceof AbstractPoppetItem) {
			CompoundTag tag = item.getOrCreateTag();
			tag.putUUID("boundPlayer", player.getUUID());
			tag.putString("boundName", player.getDisplayName().getString());
			item.setTag(tag);
		}
	}

	public static void unbind(ItemStack item) {
		if(item.getItem() instanceof AbstractPoppetItem) {
			if(item.hasTag()) {
				CompoundTag tag = item.getTag();
				tag.remove("boundPlayer");
				tag.remove("boundName");
				item.setTag(tag);
			}
		}
	}

	public static PoppetResult tryUseDeathPoppet(Player player, ItemStack poppetStack, ServerLevel level, String shelfIdentifier) {
		if(poppetStack.getItem() instanceof AbstractDeathPoppetItem poppet) {
			if(PoppetHelper.belongsTo(poppetStack, player)) {
				if(poppet.canProtect(player)) {
					if(Enchanted.RANDOM.nextFloat() > poppet.getFailRate()) {
						poppet.protect(player);
						level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 0.5F, 1.0F);
						return tryDamagePoppet(poppetStack, level, shelfIdentifier) ? PoppetResult.SUCCESS_BREAK : PoppetResult.SUCCESS;
					}
					return PoppetResult.FAIL;
				}
			}
		}
		return PoppetResult.PASS;
	}

	public static boolean tryUseDeathPoppetQueue(Queue<ItemStack> queue, Player player) {
		while(!queue.isEmpty()) {
			ItemStack poppetItem = queue.remove();
			if(handleTryUseDeathPoppet(player, poppetItem, null))
				return true;
		}
		return false;
	}

	public static boolean tryUseDeathPoppetEntryQueue(Queue<PoppetEntry> queue, Player player) {
		while(!queue.isEmpty()) {
			PoppetEntry entry = queue.remove();
			if(handleTryUseDeathPoppet(player, entry.item(), entry.shelfIdentifier()))
				return true;
		}
		return false;
	}

	public static boolean handleTryUseDeathPoppet(Player player, ItemStack item, String shelfIdentifier) {
		ItemStack poppetItemOriginal = item.copy();
		PoppetResult result = PoppetHelper.tryUseDeathPoppet(player, item, (ServerLevel)player.level(), shelfIdentifier);
		return handlePoppetResult(result, poppetItemOriginal, player);
	}

	public static PoppetResult tryUseItemProtectionPoppet(Player player, ItemStack poppetStack, ItemStack toolStack, ServerLevel level, String shelfIdentifier) {
		if(poppetStack.getItem() instanceof ItemProtectionPoppetItem poppet) {
			if(PoppetHelper.belongsTo(poppetStack, player)) {
				if(Enchanted.RANDOM.nextFloat() > poppet.getFailRate()) {
					poppet.protect(toolStack);
					return tryDamagePoppet(poppetStack, level, shelfIdentifier) ? PoppetResult.SUCCESS_BREAK : PoppetResult.SUCCESS;
				}
				return PoppetResult.FAIL;
			}
		}
		return PoppetResult.PASS;
	}

	public static boolean tryUseItemProtectionPoppetQueue(Queue<ItemStack> queue, Player player, ItemStack toolStack) {
		while(!queue.isEmpty()) {
			ItemStack poppetItem = queue.remove();
			if(handleTryUseItemProtectionPoppet(player, poppetItem, toolStack, null))
				return true;
		}
		return false;
	}

	public static boolean tryUseItemProtectionPoppetEntryQueue(Queue<PoppetEntry> queue, Player player, ItemStack toolStack) {
		while(!queue.isEmpty()) {
			PoppetEntry entry = queue.remove();
			if(handleTryUseItemProtectionPoppet(player, entry.item(), toolStack, entry.shelfIdentifier()))
				return true;
		}
		return false;
	}

	public static boolean handleTryUseItemProtectionPoppet(Player player, ItemStack poppetStack, ItemStack toolStack, String shelfIdentifier) {
		ItemStack poppetItemOriginal = poppetStack.copy();
		PoppetResult result = PoppetHelper.tryUseItemProtectionPoppet(player, poppetStack, toolStack, (ServerLevel)player.level(), shelfIdentifier);
		return handlePoppetResult(result, poppetItemOriginal, player);
	}


	/**
	 * Attempts to damage a poppet.
	 *
	 * @param item The poppet to damage.
	 *
	 * @return true if poppet is destroyed.
	 */
	public static boolean tryDamagePoppet(ItemStack item, ServerLevel level, String shelfIdentifier) {
		item.setDamageValue(item.getDamageValue()+1);
		if(item.getDamageValue() >= item.getMaxDamage()) {
			item.shrink(1);
			if(shelfIdentifier != null && item.getCount() <= 0) {
				PoppetShelfSavedData data = PoppetShelfSavedData.get(level);
				PoppetShelfInventory inventory = data.SHELF_STORAGE.get(shelfIdentifier);
				for(int i = 0; i < inventory.getContainerSize(); i++)
					if(inventory.get(i).equals(item))
						inventory.set(i, ItemStack.EMPTY);
				data.updateShelf(shelfIdentifier);
			}
			return true;
		}
		return false;
	}

	private static boolean handlePoppetResult(PoppetResult result, ItemStack poppetItemOriginal, Player player) {
		if(result == PoppetResult.SUCCESS || result == PoppetResult.SUCCESS_BREAK) {
			if(!player.level().isClientSide)
				CommonServices.NETWORK.sendToAllPlayers(new EnchantedPoppetAnimationPacket(result, poppetItemOriginal, player.getId()), player.level().getServer());
			return true;
		}
		return false;
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

	public enum PoppetResult {
		SUCCESS,
		SUCCESS_BREAK,
		FAIL,
		PASS
	}
}

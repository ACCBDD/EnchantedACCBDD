package favouriteless.enchanted.util;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Util class for functions related to {@link ItemStack}.
 */
public class ItemUtil {

	/**
	 * Drops copies of a {@link Container}'s items on the floor, without modifying the ones stored in the container.
	 */
	public static void dropContentsNoChange(Level level, double x, double y, double z, Container inventory) {
		for(int i = 0; i < inventory.getContainerSize(); ++i) {
			ItemStack item = inventory.getItem(i).copy();

			double width = EntityType.ITEM.getWidth();
			double inverseWidth = 1.0D - width;
			double radius = width / 2.0D;
			double dx = Math.floor(x) + Enchanted.RANDOM.nextDouble() * inverseWidth + radius;
			double dy = Math.floor(y) + Enchanted.RANDOM.nextDouble() * inverseWidth;
			double dz = Math.floor(z) + Enchanted.RANDOM.nextDouble() * inverseWidth + radius;

			while(!item.isEmpty()) {
				ItemEntity entity = new ItemEntity(level, dx, dy, dz, item.split(Enchanted.RANDOM.nextInt(21) + 10));
				entity.setDeltaMovement(Enchanted.RANDOM.nextGaussian() * 0.05D, Enchanted.RANDOM.nextGaussian() * 0.05D + 0.2D, Enchanted.RANDOM.nextGaussian() * 0.05D);
				level.addFreshEntity(entity);
			}
		}
	}

	/**
	 * Check if a given {@link ItemStack} is furnace fuel or not.
	 *
	 * @param stack The {@link ItemStack} to be checked.
	 * @return True if stack's burn value > 0.
	 */
	public static boolean isFuel(ItemStack stack) {
		return CommonServices.PLATFORM.getBurnTime(stack, null) > 0;
	}

	/**
	 * @return true if <b>a</b> is the same item as <b>b</b> AND all components on <b>b</b> are present and equal on
	 * <b>a</b>.
	 */
	public static boolean isSameItemPartial(ItemStack a, ItemStack b) {
		if(a.getItem() != b.getItem())
			return false;

		DataComponentMap aMap = a.getComponents();
		DataComponentMap bMap = b.getComponents();
		for(DataComponentType<?> type : bMap.keySet()) {
			if(!aMap.has(type))
				return false;

			Object aComp = aMap.get(type);
			Object bComp = bMap.get(type);
			if(aComp == null) {
				if(bComp == null)
					continue;
				return false;
			}

			if(!aComp.equals(bComp))
				return false;
		}

		return true;
	}

	public static void giveOrDrop(Player player, ItemStack item) {
		if(player.addItem(item))
			return;

		ItemEntity entity = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), item);
		entity.setNoPickUpDelay();
		entity.setThrower(player);
		player.level().addFreshEntity(player);
	}

}

package com.favouriteless.enchanted.common.util;

import com.favouriteless.enchanted.Enchanted;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StaticItemStackHelper {

	public static boolean canStack(ItemStack original, ItemStack other) {
		if(original.isStackable())
			if(original.sameItem(other))
				if(original.getCount() + other.getCount() <= original.getMaxStackSize())
					return original.getOrCreateTag().equals(other.getOrCreateTag());

		return false;
	}

	/**
	 * Similar to InventoryHelper#dropContents but works with copies of the itemstacks instead.
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param inventory
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

}
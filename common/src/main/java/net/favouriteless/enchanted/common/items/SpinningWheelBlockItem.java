package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.common.blocks.EBlocks;
import net.minecraft.world.item.BlockItem;

/**
 * <strong>IMPORTANT:</strong> Forge implementation will mixin this to set up it's client render stuff.
 */
public class SpinningWheelBlockItem extends BlockItem {

	public SpinningWheelBlockItem() {
		super(EBlocks.SPINNING_WHEEL.get(), new Properties());
	}

}

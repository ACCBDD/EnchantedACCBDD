package net.favouriteless.enchanted.common.items.poppets;

import net.favouriteless.enchanted.common.poppet.PoppetColour;
import net.minecraft.world.item.ItemStack;

public class ItemProtectionPoppetItem extends PoppetItem {

	public float damageMultiplier;

	public ItemProtectionPoppetItem(float failRate, float damageMultiplier, PoppetColour colour, Properties properties) {
		super(failRate, colour, properties);
		this.damageMultiplier = damageMultiplier;
	}

	public void protect(ItemStack item) {
		item.setDamageValue(Math.round(item.getMaxDamage() * damageMultiplier));
	}

}

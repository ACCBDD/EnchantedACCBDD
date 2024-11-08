package net.favouriteless.enchanted.common.items.poppets;

import net.favouriteless.enchanted.common.poppet.PoppetColour;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;

import java.util.function.Predicate;

public class FirePoppetItem extends DeathPoppetItem {

	public FirePoppetItem(float failRate, PoppetColour colour, Predicate<DamageSource> sourcePredicate, Properties properties) {
		super(failRate, colour, sourcePredicate, properties);
	}

	@Override
	public void protect(Player player) {
		player.setHealth(1);
		player.clearFire();
	}

}

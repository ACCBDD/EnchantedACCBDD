package favouriteless.enchanted.common.items.poppets;

import favouriteless.enchanted.common.poppet.PoppetColour;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class FirePoppetEffectItem extends DeathPoppetEffectItem {

	public FirePoppetEffectItem(float failRate, int durability, PoppetColour colour, Supplier<MobEffectInstance> deathEffect, Predicate<DamageSource> sourcePredicate) {
		super(failRate, durability, colour, deathEffect, sourcePredicate);
	}

	@Override
	public void protect(Player player) {
		player.setHealth(1);
		player.clearFire();
		player.addEffect(deathEffect.get());
	}

}

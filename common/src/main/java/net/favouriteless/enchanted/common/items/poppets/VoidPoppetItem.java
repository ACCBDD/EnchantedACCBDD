package net.favouriteless.enchanted.common.items.poppets;

import net.favouriteless.enchanted.common.poppet.PoppetColour;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class VoidPoppetItem extends DeathPoppetItem {

	public VoidPoppetItem(float failRate, PoppetColour colour, Predicate<DamageSource> sourcePredicate, Properties properties) {
		super(failRate, colour, sourcePredicate, properties);
	}

	@Override
	public void protect(Player player) {
		player.setHealth(1);
		Vec3 pos = player.position();
		player.teleportTo(pos.x, 257.0D, pos.z);
	}

}

package net.favouriteless.enchanted.common.effects;

import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class EffectEvents {

	public static boolean onLivingHurt(LivingEntity entity, DamageSource source, float amount) {
		if(entity.hasEffect(new Holder.Direct<>(EEffects.FALL_RESISTANCE.get())))
			return source.is(DamageTypeTags.IS_FALL) || source.is(DamageTypes.FLY_INTO_WALL);
		if(entity.hasEffect(new Holder.Direct<>(EEffects.DROWN_RESISTANCE.get())))
			return source.is(DamageTypeTags.IS_DROWNING);
		if(entity.hasEffect(new Holder.Direct<>(EEffects.MAGIC_RESISTANCE.get())))
			return EEffects.isMagic(source);

		return false;
	}

}

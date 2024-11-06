package net.favouriteless.enchanted.common.effects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EMobEffect extends MobEffect {

    public EMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public EMobEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }

}

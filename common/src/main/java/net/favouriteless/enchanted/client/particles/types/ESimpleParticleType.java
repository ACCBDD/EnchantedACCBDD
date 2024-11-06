package net.favouriteless.enchanted.client.particles.types;

import net.minecraft.core.particles.SimpleParticleType;

/**
 * Wrapper for the vanilla SimpleParticleType so I don't have to do any dodgy changes to access it.
 */
public class ESimpleParticleType extends SimpleParticleType {

    public ESimpleParticleType(boolean alwaysShow) {
        super(alwaysShow);
    }

}

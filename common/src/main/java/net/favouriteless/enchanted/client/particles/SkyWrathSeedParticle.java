package net.favouriteless.enchanted.client.particles;

import net.favouriteless.enchanted.client.particles.types.DelayedPosOptions;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.rites.rites.SkyWrathRite;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;

public class SkyWrathSeedParticle extends NoRenderParticle {

	private static final double xSpread = 0.3D;
	private static final double ySpread = 0.3D;
	private static final double zSpread = 0.3D;

	protected SkyWrathSeedParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z);
		this.x = x;
		this.y = y;
		this.z = z;
		this.lifetime = 60;
		this.hasPhysics = false;
	}

	@Override
	public void tick() {
		if(age++ < lifetime) {
			if(age % 3 == 0) {
				for(int a = 0; a < 360; a += 20) {
					double angle = Math.toRadians(a);

					double cx = x + Math.sin(angle) * SkyWrathRite.LIGHTNING_RADIUS + Math.random() * xSpread;
					double cy = y + Math.random() * ySpread;
					double cz = z + Math.cos(angle) * SkyWrathRite.LIGHTNING_RADIUS + Math.random() * zSpread;

					level.addParticle(new DelayedPosOptions(EParticleTypes.SKY_WRATH.get(), new Vec3(x, y, z),
							SkyWrathRite.EXPLODE-age), cx, cy, cz, 0, 0, 0);
				}
			}
		}
		else
			this.remove();
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {

		public Factory(SpriteSet sprites) {}

		public Particle createParticle(SimpleParticleType data, ClientLevel level, double x, double y, double z,
									   double xSpeed, double ySpeed, double zSpeed) {
			return new SkyWrathSeedParticle(level, x, y, z);
		}
	}

}

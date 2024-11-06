package net.favouriteless.enchanted.client.particles;

import net.favouriteless.enchanted.client.particles.types.ColouredCircleOptions;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class TranspositionIronSeedParticle extends NoRenderParticle {

	protected TranspositionIronSeedParticle(ClientLevel level, double x, double y, double z) {
		super(level, x, y, z);
		this.hasPhysics = false;
	}

	@Override
	public void tick() {
		for(int a = 0; a < 360; a+=2) {
//			double cx = x + Math.sin(a) * RiteTranspositionIron.CIRCLE_RADIUS;
//			double cz = z + Math.cos(a) * RiteTranspositionIron.CIRCLE_RADIUS;
			double cx = x + Math.sin(a) * 8;
			double cz = z + Math.cos(a) * 8;

//			level.addParticle(new CircleMagicData(EParticleTypes.CIRCLE_MAGIC.get(), 170, 111, 58, x, y, z, RiteTranspositionIron.CIRCLE_RADIUS), cx, y, cz, 0.0D, 0.0D, 0.0D);
			level.addParticle(new ColouredCircleOptions(EParticleTypes.CIRCLE_MAGIC.get(), 0xAA6f3A,
							new Vec3(x, y, z), 8), cx, y, cz,
					0.0D, 0.0D, 0.0D);
		}
		this.remove();
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {

		public Factory(SpriteSet pSprites) {
		}

		@Nullable
		@Override
		public Particle createParticle(SimpleParticleType data, ClientLevel level, double x, double y, double z,
									   double xSpeed, double ySpeed, double zSpeed) {
			return new TranspositionIronSeedParticle(level, x, y, z);
		}
	}
}

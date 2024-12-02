package favouriteless.enchanted.client.particles;

import favouriteless.enchanted.client.EParticleRenderTypes;
import favouriteless.enchanted.client.particles.types.DelayedPosOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.world.phys.Vec3;

public class RemoveCurseParticle extends TextureSheetParticle {

	private static final double RAISE_SPEED = 0.7D;
	private static final double RAISE_ACCELERATION = 0.05D;
	private static final double ATTRACT_SPEED = 0.06D;
	private final int raiseTicks;
	private final Vec3 center;

	protected RemoveCurseParticle(ClientLevel level, double x, double y, double z, Vec3 center, int raiseTicks) {
		super(level, x, y, z);
		this.center = center;
		this.raiseTicks = raiseTicks;
		this.alpha = 0.0F;
		this.hasPhysics = false;
	}

	@Override
	public void tick() {
		xo = x;
		yo = y;
		zo = z;
		if(age++ < raiseTicks) {
			if(alpha < 1.0F) {
				alpha += 0.05F;
				if(alpha > 1.0F)
					alpha = 1.0F;
			}

			Vec3 relativePos = new Vec3(x, y, z).subtract(center);
			if(relativePos.length() <= RemoveCurseSeedParticle.ORB_RADIUS) {
				xd = 0.0D;
				yd = 0.0D;
				zd = 0.0D;
			}
			else {
				Vec3 velocity = relativePos.normalize().scale(-1 * ATTRACT_SPEED);
				xd = velocity.x();
				yd = velocity.y();
				zd = velocity.z();
			}
		}
		else {
			if(alpha > 0.0F) {
				alpha -= 0.05F;
				xd = 0.0D;
				yd = Math.min(yd + RAISE_ACCELERATION, RAISE_SPEED);
				zd = 0.0D;
			}
			else
				remove();
		}
		this.move(xd, yd, zd);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return EParticleRenderTypes.translucentParticle();
	}

	public static class Factory implements ParticleProvider<DelayedPosOptions> {
		private final SpriteSet sprite;

		public Factory(SpriteSet sprites) {
			this.sprite = sprites;
		}

		public Particle createParticle(DelayedPosOptions data, ClientLevel level, double x, double y, double z,
									   double xSpeed, double ySpeed, double zSpeed) {
			RemoveCurseParticle particle = new RemoveCurseParticle(level, x, y, z, data.getCenter(), data.getDelay());
			particle.pickSprite(this.sprite);
			return particle;
		}
	}

}
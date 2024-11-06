package net.favouriteless.enchanted.client.particles.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.FastColor;
import org.joml.Vector3f;

public class ColouredCircleOptions implements ParticleOptions {

	private final ParticleType<ColouredCircleOptions> particleType;
	private final int colour;
	private final Vector3f center;
	private final float radius;

	public ColouredCircleOptions(ParticleType<ColouredCircleOptions> particleType, int colour, Vector3f center, float radius) {
		this.particleType = particleType;
		this.colour = colour;
		this.radius = radius;
		this.center = center;
	}

	public static MapCodec<ColouredCircleOptions> codec(ParticleType<ColouredCircleOptions> type) {
		return RecordCodecBuilder.mapCodec(instance -> instance.group(
				Codec.INT.fieldOf("colour").forGetter(data -> data.colour),
				ExtraCodecs.VECTOR3F.fieldOf("center").forGetter(data -> data.center),
				Codec.FLOAT.fieldOf("radius").forGetter(data -> data.radius)
		).apply(instance, (colour, center, radius) -> new ColouredCircleOptions(type, colour, center, radius)));
	}

	public static StreamCodec<? super RegistryFriendlyByteBuf, ColouredCircleOptions> streamCodec(ParticleType<ColouredCircleOptions> type) {
		return StreamCodec.composite(
				ByteBufCodecs.INT, data -> data.colour,
				ByteBufCodecs.VECTOR3F, data -> data.center,
				ByteBufCodecs.FLOAT, data -> data.radius,
				(colour, center, radius) -> new ColouredCircleOptions(type, colour, center, radius)
		);
	}

	public float getRed() {
		return FastColor.ARGB32.red(colour) / 255.0F;
	}

	public float getGreen() {
		return FastColor.ARGB32.green(colour) / 255.0F;
	}

	public float getBlue() {
		return FastColor.ARGB32.blue(colour) / 255.0F;
	}

	public float getAlpha() {
		return FastColor.ARGB32.alpha(colour) / 255.0F;
	}

	public float getRadius() {
		return radius;
	}

	public Vector3f getCenter() {
		return center;
	}

	@Override
	public ParticleType<?> getType() {
		return particleType;
	}

}
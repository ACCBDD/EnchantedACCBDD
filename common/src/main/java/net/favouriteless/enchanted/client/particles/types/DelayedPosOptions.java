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

public class DelayedPosOptions implements ParticleOptions {

	private final ParticleType<DelayedPosOptions> particleType;
	private final int delay;
	private final Vector3f center;

	public DelayedPosOptions(ParticleType<DelayedPosOptions> particleType, Vector3f center, int delay) {
		this.particleType = particleType;
		this.delay = delay;
		this.center = center;
	}

	public static MapCodec<DelayedPosOptions> codec(ParticleType<DelayedPosOptions> type) {
		return RecordCodecBuilder.mapCodec(instance -> instance.group(
				ExtraCodecs.VECTOR3F.fieldOf("center").forGetter(data -> data.center),
				Codec.INT.fieldOf("delay").forGetter(data -> data.delay)
				).apply(instance, (center, delay) -> new DelayedPosOptions(type, center, delay)));
	}

	public static StreamCodec<? super RegistryFriendlyByteBuf, DelayedPosOptions> streamCodec(ParticleType<DelayedPosOptions> type) {
		return StreamCodec.composite(
				ByteBufCodecs.VECTOR3F, data -> data.center,
				ByteBufCodecs.INT, data -> data.delay,
				(center, delay) -> new DelayedPosOptions(type, center, delay)
		);
	}

	public int getDelay() {
		return delay;
	}

	public Vector3f getCenter() {
		return center;
	}

	@Override
	public ParticleType<?> getType() {
		return particleType;
	}

}
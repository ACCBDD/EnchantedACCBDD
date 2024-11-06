package net.favouriteless.enchanted.client.particles.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.util.EExtraCodecs;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.FastColor;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class DelayedPosOptions implements ParticleOptions {

	private final ParticleType<DelayedPosOptions> particleType;
	private final Vec3 center;
	private final int delay;


	public DelayedPosOptions(ParticleType<DelayedPosOptions> particleType, Vec3 center, int delay) {
		this.particleType = particleType;
		this.center = center;
		this.delay = delay;
	}

	public static MapCodec<DelayedPosOptions> codec(ParticleType<DelayedPosOptions> type) {
		return RecordCodecBuilder.mapCodec(instance -> instance.group(
				EExtraCodecs.VEC3.fieldOf("center").forGetter(data -> data.center),
				Codec.INT.fieldOf("delay").forGetter(data -> data.delay)
				).apply(instance, (center, delay) -> new DelayedPosOptions(type, center, delay)));
	}

	public static StreamCodec<? super RegistryFriendlyByteBuf, DelayedPosOptions> streamCodec(ParticleType<DelayedPosOptions> type) {
		return StreamCodec.composite(
				EExtraCodecs.STREAM_VEC3, data -> data.center,
				ByteBufCodecs.INT, data -> data.delay,
				(center, delay) -> new DelayedPosOptions(type, center, delay)
		);
	}

	public Vec3 getCenter() {
		return center;
	}

	public int getDelay() {
		return delay;
	}

	@Override
	public ParticleType<?> getType() {
		return particleType;
	}

}
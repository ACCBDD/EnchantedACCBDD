package net.favouriteless.enchanted.client.particles.types;

import net.favouriteless.enchanted.client.particles.types.DelayedActionParticleType.DelayedActionData;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class DelayedActionParticleType extends ParticleType<DelayedActionData> {
	public static final Codec<DelayedActionData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.STRING.fieldOf("particle_type").forGetter(data -> BuiltInRegistries.PARTICLE_TYPE.getKey(data.particleType).toString()),
			Codec.DOUBLE.fieldOf("centerX").forGetter(data -> data.centerX),
			Codec.DOUBLE.fieldOf("centerY").forGetter(data -> data.centerY),
			Codec.DOUBLE.fieldOf("centerZ").forGetter(data -> data.centerZ),
			Codec.INT.fieldOf("actionTicks").forGetter(data -> data.actionTicks)
	).apply(instance, (type, centerX, centerY, centerZ, actionTicks) -> new DelayedActionData((ParticleType<DelayedActionData>)BuiltInRegistries.PARTICLE_TYPE.get(new ResourceLocation(type)), centerX, centerY, centerZ, actionTicks)));

	public DelayedActionParticleType(boolean alwaysShow) {
		super(alwaysShow, DelayedActionData.DESERIALIZER);
	}

	@Override
	public Codec<DelayedActionData> codec() {
		return CODEC;
	}

	public static class DelayedActionData implements ParticleOptions {

		public static final Deserializer<DelayedActionData> DESERIALIZER = new Deserializer<>() {
			public DelayedActionData fromCommand(ParticleType<DelayedActionData> particleType, StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				double centerX = reader.readDouble();
				reader.expect(' ');
				double centerY = reader.readDouble();
				reader.expect(' ');
				double centerZ = reader.readDouble();
				reader.expect(' ');
				int actionTicks = reader.readInt();

				return new DelayedActionData(particleType, centerX, centerY, centerZ, actionTicks);
			}

			public DelayedActionData fromNetwork(ParticleType<DelayedActionData> particleType, FriendlyByteBuf buffer) {
				return new DelayedActionData(particleType, buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readInt());
			}
		};

		private final ParticleType<DelayedActionData> particleType;
		private final double centerX;
		private final double centerY;
		private final double centerZ;
		private final int actionTicks;

		public DelayedActionData(ParticleType<DelayedActionData> particleType, double centerX, double centerY, double centerZ, int explodeTicks) {
			this.particleType = particleType;
			this.centerX = centerX;
			this.centerY = centerY;
			this.centerZ = centerZ;
			this.actionTicks = explodeTicks;
		}


		@Override
		public String writeToString() {
			return String.format(Locale.ROOT, "%s", BuiltInRegistries.PARTICLE_TYPE.getKey(getType()));
		}

		@Override
		public void writeToNetwork(FriendlyByteBuf buffer) {
			buffer.writeDouble(centerX);
			buffer.writeDouble(centerY);
			buffer.writeDouble(centerZ);
			buffer.writeInt(actionTicks);
		}

		@Override
		public ParticleType<?> getType() {
			return particleType;
		}

		public double getCenterX() {
			return centerX;
		}

		public double getCenterY() {
			return centerY;
		}

		public double getCenterZ() {
			return centerZ;
		}

		public int getActionTicks() {
			return actionTicks;
		}

	}
}
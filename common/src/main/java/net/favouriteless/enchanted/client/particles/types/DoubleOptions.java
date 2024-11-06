package net.favouriteless.enchanted.client.particles.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class DoubleOptions implements ParticleOptions {

    private final ParticleType<DoubleOptions> particleType;
    private final double value;

    public DoubleOptions(ParticleType<DoubleOptions> particleType, double value) {
        this.particleType = particleType;
        this.value = value;
    }

    public static MapCodec<DoubleOptions> codec(ParticleType<DoubleOptions> type) {
        return Codec.DOUBLE.xmap(i -> new DoubleOptions(type, i), o -> o.value).fieldOf("value");

    }

    public static StreamCodec<? super RegistryFriendlyByteBuf, DoubleOptions> streamCodec(ParticleType<DoubleOptions> type) {
        return ByteBufCodecs.DOUBLE.map(i -> new DoubleOptions(type, i), o -> o.value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }

}
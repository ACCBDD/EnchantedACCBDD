package net.favouriteless.enchanted.client.particles.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.FastColor;

public class ColourOptions implements ParticleOptions {

    private final ParticleType<ColourOptions> particleType;
    private final int colour;

    public ColourOptions(ParticleType<ColourOptions> particleType, int colour) {
        this.particleType = particleType;
        this.colour = colour;
    }

    public static MapCodec<ColourOptions> codec(ParticleType<ColourOptions> type) {
        return Codec.INT.xmap(i -> new ColourOptions(type, i), o -> o.colour).fieldOf("colour");

    }

    public static StreamCodec<? super RegistryFriendlyByteBuf, ColourOptions> streamCodec(ParticleType<ColourOptions> type) {
        return ByteBufCodecs.INT.map(i -> new ColourOptions(type, i), o -> o.colour);
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

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }

}
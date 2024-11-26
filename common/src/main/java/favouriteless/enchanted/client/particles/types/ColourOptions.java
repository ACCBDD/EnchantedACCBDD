package favouriteless.enchanted.client.particles.types;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.FastColor;
import net.minecraft.world.phys.Vec3;

public class ColourOptions implements ParticleOptions {
    public static final ParticleOptions.Deserializer<ColourOptions> DESERIALIZER = new ParticleOptions.Deserializer<ColourOptions>() {
        @Override
        public ColourOptions fromCommand(ParticleType<ColourOptions> particleType, StringReader stringReader) throws CommandSyntaxException {
            int colour = stringReader.readInt();
            return new ColourOptions(particleType, colour);
        }

        @Override
        public ColourOptions fromNetwork(ParticleType<ColourOptions> particleType, FriendlyByteBuf friendlyByteBuf) {
            return new ColourOptions(particleType, friendlyByteBuf.readInt());
        }
    };

    private final ParticleType<ColourOptions> particleType;
    private final int colour;

    public ColourOptions(ParticleType<ColourOptions> particleType, int colour) {
        this.particleType = particleType;
        this.colour = colour;
    }

    public static MapCodec<ColourOptions> codec(ParticleType<ColourOptions> type) {
        return Codec.INT.xmap(i -> new ColourOptions(type, i), o -> o.colour).fieldOf("colour");
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

    @Override
    public void writeToNetwork(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(colour);
    }

    @Override
    public String writeToString() {
        return String.valueOf(colour);
    }
}

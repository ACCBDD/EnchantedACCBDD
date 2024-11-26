package favouriteless.enchanted.client.particles.types;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.FastColor;

public class TwoColourOptions implements ParticleOptions {

    public static final ParticleOptions.Deserializer<TwoColourOptions> DESERIALIZER = new ParticleOptions.Deserializer<TwoColourOptions>() {
        @Override
        public TwoColourOptions fromCommand(ParticleType<TwoColourOptions> particleType, StringReader stringReader) throws CommandSyntaxException {
            int first = stringReader.readInt();
            int second = stringReader.readInt();
            return new TwoColourOptions(particleType, first, second);
        }

        @Override
        public TwoColourOptions fromNetwork(ParticleType<TwoColourOptions> particleType, FriendlyByteBuf friendlyByteBuf) {
            return new TwoColourOptions(particleType, friendlyByteBuf.readInt(), friendlyByteBuf.readInt());
        }
    };

    private final ParticleType<TwoColourOptions> particleType;
    private final int first;
    private final int second;

    public TwoColourOptions(ParticleType<TwoColourOptions> particleType, int first, int second) {
        this.particleType = particleType;
        this.first = first;
        this.second = second;
    }

    public static MapCodec<TwoColourOptions> codec(ParticleType<TwoColourOptions> type) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.INT.fieldOf("first").forGetter(data -> data.first),
                Codec.INT.fieldOf("second").forGetter(data -> data.second)
        ).apply(instance, (first, second) -> new TwoColourOptions(type, first, second)));
    }

    public float getRedFirst() {
        return FastColor.ARGB32.red(first) / 255.0F;
    }

    public float getGreenFirst() {
        return FastColor.ARGB32.green(first) / 255.0F;
    }

    public float getBlueFirst() {
        return FastColor.ARGB32.blue(first) / 255.0F;
    }

    public float getAlphaFirst() {
        return FastColor.ARGB32.alpha(first) / 255.0F;
    }

    public float getRedSecond() {
        return FastColor.ARGB32.red(second) / 255.0F;
    }

    public float getGreenSecond() {
        return FastColor.ARGB32.green(second) / 255.0F;
    }

    public float getBlueSecond() {
        return FastColor.ARGB32.blue(second) / 255.0F;
    }

    public float getAlphaSecond() {
        return FastColor.ARGB32.alpha(second) / 255.0F;
    }

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(first);
        friendlyByteBuf.writeInt(second);
    }

    @Override
    public String writeToString() {
        return first + " " + second;
    }
}

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

public class DoubleOptions implements ParticleOptions {

    public static final ParticleOptions.Deserializer<DoubleOptions> DESERIALIZER = new ParticleOptions.Deserializer<DoubleOptions>() {
        @Override
        public DoubleOptions fromCommand(ParticleType<DoubleOptions> particleType, StringReader stringReader) throws CommandSyntaxException {
            double value = stringReader.readDouble();
            return new DoubleOptions(particleType, value);
        }

        @Override
        public DoubleOptions fromNetwork(ParticleType<DoubleOptions> particleType, FriendlyByteBuf friendlyByteBuf) {
            return new DoubleOptions(particleType, friendlyByteBuf.readDouble());
        }
    };

    private final ParticleType<DoubleOptions> particleType;
    private final double value;

    public DoubleOptions(ParticleType<DoubleOptions> particleType, double value) {
        this.particleType = particleType;
        this.value = value;
    }

    public static MapCodec<DoubleOptions> codec(ParticleType<DoubleOptions> type) {
        return Codec.DOUBLE.xmap(i -> new DoubleOptions(type, i), o -> o.value).fieldOf("value");
    }

    public double getValue() {
        return value;
    }

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeDouble(value);
    }

    @Override
    public String writeToString() {
        return String.valueOf(value);
    }
}

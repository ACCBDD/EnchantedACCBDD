package favouriteless.enchanted.client.particles.types;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.common.util.EExtraCodecs;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

public class DelayedPosOptions implements ParticleOptions {

    public static final ParticleOptions.Deserializer<DelayedPosOptions> DESERIALIZER = new ParticleOptions.Deserializer<DelayedPosOptions>() {
        @Override
        public DelayedPosOptions fromCommand(ParticleType<DelayedPosOptions> particleType, StringReader stringReader) throws CommandSyntaxException {
            Vec3 center = new Vec3(stringReader.readDouble(), stringReader.readDouble(), stringReader.readDouble());
            int delay = stringReader.readInt();
            return new DelayedPosOptions(particleType, center, delay);
        }

        @Override
        public DelayedPosOptions fromNetwork(ParticleType<DelayedPosOptions> particleType, FriendlyByteBuf friendlyByteBuf) {
            Vec3 center = new Vec3(friendlyByteBuf.readVector3f());
            int delay = friendlyByteBuf.readInt();
            return new DelayedPosOptions(particleType, center, delay);
        }
    };

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

    @Override
    public void writeToNetwork(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeVector3f(center.toVector3f());
        friendlyByteBuf.writeInt(delay);
    }

    @Override
    public String writeToString() {
        return center.x + " " + center.y + " " + center.z + " " + delay;
    }
}

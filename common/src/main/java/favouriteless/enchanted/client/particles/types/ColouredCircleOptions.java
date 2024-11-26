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
import net.minecraft.util.FastColor;
import net.minecraft.world.phys.Vec3;

public class ColouredCircleOptions implements ParticleOptions {

    public static final ParticleOptions.Deserializer<ColouredCircleOptions> DESERIALIZER = new ParticleOptions.Deserializer<ColouredCircleOptions>() {
        @Override
        public ColouredCircleOptions fromCommand(ParticleType<ColouredCircleOptions> particleType, StringReader stringReader) throws CommandSyntaxException {
            int colour = stringReader.readInt();
            Vec3 center = new Vec3(stringReader.readDouble(), stringReader.readDouble(), stringReader.readDouble());
            float radius = stringReader.readFloat();
            return new ColouredCircleOptions(particleType, colour, center, radius);
        }

        @Override
        public ColouredCircleOptions fromNetwork(ParticleType<ColouredCircleOptions> particleType, FriendlyByteBuf friendlyByteBuf) {
            int colour = friendlyByteBuf.readInt();
            Vec3 center = new Vec3(friendlyByteBuf.readVector3f());
            float radius = friendlyByteBuf.readFloat();
            return new ColouredCircleOptions(particleType, colour, center, radius);
        }
    };

    private final ParticleType<ColouredCircleOptions> particleType;
    private final int colour;
    private final Vec3 center;
    private final float radius;

    public ColouredCircleOptions(ParticleType<ColouredCircleOptions> particleType, int colour, Vec3 center, float radius) {
        this.particleType = particleType;
        this.colour = colour;
        this.radius = radius;
        this.center = center;
    }

    public static MapCodec<ColouredCircleOptions> codec(ParticleType<ColouredCircleOptions> type) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.INT.fieldOf("colour").forGetter(data -> data.colour),
                EExtraCodecs.VEC3.fieldOf("center").forGetter(data -> data.center),
                Codec.FLOAT.fieldOf("radius").forGetter(data -> data.radius)
        ).apply(instance, (colour, center, radius) -> new ColouredCircleOptions(type, colour, center, radius)));
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

    public Vec3 getCenter() {
        return center;
    }

    @Override
    public ParticleType<?> getType() {
        return particleType;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(colour);
        friendlyByteBuf.writeVector3f(center.toVector3f());
        friendlyByteBuf.writeFloat(radius);
    }

    @Override
    public String writeToString() {
        return colour + " " + center.x + " " + center.y + " " + center.z + " " + radius;
    }
}

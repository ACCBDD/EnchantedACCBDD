package favouriteless.enchanted.common.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.phys.Vec3;

public class EExtraCodecs {

    public static final Codec<Integer> HEX_INT = Codec.STRING.comapFlatMap(s -> {
        try {
            return DataResult.success(Integer.parseUnsignedInt(s, 16));
        } catch(NumberFormatException e) {
            return DataResult.error(() -> "Not a valid hex number: " + s + " " + e.getMessage());
        }
    }, i -> Integer.toHexString(i).toUpperCase());

    public static final Codec<Vec3> VEC3 = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("x").forGetter(v -> v.x),
            Codec.DOUBLE.fieldOf("y").forGetter(v -> v.y),
            Codec.DOUBLE.fieldOf("z").forGetter(v -> v.z)
    ).apply(instance, Vec3::new));
}

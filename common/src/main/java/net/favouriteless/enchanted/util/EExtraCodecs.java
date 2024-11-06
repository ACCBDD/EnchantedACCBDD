package net.favouriteless.enchanted.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.List;

public class EExtraCodecs {

    public static final Codec<Integer> HEX_INT = Codec.STRING.comapFlatMap(s -> {
        try {
            return DataResult.success(Integer.parseUnsignedInt(s, 16));
        } catch(NumberFormatException e) {
            return DataResult.error(() -> "Not a valid hex number: " + s + " " + e.getMessage());
        }
    }, Integer::toHexString);

}

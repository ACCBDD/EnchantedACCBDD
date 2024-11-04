package favouriteless.enchanted.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

public class ExtraCodecs {

    public static final Codec<Integer> HEX_INT = Codec.STRING.comapFlatMap(s -> {
        try {
            return DataResult.success(Integer.parseUnsignedInt(s, 16));
        } catch(NumberFormatException e) {
            return DataResult.error(() -> "Not a valid hex number: " + s + " " + e.getMessage());
        }
    }, Integer::toHexString);

}

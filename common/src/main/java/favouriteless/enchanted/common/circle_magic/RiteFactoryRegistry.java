package favouriteless.enchanted.common.circle_magic;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import favouriteless.enchanted.api.rites.RiteFactory;
import net.minecraft.resources.ResourceLocation;

public class RiteFactoryRegistry {

    private static final BiMap<ResourceLocation, Codec<? extends RiteFactory>> typeCodecs = HashBiMap.create();

    public static final Codec<RiteFactory> CODEC = ResourceLocation.CODEC.dispatch(RiteFactory::id, typeCodecs::get);

    public static void register(ResourceLocation id, Codec<? extends RiteFactory> codec) {
        if(typeCodecs.containsKey(id))
            throw new IllegalArgumentException("Attempted to register a duplicate RiteFactory: " + id.toString());
        typeCodecs.put(id, codec);
    }

}
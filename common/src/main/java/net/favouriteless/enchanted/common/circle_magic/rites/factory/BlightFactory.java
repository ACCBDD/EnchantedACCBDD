package net.favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.circle_magic.rites.BlightRite;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record BlightFactory(int radius) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("blight");

    public static final MapCodec<BlightFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("radius").forGetter(f -> f.radius)
    ).apply(instance, BlightFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new BlightRite(baseParams, params, radius);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.FertilityRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record FertilityFactory(int radius) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("fertility");

    public static final MapCodec<FertilityFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("radius").forGetter(f -> f.radius)
    ).apply(instance, FertilityFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new FertilityRite(baseParams, params, radius);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

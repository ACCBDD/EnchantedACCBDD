package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.ForestRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SaplingBlock;

public record ForestFactory(int radius, int spacing) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("forest");

    public static final MapCodec<ForestFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("radius").forGetter(f -> f.radius),
            Codec.INT.fieldOf("spacing").forGetter(f -> f.radius)
    ).apply(instance, ForestFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new ForestRite(baseParams, params, radius, spacing);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

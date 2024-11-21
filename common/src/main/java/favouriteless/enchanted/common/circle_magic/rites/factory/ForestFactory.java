package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.ForestRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

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

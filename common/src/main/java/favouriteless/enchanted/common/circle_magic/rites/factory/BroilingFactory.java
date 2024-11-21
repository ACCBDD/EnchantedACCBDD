package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.BroilingRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record BroilingFactory(double burnChance) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("broiling");

    public static final MapCodec<BroilingFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.DOUBLE.optionalFieldOf("burn_chance", 0.3D).forGetter(f -> f.burnChance)
    ).apply(instance, BroilingFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new BroilingRite(baseParams, params, burnChance);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

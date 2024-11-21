package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.RiteTargetingType;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.SkyWrathEntityRite;
import favouriteless.enchanted.common.circle_magic.rites.SkyWrathRite;
import favouriteless.enchanted.common.circle_magic.rites.SkyWrathWaystoneRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record SkyWrathFactory(RiteTargetingType target) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sky_wrath");

    public static final MapCodec<SkyWrathFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RiteTargetingType.CODEC.optionalFieldOf("target", RiteTargetingType.DEFAULT).forGetter(f -> f.target)
    ).apply(instance, SkyWrathFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return switch(target) {
            case DEFAULT -> new SkyWrathRite(baseParams, params);
            case LOCATION -> new SkyWrathWaystoneRite(baseParams, params);
            case ENTITY -> new SkyWrathEntityRite(baseParams, params);
        };
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

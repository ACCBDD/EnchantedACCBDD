package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.RiteTargetingType;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.TransposeCasterEntityRite;
import favouriteless.enchanted.common.circle_magic.rites.TransposeCasterWaystoneRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record TransposeCasterFactory(RiteTargetingType target) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("transpose_caster");

    public static final MapCodec<TransposeCasterFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RiteTargetingType.CODEC.optionalFieldOf("target", RiteTargetingType.DEFAULT).forGetter(f -> f.target)
    ).apply(instance, TransposeCasterFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return switch(target) {
            case DEFAULT, LOCATION -> new TransposeCasterWaystoneRite(baseParams, params);
            case ENTITY -> new TransposeCasterEntityRite(baseParams, params);
        };
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

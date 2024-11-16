package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.RiteTargetingType;
import net.favouriteless.enchanted.common.rites.rites.*;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record SkyWrathRiteFactory(RiteTargetingType target) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sky_wrath");

    public static final MapCodec<SkyWrathRiteFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RiteTargetingType.CODEC.optionalFieldOf("target", RiteTargetingType.DEFAULT).forGetter(f -> f.target)
    ).apply(instance, SkyWrathRiteFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return switch(target) {
            case DEFAULT -> new SkyWrathRite(baseParams, params);
            case WAYSTONE -> new SkyWrathWaystoneRite(baseParams, params);
            case ENTITY -> new SkyWrathEntityRite(baseParams, params);
        };
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

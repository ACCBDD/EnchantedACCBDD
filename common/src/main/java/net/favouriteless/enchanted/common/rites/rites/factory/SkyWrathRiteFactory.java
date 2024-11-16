package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.SkyWrathRite;
import net.minecraft.resources.ResourceLocation;

public class SkyWrathRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sky_wrath");

    public static final MapCodec<SkyWrathRiteFactory> CODEC = MapCodec.unit(new SkyWrathRiteFactory());

    @Override
    public Rite create(BaseRiteParams params) {
        return new SkyWrathRite(params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

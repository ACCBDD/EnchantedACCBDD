package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.SkyWrathBloodedRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.minecraft.resources.ResourceLocation;

public class SkyWrathBloodedRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sky_wrath_blooded");

    public static final MapCodec<SkyWrathBloodedRiteFactory> CODEC = MapCodec.unit(new SkyWrathBloodedRiteFactory());

    @Override
    public Rite create(BaseRiteParams params) {
        return new SkyWrathBloodedRite(params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

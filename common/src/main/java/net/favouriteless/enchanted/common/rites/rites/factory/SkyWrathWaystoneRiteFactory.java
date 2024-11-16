package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.SkyWrathWaystoneRite;
import net.minecraft.resources.ResourceLocation;

public class SkyWrathWaystoneRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sky_wrath_waystone");

    public static final MapCodec<SkyWrathWaystoneRiteFactory> CODEC = MapCodec.unit(new SkyWrathWaystoneRiteFactory());

    @Override
    public Rite create(BaseRiteParams params) {
        return new SkyWrathWaystoneRite(params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

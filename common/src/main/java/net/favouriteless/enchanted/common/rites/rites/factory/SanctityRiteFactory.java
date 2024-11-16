package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.favouriteless.enchanted.common.rites.rites.SanctityRite;
import net.minecraft.resources.ResourceLocation;

public class SanctityRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sanctity");

    public static final MapCodec<SanctityRiteFactory> CODEC = MapCodec.unit(SanctityRiteFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new SanctityRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

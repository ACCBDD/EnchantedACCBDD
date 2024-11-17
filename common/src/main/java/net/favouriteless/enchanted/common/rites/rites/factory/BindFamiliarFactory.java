package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.BindFamiliarRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public class BindFamiliarFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("bind_familiar");

    public static final MapCodec<BindFamiliarFactory> CODEC = MapCodec.unit(BindFamiliarFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new BindFamiliarRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.favouriteless.enchanted.common.rites.rites.SummonFamiliarRite;
import net.minecraft.resources.ResourceLocation;

public class SummonFamiliarFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("summon_familiar");

    public static final MapCodec<SummonFamiliarFactory> CODEC = MapCodec.unit(SummonFamiliarFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new SummonFamiliarRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

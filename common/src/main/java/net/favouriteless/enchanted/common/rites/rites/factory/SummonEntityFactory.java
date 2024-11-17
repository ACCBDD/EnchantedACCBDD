package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.favouriteless.enchanted.common.rites.rites.SummonEntityRite;
import net.minecraft.resources.ResourceLocation;

public record SummonEntityFactory() implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("summon_entity");

    public static final MapCodec<SummonEntityFactory> CODEC = MapCodec.unit(new SummonEntityFactory());

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new SummonEntityRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

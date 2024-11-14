package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.BroilingRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.minecraft.resources.ResourceLocation;

public class BroilingRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("broiling");

    public static final MapCodec<BroilingRiteFactory> CODEC = MapCodec.unit(BroilingRiteFactory::new);

    @Override
    public Rite create(BaseRiteParams params) {
        return new BroilingRite(params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

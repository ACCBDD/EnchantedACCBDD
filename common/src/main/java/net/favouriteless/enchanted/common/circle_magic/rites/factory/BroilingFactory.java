package net.favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.circle_magic.rites.BroilingRite;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public class BroilingFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("broiling");

    public static final MapCodec<BroilingFactory> CODEC = MapCodec.unit(BroilingFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new BroilingRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

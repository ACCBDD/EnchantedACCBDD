package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.WaystoneTransposeCasterRite;
import net.minecraft.resources.ResourceLocation;

public class WaystoneTransposeCasterRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("transpose_caster_waystone");

    public static final MapCodec<WaystoneTransposeCasterRiteFactory> CODEC = MapCodec.unit(new WaystoneTransposeCasterRiteFactory());

    @Override
    public Rite create(BaseRiteParams params) {
        return new WaystoneTransposeCasterRite(params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

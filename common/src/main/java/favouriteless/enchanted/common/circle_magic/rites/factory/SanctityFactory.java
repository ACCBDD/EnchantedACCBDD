package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import favouriteless.enchanted.common.circle_magic.rites.SanctityRite;
import net.minecraft.resources.ResourceLocation;

public class SanctityFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("sanctity");

    public static final MapCodec<SanctityFactory> CODEC = MapCodec.unit(SanctityFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new SanctityRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

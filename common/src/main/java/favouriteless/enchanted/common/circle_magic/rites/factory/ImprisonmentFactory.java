package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.ImprisonmentRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public class ImprisonmentFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("imprisonment");

    public static final MapCodec<ImprisonmentFactory> CODEC = MapCodec.unit(ImprisonmentFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new ImprisonmentRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import favouriteless.enchanted.common.circle_magic.rites.TotalEclipseRite;
import net.minecraft.resources.ResourceLocation;

public class TotalEclipseFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("total_eclipse");

    public static final MapCodec<TotalEclipseFactory> CODEC = MapCodec.unit(TotalEclipseFactory::new);

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new TotalEclipseRite(baseParams, params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

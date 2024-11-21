package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import favouriteless.enchanted.common.circle_magic.rites.SummonFamiliarRite;
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

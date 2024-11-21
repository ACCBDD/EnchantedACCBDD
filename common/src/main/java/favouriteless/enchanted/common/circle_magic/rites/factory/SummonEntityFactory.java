package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import favouriteless.enchanted.common.circle_magic.rites.SummonEntityRite;
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

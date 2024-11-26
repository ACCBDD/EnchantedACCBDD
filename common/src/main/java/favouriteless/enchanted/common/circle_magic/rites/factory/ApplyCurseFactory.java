package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.curses.CurseType;
import favouriteless.enchanted.common.circle_magic.rites.ApplyCurseRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import favouriteless.enchanted.common.init.registry.CurseTypes;
import net.minecraft.resources.ResourceLocation;

public class ApplyCurseFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("apply_curse");

    public static final MapCodec<ApplyCurseFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("curse").forGetter(f -> f.curse.getId())
    ).apply(instance, ApplyCurseFactory::new));

    private final CurseType<?> curse;

    public ApplyCurseFactory(ResourceLocation id) {
        curse = CurseTypes.get(id);
        if(curse == null)
            throw new IllegalArgumentException("Error creating ApplyCurseFactory: " + id.toString() + " is not a curse type.");
    }

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new ApplyCurseRite(baseParams, params, curse);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

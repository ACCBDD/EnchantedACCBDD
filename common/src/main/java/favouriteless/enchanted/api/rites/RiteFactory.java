package favouriteless.enchanted.api.rites;

import net.favouriteless.enchanted.common.circle_magic.rites.Rite;
import net.minecraft.resources.ResourceLocation;

public interface RiteFactory {

    Rite create(Rite.BaseRiteParams baseParams, Rite.RiteParams params);

    ResourceLocation id();

}

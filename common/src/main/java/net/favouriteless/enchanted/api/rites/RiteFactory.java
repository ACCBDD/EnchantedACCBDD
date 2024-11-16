package net.favouriteless.enchanted.api.rites;

import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public interface RiteFactory {

    Rite create(BaseRiteParams baseParams, RiteParams params);

    ResourceLocation id();

}

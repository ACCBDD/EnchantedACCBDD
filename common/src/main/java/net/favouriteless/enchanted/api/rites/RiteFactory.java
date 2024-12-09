package net.favouriteless.enchanted.api.rites;

import net.favouriteless.enchanted.common.circle_magic.rites.Rite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public interface RiteFactory {

    Rite create(Rite.BaseRiteParams baseParams, Rite.RiteParams params);

    ResourceLocation id();

    /**
     * Item outputs for JEI. If this list is non-null, JEI will automatically support RiteTypes using the factory.
     */
    default @Nullable List<ItemStack> getOutputs() {
        return null;
    }
}

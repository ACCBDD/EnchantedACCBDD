package favouriteless.enchanted.integrations.jei.recipes;

import favouriteless.enchanted.common.circle_magic.RiteType;
import favouriteless.enchanted.integrations.jei.EJeiRecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public record JeiRiteRecipe(ResourceLocation id, RiteType rite) {

    public static void register(IRecipeRegistration registration, Registry<RiteType> registry) {
        registration.addRecipes(EJeiRecipeTypes.RITE,
                registry.entrySet().stream()
                        .filter(e -> e.getValue().getOutputs() != null)
                        .map(e -> new JeiRiteRecipe(e.getKey().location(), e.getValue()))
                        .toList()
        );
    }
}

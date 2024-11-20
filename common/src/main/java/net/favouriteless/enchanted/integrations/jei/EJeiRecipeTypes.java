package net.favouriteless.enchanted.integrations.jei;

import mezz.jei.api.recipe.RecipeType;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.recipes.*;
import net.favouriteless.enchanted.integrations.jei.recipes.JeiMutandisRecipe;

public class EJeiRecipeTypes {

    public static final RecipeType<ByproductRecipe> BYPRODUCT = new RecipeType<>(Enchanted.id("byproduct_category"), ByproductRecipe.class);
    public static final RecipeType<DistillingRecipe> DISTILLING = new RecipeType<>(Enchanted.id("distilling_category"), DistillingRecipe.class);
    public static final RecipeType<SpinningRecipe> SPINNING = new RecipeType<>(Enchanted.id("spinning_category"), SpinningRecipe.class);
    public static final RecipeType<WitchCauldronRecipe> CAULDRON = new RecipeType<>(Enchanted.id("witch_cauldron_category"), WitchCauldronRecipe.class);
    public static final RecipeType<KettleRecipe> KETTLE = new RecipeType<>(Enchanted.id("kettle_category"), KettleRecipe.class);
//    public static final RecipeType<JEIRiteRecipe> RITE = new RecipeType<>(Enchanted.id("rite_category"), JEIRiteRecipe.class);
    public static final RecipeType<JeiMutandisRecipe> MUTANDIS = new RecipeType<>(Enchanted.id("mutandis_category"), JeiMutandisRecipe.class);
    public static final RecipeType<JeiMutandisRecipe> MUTANDIS_EXTREMIS = new RecipeType<>(Enchanted.id("mutandis_extremis_category"), JeiMutandisRecipe.class);


}

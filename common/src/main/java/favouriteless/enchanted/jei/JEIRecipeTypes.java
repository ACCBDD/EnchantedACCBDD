package favouriteless.enchanted.jei;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.api.rites.CreateItemRite;
import favouriteless.enchanted.jei.recipes.JEIMutandisRecipe;
import favouriteless.enchanted.common.recipes.*;
import mezz.jei.api.recipe.RecipeType;

public class JEIRecipeTypes {

    public static final RecipeType<ByproductRecipe> RECIPE_TYPE_BYPRODUCT = new RecipeType<>(Enchanted.id("byproduct_category"), ByproductRecipe.class);
    public static final RecipeType<DistillingRecipe> RECIPE_TYPE_DISTILLING = new RecipeType<>(Enchanted.id("distilling_category"), DistillingRecipe.class);
    public static final RecipeType<SpinningRecipe> RECIPE_TYPE_SPINNING = new RecipeType<>(Enchanted.id("spinning_category"), SpinningRecipe.class);
    public static final RecipeType<WitchCauldronRecipe> RECIPE_TYPE_WITCH_CAULDRON = new RecipeType<>(Enchanted.id("witch_cauldron_category"), WitchCauldronRecipe.class);
    public static final RecipeType<KettleRecipe> RECIPE_TYPE_KETTLE = new RecipeType<>(Enchanted.id("kettle_category"), KettleRecipe.class);
    public static final RecipeType<CreateItemRite> RECIPE_TYPE_RITE = new RecipeType<>(Enchanted.id("rite_category"), CreateItemRite.class);
    public static final RecipeType<JEIMutandisRecipe> RECIPE_TYPE_MUTANDIS = new RecipeType<>(Enchanted.id("mutandis_category"), JEIMutandisRecipe.class);
    public static final RecipeType<JEIMutandisRecipe> RECIPE_TYPE_MUTANDIS_EXTREMIS = new RecipeType<>(Enchanted.id("mutandis_extremis_category"), JEIMutandisRecipe.class);

}
package net.favouriteless.enchanted.patchouli.processors;

import net.favouriteless.enchanted.common.init.registry.EItems;
import net.favouriteless.enchanted.common.recipes.ByproductRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class WitchOvenRecipeProcessor implements IComponentProcessor {

	private ByproductRecipe byproductRecipe;
	private ItemStack itemIn;
	private ItemStack resultItem;

	@Override
	public void setup(Level level, IVariableProvider variables) {
		ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());

		RecipeManager recipeManager = level.getRecipeManager();
		byproductRecipe = (ByproductRecipe)recipeManager.byKey(recipeId).orElseThrow(() -> new IllegalArgumentException("Could not find recipe for: " + recipeId));
		itemIn = variables.get("itemIn").as(ItemStack.class);
		resultItem = variables.get("resultItem").as(ItemStack.class);
	}

	@Override
	public IVariable process(Level level, String key) {

		if(key.startsWith("input"))
			return IVariable.from(itemIn);
		else if(key.startsWith("jar"))
			return IVariable.from(new ItemStack(EItems.CLAY_JAR.get()));
		else if(key.startsWith("result"))
			return IVariable.from(resultItem);
		else if(key.startsWith("byproduct"))
			return IVariable.from(byproductRecipe.getResultItem(level.registryAccess()));

		return null;
	}
}

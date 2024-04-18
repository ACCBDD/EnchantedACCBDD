package com.favouriteless.enchanted.patchouli.processors;

import com.favouriteless.enchanted.common.recipes.WitchCauldronRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class CauldronRecipeProcessor implements IComponentProcessor {

	private WitchCauldronRecipe recipe;

	@Override
	public void setup(IVariableProvider variables) {
		ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());
		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
		recipe = (WitchCauldronRecipe)recipeManager.byKey(recipeId).orElseThrow(() -> new IllegalArgumentException("Could not find recipe for: " + recipeId));;
	}

	@Override
	public IVariable process(String key) {

		if(key.startsWith("result"))
			return IVariable.from(recipe.getResultItem());
		else if(key.startsWith("itemList"))
			return IVariable.from(getItemArray(recipe));
		else if(key.startsWith("power"))
			return IVariable.wrap(recipe.getPower() + " Altar Power");

		return null;
	}

	private static ItemStack[] getItemArray(WitchCauldronRecipe recipe) {
		NonNullList<ItemStack> itemStacks = recipe.getItemsIn();
		ItemStack[] itemArray = new ItemStack[itemStacks.size()];
		for(int i = 0; i < itemArray.length; i++) {
			itemArray[i] = itemStacks.get(i);
		}
		return itemArray;
	}
}

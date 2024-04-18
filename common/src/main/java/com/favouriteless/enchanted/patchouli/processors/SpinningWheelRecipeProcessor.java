package com.favouriteless.enchanted.patchouli.processors;

import com.favouriteless.enchanted.common.recipes.SpinningRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class SpinningWheelRecipeProcessor implements IComponentProcessor {

	private SpinningRecipe recipe;

	@Override
	public void setup(IVariableProvider variables) {
		ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());
		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
		recipe = (SpinningRecipe)recipeManager.byKey(recipeId).orElseThrow(() -> new IllegalArgumentException("Could not find recipe for: " + recipeId));;
	}

	@Override
	public IVariable process(String key) {
		if(key.startsWith("in")) {
			int index = Integer.parseInt(String.valueOf(key.charAt(key.length()-1)));
			NonNullList<ItemStack> stacks = recipe.getItemsIn();
			if(index >= 0 && index < stacks.size()) {
				return IVariable.from(stacks.get(index));
			}
			return IVariable.from(ItemStack.EMPTY);
		}
		else if(key.equals("out")) {
			ItemStack stack = recipe.getResultItem();

			return IVariable.from(stack);
		}

		return null;
	}
}

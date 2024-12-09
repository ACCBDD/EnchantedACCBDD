package net.favouriteless.enchanted.patchouli.processors;

import net.favouriteless.enchanted.common.recipes.SpinningRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class SpinningWheelRecipeProcessor implements IComponentProcessor {

	private SpinningRecipe recipe;

	@Override
	public void setup(Level level, IVariableProvider variables) {
		ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());
		RecipeManager recipeManager = level.getRecipeManager();
		recipe = (SpinningRecipe)recipeManager.byKey(recipeId).orElseThrow(() -> new IllegalArgumentException("Could not find recipe for: " + recipeId));;
	}

	@Override
	public IVariable process(Level level, String key) {
		if(key.startsWith("in")) {
			int index = Integer.parseInt(String.valueOf(key.charAt(key.length()-1)));
			NonNullList<ItemStack> stacks = recipe.getItemsIn();
			if(index >= 0 && index < stacks.size()) {
				return IVariable.from(stacks.get(index));
			}
			return IVariable.from(ItemStack.EMPTY);
		}
		else if(key.equals("out")) {
			ItemStack stack = recipe.getResultItem(level.registryAccess());

			return IVariable.from(stack);
		}

		return null;
	}
}

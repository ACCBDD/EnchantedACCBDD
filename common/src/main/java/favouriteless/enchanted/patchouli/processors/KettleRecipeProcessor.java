package favouriteless.enchanted.patchouli.processors;

import favouriteless.enchanted.common.recipes.KettleRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class KettleRecipeProcessor implements IComponentProcessor {

	private KettleRecipe recipe;

	@Override
	public void setup(Level level, IVariableProvider variables) {
		ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());
		RecipeManager recipeManager = level.getRecipeManager();
		recipe = (KettleRecipe)recipeManager.byKey(recipeId).orElseThrow(() -> new IllegalArgumentException("Could not find recipe for: " + recipeId));
	}

	@Override
	public IVariable process(Level level, String key) {

		if(key.startsWith("result"))
			return IVariable.from(recipe.getResultItem(level.registryAccess()));
		else if(key.startsWith("itemList"))
			return IVariable.from(getItemArray(recipe));
		else if(key.startsWith("power"))
			return IVariable.wrap(recipe.getPower() + " Altar Power");

		return null;
	}

	private static ItemStack[] getItemArray(KettleRecipe recipe) {
		NonNullList<ItemStack> itemStacks = recipe.getItemsIn();
		ItemStack[] itemArray = new ItemStack[itemStacks.size()];
		for(int i = 0; i < itemArray.length; i++) {
			itemArray[i] = itemStacks.get(i);
		}
		return itemArray;
	}
}

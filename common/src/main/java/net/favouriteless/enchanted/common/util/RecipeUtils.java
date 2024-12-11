package net.favouriteless.enchanted.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class RecipeUtils {

	/**
	 * Attempts to grab the result of a {@link Recipe} without registry access, using the client's level.
	 * @return The {@link ItemStack} result of recipe
	 */
	public static ItemStack getResultItem(Recipe<?> recipe) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		return recipe.getResultItem(level.registryAccess());
	}

	public static <C extends Container, T extends Recipe<C>> List<T> getRecipes(RecipeType<T> type) {
		return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(type);
	}

}
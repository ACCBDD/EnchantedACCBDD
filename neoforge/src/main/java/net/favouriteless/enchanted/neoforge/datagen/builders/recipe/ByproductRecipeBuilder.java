package net.favouriteless.enchanted.neoforge.datagen.builders.recipe;

import net.favouriteless.enchanted.common.recipes.ByproductRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class ByproductRecipeBuilder extends ERecipeBuilder {

    private final ItemStack result;
    private final Ingredient ingredient;

    private ByproductRecipeBuilder(ItemStack result, Ingredient ingredient) {
        this.result = result;
        this.ingredient = ingredient;
    }

    public static ByproductRecipeBuilder create(ItemStack result, ItemLike... itemLikes) {
        return new ByproductRecipeBuilder(result, Ingredient.of(itemLikes));
    }

    public static ByproductRecipeBuilder create(ItemStack result, ItemStack... stacks) {
        return new ByproductRecipeBuilder(result, Ingredient.of(stacks));
    }

    public static ByproductRecipeBuilder create(ItemStack result, TagKey<Item> tag) {
        return new ByproductRecipeBuilder(result, Ingredient.of(tag));
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation id) {
        output.accept(id, new ByproductRecipe(ingredient, result), null);
    }

}

package com.favouriteless.enchanted.datagen.builders.recipe;

import com.favouriteless.enchanted.common.init.registry.EnchantedRecipeTypes;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ByproductRecipeBuilder extends EnchantedRecipeBuilder {

    private ItemLike[] items = null;
    private TagKey<Item> tag = null;

    private final ItemLike result;
    private int count = 1;

    private ByproductRecipeBuilder(ItemLike result) {
        super("byproduct");
        this.result = result;
    }

    private ByproductRecipeBuilder(ItemLike result, ItemLike[] items) {
        this(result);
        this.items = items;
    }

    private ByproductRecipeBuilder(ItemLike result, TagKey<Item> tag) {
        this(result);
        this.tag = tag;
    }

    /**
     * Create a new {@link ByproductRecipeBuilder} with the specified result and ingredient.
     * @param result The {@link ItemLike} this recipe produces.
     * @param items The {@link ItemLike}s used to create the result.
     * @return A new {@link ByproductRecipeBuilder} with the specified parameters.
     */
    public static ByproductRecipeBuilder create(ItemLike result, ItemLike... items) {
        return new ByproductRecipeBuilder(result, items);
    }

    /**
     * Create a new {@link ByproductRecipeBuilder} with the specified result and ingredient.
     * @param result The {@link ItemLike} this recipe produces.
     * @param tag The tag used to create the result.
     * @return A new {@link ByproductRecipeBuilder} with the specified parameters.
     */
    public static ByproductRecipeBuilder create(ItemLike result, TagKey<Item> tag) {
        return new ByproductRecipeBuilder(result, tag);
    }

    public ByproductRecipeBuilder setCount(int count) {
        if(count < 1)
            throw new IllegalStateException("Count of byproduct recipe cannot be less than 1.");
        this.count = count;
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        return result.asItem();
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
        consumer.accept(new Result(id, tag == null ? Ingredient.of(items) : Ingredient.of(tag), result.asItem(), count));
    }

    @Override
    protected String getRecipeName() {
        String prefix = ForgeRegistries.ITEMS.getKey(result.asItem()).getPath();
        String suffix = tag != null ? tag.location().getPath() : ForgeRegistries.ITEMS.getKey(items[0].asItem()).getPath();

        return prefix + "_" + suffix;
    }

    public static class Result extends EnchantedFinishedRecipe {

        private final Ingredient ingredient;
        private final Item result;
        private final int count;

        public Result(ResourceLocation id, Ingredient ingredient, Item result, int count) {
            super(id);
            this.ingredient = ingredient;
            this.result = result;
            this.count = count;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredient", ingredient.toJson());

            JsonObject obj = new JsonObject();
            obj.addProperty("item", ForgeRegistries.ITEMS.getKey(result).toString());
            if (this.count > 1) {
                obj.addProperty("count", this.count);
            }

            json.add("result", obj);
        }

        @NotNull
        @Override
        public RecipeSerializer<?> getType() {
            return EnchantedRecipeTypes.BYPRODUCT_SERIALIZER.get();
        }

    }

}

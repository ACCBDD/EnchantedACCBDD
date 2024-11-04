package favouriteless.enchanted.datagen.builders.recipe;

import favouriteless.enchanted.common.init.registry.EnchantedRecipeTypes;
import favouriteless.enchanted.common.recipes.CauldronTypeRecipe;
import favouriteless.enchanted.util.ItemUtil;
import favouriteless.enchanted.util.JsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class CauldronTypeRecipeBuilder extends EnchantedRecipeBuilder {

    private final RecipeType<? extends CauldronTypeRecipe> type;
    private final List<ItemStack> inputs = new ArrayList<>();
    private final ItemStack result;
    private final int power;

    private final int[] cookColor = new int[] { 128, 30, 23 };
    private final int[] finalColor = new int[] { 222, 49, 29 };

    private CauldronTypeRecipeBuilder(RecipeType<? extends CauldronTypeRecipe> type, ItemStack result, int power) {
        super(ForgeRegistries.RECIPE_TYPES.getKey(type).getPath());
        this.type = type;
        this.power = power;
        this.result = result;
    }

    /**
     * Create a new {@link CauldronTypeRecipeBuilder} with the specified result and ingredients.
     *
     * @param result The {@link ItemStack}s this recipe creates.
     * @return A new {@link CauldronTypeRecipeBuilder} with the specified parameters.
     */
    public static CauldronTypeRecipeBuilder cauldron(ItemStack result, int power) {
        return new CauldronTypeRecipeBuilder(EnchantedRecipeTypes.WITCH_CAULDRON.get(), result, power);
    }

    /**
     * Create a new {@link CauldronTypeRecipeBuilder} with the specified result and ingredients.
     *
     * @param result The {@link ItemStack}s this recipe creates.
     * @return A new {@link CauldronTypeRecipeBuilder} with the specified parameters.
     */
    public static CauldronTypeRecipeBuilder kettle(ItemStack result, int power) {
        return new CauldronTypeRecipeBuilder(EnchantedRecipeTypes.KETTLE.get(), result, power);
    }

    /**
     * Adds the specified {@link ItemStack}s to this recipe's ingredients.
     *
     * @param inputs The {@link ItemStack}s this recipe takes.
     * @return The {@link CauldronTypeRecipeBuilder} inputs was called on.
     */
    public CauldronTypeRecipeBuilder inputs(ItemStack... inputs) {
        Collections.addAll(this.inputs, inputs);
        return this;
    }

    /**
     * Adds the specified {@link ItemLike}s to this recipe's ingredients.
     *
     * @param inputs The {@link ItemLike}s this recipe takes.
     * @return The {@link CauldronTypeRecipeBuilder} inputs was called on.
     */
    public CauldronTypeRecipeBuilder inputs(ItemLike... inputs) {
        for(ItemLike item : inputs)
            this.inputs.add(item.asItem().getDefaultInstance());

        return this;
    }

    /**
     * Sets the cooking color of this recipe.
     *
     * @param red The red value of the color.
     * @param green The green value of the color.
     * @param blue The blue value of the color.
     * @return The {@link CauldronTypeRecipeBuilder} cookColor was called on.
     */
    public CauldronTypeRecipeBuilder cookColor(int red, int green, int blue) {
        cookColor[0] = red;
        cookColor[1] = green;
        cookColor[2] = blue;
        return this;
    }

    /**
     * Sets the final color of this recipe.
     *
     * @param red The red value of the color.
     * @param green The green value of the color.
     * @param blue The blue value of the color.
     * @return The {@link CauldronTypeRecipeBuilder} finalColor was called on.
     */
    public CauldronTypeRecipeBuilder finalColor(int red, int green, int blue) {
        finalColor[0] = red;
        finalColor[1] = green;
        finalColor[2] = blue;
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
        consumer.accept(new Result(type, id, inputs, result, power, cookColor, finalColor));
    }

    @Override
    protected String getRecipeName() {
        return ForgeRegistries.ITEMS.getKey(result.getItem()).getPath();
    }

    public static class Result extends EnchantedFinishedRecipe {

        private final RecipeType<? extends CauldronTypeRecipe> type;
        private final List<ItemStack> inputs;
        private final ItemStack result;
        private final int power;
        private final int[] cookColor;
        private final int[] finalColor;

        public Result(RecipeType<? extends CauldronTypeRecipe> type, ResourceLocation id, List<ItemStack> inputs,
                      ItemStack result, int power, int[] cookColor, int[] finalColor) {
            super(id);
            this.type = type;
            this.inputs = inputs;
            this.result = result;
            this.power = power;
            this.cookColor = cookColor;
            this.finalColor = finalColor;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientsArray = new JsonArray();
            for(ItemStack item : inputs)
                ingredientsArray.add(ItemUtil.asJson(item, true));

            json.add("ingredients", ingredientsArray);
            json.add("result", ItemUtil.asJson(result, true));
            json.addProperty("power", power);
            json.add("cookingColor", JsonHelper.rgbAsJson(cookColor));
            json.add("finalColor", JsonHelper.rgbAsJson(finalColor));
        }

        @NotNull
        @Override
        public RecipeSerializer<?> getType() {
            return type == EnchantedRecipeTypes.WITCH_CAULDRON.get() ? EnchantedRecipeTypes.WITCH_CAULDRON_SERIALIZER.get() : EnchantedRecipeTypes.KETTLE_SERIALIZER.get();
        }

    }

}

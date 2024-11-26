package favouriteless.enchanted.datagen.builders.recipe;

import favouriteless.enchanted.common.init.registry.EItems;
import favouriteless.enchanted.common.init.registry.ERecipeTypes;
import favouriteless.enchanted.util.ItemStackHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DistillingRecipeBuilder extends EnchantedRecipeBuilder {

    private final ItemStack[] inputs;
    private final List<ItemStack> results = new ArrayList<>();
    private int cookTime;
    private int power;

    private DistillingRecipeBuilder(ItemStack[] inputs) {
        super("distilling");
        this.inputs = inputs;
    }

    /**
     * Create a new {@link DistillingRecipeBuilder} with the specified ingredients.
     * @param inputs The {@link ItemLike}s this recipe takes.
     * @return A new {@link DistillingRecipeBuilder} with the specified parameters.
     */
    public static DistillingRecipeBuilder create(ItemStack... inputs) {
        if(inputs.length > 3)
            throw new IllegalStateException("Distillery recipes cannot have more than 3 inputs.");
        return new DistillingRecipeBuilder(inputs);
    }

    /**
     * Create a new {@link DistillingRecipeBuilder} with the specified ingredients.
     * @param inputs The {@link ItemLike}s this recipe takes.
     * @return A new {@link DistillingRecipeBuilder} with the specified parameters.
     */
    public static DistillingRecipeBuilder create(ItemLike... inputs) {
        if(inputs.length > 3)
            throw new IllegalStateException("Distillery recipes cannot have more than 3 inputs.");

        ItemStack[] stacks = new ItemStack[inputs.length];
        for(int i = 0; i < inputs.length; i++)
            stacks[i] = new ItemStack(inputs[i]);

        return new DistillingRecipeBuilder(stacks);
    }

    /**
     * Adds {@link ItemStack}s to the result pool for this recipe.
     * @param results The {@link ItemStack}s to add.
     * @return The {@link DistillingRecipeBuilder} results was called on.
     */
    public DistillingRecipeBuilder results(ItemStack... results) {
        Collections.addAll(this.results, results);
        if(this.results.size() > 4)
            throw new IllegalStateException("Distillery recipes cannot have more than 4 results.");
        return this;
    }

    /**
     * Adds {@link ItemStack}s to the result pool for this recipe.
     * @param results The {@link ItemStack}s to add.
     * @return The {@link DistillingRecipeBuilder} results was called on.
     */
    public DistillingRecipeBuilder results(ItemLike... results) {
        ItemStack[] stacks = new ItemStack[results.length];
        for(int i = 0; i < results.length; i++)
            stacks[i] = new ItemStack(results[i]);

        Collections.addAll(this.results, stacks);
        if(this.results.size() > 4)
            throw new IllegalStateException("Distillery recipes cannot have more than 4 results.");
        return this;
    }

    /**
     * Sets the total cooking duration for the recipe.
     * @param cookTime The cookTime for the recipe.
     * @return The {@link DistillingRecipeBuilder} results was called on.
     */
    public DistillingRecipeBuilder cookTime(int cookTime) {
        if(cookTime < 1)
            throw new IllegalArgumentException("Distillery recipes cannot have a cookTime smaller than 1.");
        this.cookTime = cookTime;
        return this;
    }

    public DistillingRecipeBuilder power(int power) {
        if(power < 0)
            throw new IllegalArgumentException("Distillery recipes cannot have a power smaller than 0.");
        this.power = power;
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        return results.get(0).getItem();
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
        consumer.accept(new Result(id, inputs, results.toArray(ItemStack[]::new), cookTime, power));
    }

    @Override
    protected String getRecipeName() {
        StringBuilder name = new StringBuilder();
        for(ItemStack item : inputs)
            if(item.getItem() != EItems.CLAY_JAR.get())
                name.append(ForgeRegistries.ITEMS.getKey(item.getItem()).getPath()).append("_");
        name.deleteCharAt(name.length() - 1); // Delete trailing _

        return name.toString();
    }

    public static class Result extends EnchantedFinishedRecipe {

        private final ItemStack[] inputs;
        private final ItemStack[] results;
        private final int cookTime;
        private final int power;

        public Result(ResourceLocation id, ItemStack[] inputs, ItemStack[] results, int cookTime, int power) {
            super(id);
            this.inputs = inputs;
            this.results = results;
            this.cookTime = cookTime;
            this.power = power;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientsArray = new JsonArray();
            for(ItemStack item : inputs)
                ingredientsArray.add(ItemStackHelper.asJson(item, true));

            JsonArray resultsArray = new JsonArray();
            for(ItemStack item : results)
                resultsArray.add(ItemStackHelper.asJson(item, true));

            json.add("ingredients", ingredientsArray);
            json.add("results", resultsArray);

            json.addProperty("cookTime", cookTime);
            json.addProperty("power", power);
        }

        @NotNull
        @Override
        public RecipeSerializer<?> getType() {
            return ERecipeTypes.DISTILLING_SERIALIZER.get();
        }

    }

}

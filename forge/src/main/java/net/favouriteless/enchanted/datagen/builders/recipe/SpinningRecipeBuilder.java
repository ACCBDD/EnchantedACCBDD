package net.favouriteless.enchanted.datagen.builders.recipe;

import net.favouriteless.enchanted.common.init.registry.ERecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.favouriteless.enchanted.common.util.ItemUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SpinningRecipeBuilder extends EnchantedRecipeBuilder {

    private final ItemStack[] items;
    private final ItemStack result;
    private final int power;
    private final int duration;

    private SpinningRecipeBuilder(ItemLike result, int power, ItemStack[] items, int duration) {
        super("spinning");
        this.result = new ItemStack(result.asItem());
        this.items = items;
        this.power = power;
        this.duration = duration;

        if(items.length > 3)
            throw new IllegalArgumentException("Tried to create spinning recipe with more than 3 inputs.");

        if(duration < 1)
            throw new IllegalArgumentException("Tried to create spinning recipe with duration less than 1.");
    }

    /**
     * Create a new {@link SpinningRecipeBuilder} with the specified result and ingredients.
     * @param result The {@link ItemLike} this recipe produces.
     * @return A new {@link SpinningRecipeBuilder} with the specified parameters.
     */
    public static SpinningRecipeBuilder create(ItemLike result, int power, int duration, ItemLike... items)  {
        ItemStack[] itemsOut = new ItemStack[items.length];
        for(int i = 0; i < items.length; i++)
            itemsOut[i] = items[i].asItem().getDefaultInstance();

        return new SpinningRecipeBuilder(result, power, itemsOut, duration);
    }

    /**
     * Create a new {@link SpinningRecipeBuilder} with the specified result and ingredients.
     * @param result The {@link ItemLike} this recipe produces.
     * @return A new {@link SpinningRecipeBuilder} with the specified parameters.
     */
    public static SpinningRecipeBuilder create(ItemLike result, int power, int duration, ItemStack... items) {
        return new SpinningRecipeBuilder(result, power, items, duration);
    }

    /**
     * Sets the count of the output {@link ItemStack}.
     * @param count The count of the output.
     */
    public SpinningRecipeBuilder countOut(int count) {
        if(count < 1)
            throw new IllegalStateException("Count of byproduct recipe cannot be less than 1.");
        result.setCount(count);
        return this;
    }

    /**
     * Sets the {@link CompoundTag} of an input {@link ItemStack}.
     * @param index The index of the output.
     * @param nbt The {@link CompoundTag} of the input.
     */
    public SpinningRecipeBuilder tagIn(int index, CompoundTag nbt) {
        if(index > 0 && index < items.length)
            items[index].setTag(nbt);
        return this;
    }

    /**
     * Sets the {@link CompoundTag} of the output {@link ItemStack}.
     * @param nbt The {@link CompoundTag} of the output.
     */
    public SpinningRecipeBuilder tagOut(CompoundTag nbt) {
        result.setTag(nbt);
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation id) {
        consumer.accept(new Result(id, items, result, power, duration));
    }

    @Override
    protected String getRecipeName() {
        return ForgeRegistries.ITEMS.getKey(result.getItem()).getPath();
    }

    public static class Result extends EnchantedFinishedRecipe {

        private final ItemStack[] items;
        private final ItemStack result;
        private final int power;
        private final int duration;

        public Result(ResourceLocation id, ItemStack[] items, ItemStack result, int power, int duration) {
            super(id);
            this.items = items;
            this.result = result;
            this.power = power;
            this.duration = duration;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientsArray = new JsonArray();
            for(ItemStack item : items)
                ingredientsArray.add(ItemUtils.asJson(item, true));

            json.add("ingredients", ingredientsArray);
            json.add("result", ItemUtils.asJson(result, true));
            json.addProperty("power", power);
            json.addProperty("duration", duration);
        }

        @NotNull
        @Override
        public RecipeSerializer<?> getType() {
            return ERecipeTypes.SPINNING_SERIALIZER.get();
        }

    }

}

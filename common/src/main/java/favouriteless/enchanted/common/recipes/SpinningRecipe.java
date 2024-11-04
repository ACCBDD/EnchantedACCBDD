package favouriteless.enchanted.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.common.init.registry.EnchantedRecipeTypes;
import favouriteless.enchanted.common.recipes.recipe_inputs.ListInput;
import favouriteless.enchanted.util.ItemUtil;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpinningRecipe implements Recipe<ListInput> {

    public static final MapCodec<SpinningRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group (
            ItemStack.CODEC.listOf(3, 3).fieldOf("ingredients").forGetter(recipe -> recipe.inputs),
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
            Codec.INT.optionalFieldOf("power", 0).forGetter(recipe -> recipe.power)
    ).apply(instance, SpinningRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SpinningRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStack.LIST_STREAM_CODEC, recipe -> recipe.inputs,
            ItemStack.STREAM_CODEC, recipe -> recipe.result,
            ByteBufCodecs.INT, recipe -> recipe.power,
            SpinningRecipe::new
    );

    protected final List<ItemStack> inputs;
    protected final ItemStack result;
    protected final int power;

    public SpinningRecipe(List<ItemStack> inputs, ItemStack result, int power) {
        this.inputs = inputs;
        this.result = result;
        this.power = power;
    }

    @Override
    public boolean matches(ListInput input, Level level) {
        if(input.size() != 3)
            return false;

        if(!ItemUtil.isSameItemPartial(input.getItem(0), inputs.getFirst()) ||
                input.getItem(0).getCount() < inputs.getFirst().getCount()) // If "main" input does not match
            return false;

        for(int i = 1; i < inputs.size(); i++) {
            ItemStack neededCopy = inputs.get(i).copy();

            if(ItemUtil.isSameItemPartial(input.getItem(1), neededCopy))
                neededCopy.shrink(input.getItem(1).getCount());
            if(ItemUtil.isSameItemPartial(input.getItem(2), neededCopy))
                neededCopy.shrink(input.getItem(2).getCount());

            if(!neededCopy.isEmpty()) // If not empty then there was not enough of this item
                return false;
        }
        return false;
    }

    @Override
    public ItemStack assemble(ListInput input, Provider registries) {
        return result.copy();
    }

    @Override
    public ItemStack getResultItem(Provider registries) {
        return result;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EnchantedRecipeTypes.SPINNING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return EnchantedRecipeTypes.SPINNING.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public int getPower() {
        return power;
    }

}

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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class DistillingRecipe implements Recipe<ListInput> {

    public static final MapCodec<DistillingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.sizeLimitedListOf(3).fieldOf("ingredients").forGetter(recipe -> recipe.inputs),
            ItemStack.CODEC.sizeLimitedListOf(4).fieldOf("results").forGetter(recipe -> recipe.outputs),
            Codec.INT.optionalFieldOf("power", 750).forGetter(recipe -> recipe.power),
            Codec.INT.optionalFieldOf("cookTime", 300).forGetter(recipe -> recipe.cookTime)
    ).apply(instance, DistillingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DistillingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStack.LIST_STREAM_CODEC, recipe -> recipe.inputs,
            ItemStack.LIST_STREAM_CODEC, recipe -> recipe.outputs,
            ByteBufCodecs.INT, recipe -> recipe.power,
            ByteBufCodecs.INT, recipe -> recipe.cookTime,
            DistillingRecipe::new
    );

    protected final List<ItemStack> inputs;
    protected final List<ItemStack> outputs;
    protected final int cookTime;
    protected final int power;

    public DistillingRecipe(List<ItemStack> inputs, List<ItemStack> outputs,
                            int cookTime, int power) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.cookTime = cookTime;
        this.power = power;
    }

    public List<ItemStack> getOutputs() {
        List<ItemStack> out = new ArrayList<>();
        for(ItemStack stack : outputs)
            out.add(stack.copy());
        return out;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public int getCookTime() {
        return cookTime;
    }

    @Override
    public boolean matches(ListInput inv, Level level) {
        int requiredItems = getInputs().size();

        for(ItemStack stack : getInputs()) {
            for(int i = 0; i < 3; i++) {
                ItemStack item = inv.getItem(i);
                if(ItemUtil.isSameItemPartial(item, stack) && item.getCount() >= stack.getCount()) {
                    requiredItems--;
                    break;
                }
            }
        }
        return requiredItems == 0;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Deprecated
    @Override
    public ItemStack assemble(ListInput input, Provider registries) {
        return ItemStack.EMPTY;
    }

    @Deprecated
    @Override
    public ItemStack getResultItem(Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EnchantedRecipeTypes.DISTILLING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return EnchantedRecipeTypes.DISTILLING.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public int getPower() {
        return power;
    }

}

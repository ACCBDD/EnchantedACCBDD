package favouriteless.enchanted.common.recipes;

import favouriteless.enchanted.common.init.registry.EnchantedRecipeTypes;
import favouriteless.enchanted.util.ItemStackHelper;
import favouriteless.enchanted.util.JsonHelper;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class SpinningRecipe implements Recipe<Container> {

    protected final RecipeType<?> type;
    protected final ResourceLocation id;

    protected final NonNullList<ItemStack> itemsIn;
    protected final ItemStack result;
    protected final int power;

    public SpinningRecipe(ResourceLocation id, NonNullList<ItemStack> itemsIn, ItemStack result, int power) {
        this.type = EnchantedRecipeTypes.SPINNING.get();
        this.id = id;
        this.itemsIn = itemsIn;
        this.result = result;
        this.power = power;
    }

    @Override
    public boolean matches(Container inv, Level level) {
        ItemStack mainIn = inv.getItem(0);
        if(!ItemStack.isSameItem(mainIn, itemsIn.get(0)) || mainIn.getCount() < itemsIn.get(0).getCount()) // If "main" input does not match
            return false;

        for(int i = 1; i < itemsIn.size(); i++) {
            ItemStack itemNeeded = itemsIn.get(i).copy();

            if(ItemStack.isSameItemSameTags(itemNeeded, inv.getItem(1)))
                itemNeeded.shrink(inv.getItem(1).getCount());
            if(ItemStack.isSameItemSameTags(itemNeeded, inv.getItem(2)))
                itemNeeded.shrink(inv.getItem(2).getCount());

            if(!itemNeeded.isEmpty()) // If not empty then there was not enough of this item
                return false;
        }

        return true;
    }


    @Override
    public ItemStack assemble(Container inv, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 4;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EnchantedRecipeTypes.SPINNING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * @return A {@link NonNullList} containing copies of this recipe's inputs.
     */
    public NonNullList<ItemStack> getItemsIn() {
        NonNullList<ItemStack> in = NonNullList.withSize(itemsIn.size(), ItemStack.EMPTY);
        for(int i = 0; i < itemsIn.size(); i++)
            in.set(i, itemsIn.get(i).copy());
        return in;
    }


    public int getPower() {
        return power;
    }

    public static class Serializer implements RecipeSerializer<SpinningRecipe> {

        @Override
        public SpinningRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            NonNullList<ItemStack> itemsIn = JsonHelper.readItemStackList(GsonHelper.getAsJsonArray(json, "ingredients"), true);
            ItemStack result = ItemStackHelper.fromJson(GsonHelper.getAsJsonObject(json, "result"), true);
            int power = GsonHelper.getAsInt(json, "power", 0);

            return new SpinningRecipe(recipeId, itemsIn, result, power);
        }

        @Override
        public SpinningRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int ingredientSize = buffer.readInt();
            NonNullList<ItemStack> itemsIn = NonNullList.create();
            for(int i = 0; i < ingredientSize; i++) {
                itemsIn.add(buffer.readItem());
            }

            ItemStack result = buffer.readItem();
            int power = buffer.readInt();

            return new SpinningRecipe(recipeId, itemsIn, result, power);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SpinningRecipe recipe) {
            buffer.writeInt(recipe.itemsIn.size());
            recipe.itemsIn.forEach(buffer::writeItem);
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.getPower());
        }

    }

}

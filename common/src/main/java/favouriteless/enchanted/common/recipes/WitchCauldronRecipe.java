package favouriteless.enchanted.common.recipes;

import favouriteless.enchanted.common.init.registry.EnchantedRecipeTypes;
import favouriteless.enchanted.util.ItemStackHelper;
import favouriteless.enchanted.util.JsonHelper;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;

public class WitchCauldronRecipe extends CauldronTypeRecipe {

    public WitchCauldronRecipe(ResourceLocation id, NonNullList<ItemStack> itemsIn, ItemStack itemOut, int power, int[] cookingColour, int[] finalColour) {
        super(EnchantedRecipeTypes.WITCH_CAULDRON.get(), id, itemsIn, itemOut, power, cookingColour, finalColour);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EnchantedRecipeTypes.WITCH_CAULDRON_SERIALIZER.get();
    }



    public static class Serializer implements RecipeSerializer<WitchCauldronRecipe> {

        @Override
        public WitchCauldronRecipe fromJson(ResourceLocation id, JsonObject json) {

            NonNullList<ItemStack> itemsIn = JsonHelper.readItemStackList(GsonHelper.getAsJsonArray(json, "ingredients"), true);
            ItemStack itemOut = ItemStackHelper.fromJson(GsonHelper.getAsJsonObject(json, "result"), true);
            int power = GsonHelper.getAsInt(json, "power");
            int[] cookingColour = JsonHelper.readRgb(GsonHelper.getAsJsonArray(json, "cookingColor"));
            int[] finalColour = JsonHelper.readRgb(GsonHelper.getAsJsonArray(json, "finalColor"));

            return new WitchCauldronRecipe(id, itemsIn, itemOut, power, cookingColour, finalColour);
        }

        @Nullable
        @Override
        public WitchCauldronRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {

            int inSize = buffer.readInt();
            NonNullList<ItemStack> itemsIn = NonNullList.create();
            for (int x = 0; x < inSize; ++x) {
                itemsIn.add(buffer.readItem());
            }
            ItemStack itemOut = buffer.readItem();
            int power = buffer.readInt();
            int[] cookingColour = new int[] {(int)buffer.readShort(), (int)buffer.readShort(), (int)buffer.readShort() };
            int[] finalColour = new int[] {(int)buffer.readShort(), (int)buffer.readShort(), (int)buffer.readShort() };

            return new WitchCauldronRecipe(id, itemsIn, itemOut, power, cookingColour, finalColour);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, WitchCauldronRecipe recipe) {
            buffer.writeInt(recipe.itemsIn.size());
            for (ItemStack item : recipe.itemsIn) {
                buffer.writeItem(item);
            }
            buffer.writeItem(recipe.itemOut);
            buffer.writeInt(recipe.getPower());
            buffer.writeShort(recipe.getCookRed());
            buffer.writeShort(recipe.getCookGreen());
            buffer.writeShort(recipe.getCookBlue());
            buffer.writeShort(recipe.getFinalRed());
            buffer.writeShort(recipe.getFinalGreen());
            buffer.writeShort(recipe.getFinalBlue());

        }

    }
}

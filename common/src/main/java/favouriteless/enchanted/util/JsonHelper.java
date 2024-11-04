package favouriteless.enchanted.util;

import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;

public class JsonHelper {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static NonNullList<ItemStack> readItemStackList(JsonArray array, boolean readNbt) {
        NonNullList<ItemStack> out = NonNullList.create();
        for (int i = 0; i < array.size(); ++i)
            out.add(ItemUtil.fromJson(array.get(i).getAsJsonObject(), readNbt));

        return out;
    }

    public static NonNullList<Ingredient> readIngredientList(JsonArray array) {
        NonNullList<Ingredient> out = NonNullList.create();
        for (int i = 0; i < array.size(); ++i)
            out.add(Ingredient.fromJson(array.get(i)));

        return out;
    }

    public static NonNullList<Item> readItemList(JsonArray array) {
        NonNullList<Item> out = NonNullList.create();
        for (int i = 0; i < array.size(); ++i)
            out.add(ItemUtil.fromJson(array.get(i).getAsJsonObject(), false).getItem());

        return out;
    }

    public static int[] readRgb(JsonArray json) {
        if(json.size() != 3)
            throw new JsonParseException("Invalid colour in json");

        return new int[] { json.get(0).getAsInt(), json.get(1).getAsInt(), json.get(2).getAsInt() };
    }

    public static JsonArray rgbAsJson(int red, int green, int blue) {
        JsonArray out = new JsonArray();
        out.add(red);
        out.add(green);
        out.add(blue);
        return out;
    }

    public static JsonArray rgbAsJson(int[] rgb) {
        JsonArray out = new JsonArray();
        out.add(rgb[0]);
        out.add(rgb[1]);
        out.add(rgb[2]);
        return out;
    }

    public static CompoundTag readTag(JsonElement element) {
        try {
            if (element.isJsonObject())
                return TagParser.parseTag(GSON.toJson(element));
            else
                return TagParser.parseTag(GsonHelper.convertToString(element, "nbt"));
        }
        catch (CommandSyntaxException exception) {
            throw new JsonSyntaxException("Invalid NBT Entry: " + exception);
        }
    }

}
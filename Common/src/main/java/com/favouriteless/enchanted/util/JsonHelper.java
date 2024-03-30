package com.favouriteless.enchanted.util;

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
            out.add(ItemStackHelper.fromJson(array.get(i).getAsJsonObject(), readNbt));

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
            out.add(ItemStackHelper.fromJson(array.get(i).getAsJsonObject(), false).getItem());

        return out;
    }

    public static int[] readRGB(JsonObject json) {
        if(json.has("red") && json.has("green") && json.has("blue")) {
            int red = GsonHelper.getAsInt(json, "red");
            int green = GsonHelper.getAsInt(json, "green");
            int blue = GsonHelper.getAsInt(json, "blue");

            return new int[] { red, green, blue };
        } else {
            throw new JsonParseException("Invalid colour in json");
        }
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
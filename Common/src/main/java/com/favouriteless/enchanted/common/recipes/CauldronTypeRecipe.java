package com.favouriteless.enchanted.common.recipes;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public abstract class CauldronTypeRecipe implements Recipe<Container> {

    protected final RecipeType<?> type;
    protected final ResourceLocation id;

    private final NonNullList<ItemStack> itemsIn;
    private final ItemStack itemOut;
    private final int power;

    private int[] cookColor;
    private int[] finalColor;

    public CauldronTypeRecipe(RecipeType<?> type, ResourceLocation id, NonNullList<ItemStack> itemsIn, ItemStack itemOut, int power, int[] cookColor, int[] finalColor) {
        this.type = type;
        this.id = id;

        this.itemsIn = itemsIn;
        this.itemOut = itemOut;
        this.power = power;

        this.cookColor = cookColor;
        this.finalColor = finalColor;
    }

    /**
     * Returns true if inventory is a partial match for this recipe
     * @param inventory
     * @param world
     * @return isMatch
     */
    @Override
    public boolean matches(Container inventory, Level world) {
        if(inventory.isEmpty() || inventory.getContainerSize() > itemsIn.size())
            return false; // Too many items

        for(int i = 0; i < itemsIn.size() && i < inventory.getContainerSize(); i++) {
            ItemStack itemIn = itemsIn.get(i);
            ItemStack inventoryItem = inventory.getItem(i);

            if(!ItemStack.matches(itemIn, inventoryItem))
                return false;
        }
        return true;
    }

    /**
     * Returns true if inventory is a full match to this recipe
     * @param inventory
     * @param world
     * @return isMatch
     */
    public boolean fullMatch(Container inventory) {
        if(inventory.getContainerSize() != itemsIn.size()) // Same number of items
            return false;

        for(int i = 0; i < itemsIn.size(); i++) {
            ItemStack itemIn = itemsIn.get(i);
            ItemStack inventoryItem = inventory.getItem(i);
            if(!ItemStack.matches(itemIn, inventoryItem))
                return false;
        }

        return true;
    }

    public NonNullList<ItemStack> getItemsIn() {
        return itemsIn;
    }

    public int getPower() {
        return power;
    }

    public int getCookRed() {
        return cookColor[0];
    }

    public int getCookGreen() {
        return cookColor[1];
    }

    public int getCookBlue() {
        return cookColor[2];
    }

    public int getFinalRed() {
        return finalColor[0];
    }

    public int getFinalGreen() {
        return finalColor[1];
    }

    public int getFinalBlue() {
        return finalColor[2];
    }

    @Override
    public ItemStack getResultItem() {
        return itemOut.copy();
    }

    @Override
    public ItemStack assemble(Container inv) {
        return itemOut.copy();
    }

    @Override
    public boolean canCraftInDimensions(int wdth, int height) {
        return false;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeType<?> getType() {
        return type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}

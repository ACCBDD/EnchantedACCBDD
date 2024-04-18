package com.favouriteless.enchanted.common.jei.categories;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import com.favouriteless.enchanted.common.recipes.SpinningRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SpinningWheelCategory implements IRecipeCategory<SpinningRecipe> {

    private final RecipeType<SpinningRecipe> type;
    private final IJeiHelpers jeiHelpers;

    private final int GUI_WIDTH = 140;

    private final IDrawableAnimated leftArrow;
    private final IDrawableAnimated rightArrow;
    private final IDrawableStatic background;

    public SpinningWheelCategory(IJeiHelpers jeiHelpers, RecipeType<SpinningRecipe> recipeTypeSpinningWheel) {
        this.jeiHelpers = jeiHelpers;
        this.type = recipeTypeSpinningWheel;
        IDrawableStatic leftArrow = jeiHelpers.getGuiHelper().createDrawable(Enchanted.location("textures/gui/spinning_wheel.png"), 176, 0, 15, 20);
        this.leftArrow = jeiHelpers.getGuiHelper().createAnimatedDrawable(leftArrow, 120, IDrawableAnimated.StartDirection.BOTTOM, false);
        IDrawableStatic rightArrow = jeiHelpers.getGuiHelper().createDrawable(Enchanted.location("textures/gui/spinning_wheel.png"), 176, 20, 15, 20);
        this.rightArrow = jeiHelpers.getGuiHelper().createAnimatedDrawable(rightArrow, 120, IDrawableAnimated.StartDirection.BOTTOM, false);
        this.background = jeiHelpers.getGuiHelper().createDrawable(Enchanted.location("textures/gui/spinning_wheel.png"), 20, 10, 140, 60);
    }

    @Override
    public void draw(SpinningRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        this.background.draw(stack);
        this.leftArrow.draw(stack,7,16);
        this.rightArrow.draw(stack,44,16);
        drawPowerCost(Minecraft.getInstance(),stack,"Required Altar Power : "+recipe.getPower(),0xFFFFFFFF);
    }

    private void drawPowerCost(Minecraft minecraft, PoseStack poseStack, String text, int mainColor) {
        int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
        int width = minecraft.font.width(text);
        int x = GUI_WIDTH/2 - width/2 - 1;
        int y = 65;

        minecraft.font.draw(poseStack, text, x + 1, y, shadowColor);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.enchanted.spinning_wheel");
    }

    @Override
    public IDrawable getBackground() {
        return jeiHelpers.getGuiHelper().createBlankDrawable(GUI_WIDTH,80);
    }

    @Override
    public IDrawable getIcon() {
        return jeiHelpers.getGuiHelper().createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EnchantedItems.SPINNING_WHEEL.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SpinningRecipe recipe, IFocusGroup focuses) {
        List<ItemStack> inputs = recipe.getItemsIn();
        int[][] positions = new int[][]{{25, 13}, {13, 37}, {37, 37}};
        int pos = 0;
        for (ItemStack i : inputs) {
            builder.addSlot(RecipeIngredientRole.INPUT, positions[pos][0], positions[pos][1]).addIngredient(VanillaTypes.ITEM_STACK, i);
            pos++;
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 25).addIngredient(VanillaTypes.ITEM_STACK, recipe.getResultItem());
    }

    @Override
    public RecipeType<SpinningRecipe> getRecipeType() {
        return type;
    }

}
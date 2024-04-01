package com.favouriteless.enchanted.common.jei.categories;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.jei.recipes.JEIMutandisRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class MutandisCategory implements IRecipeCategory<JEIMutandisRecipe> {

    private final IJeiHelpers jeiHelpers;
    private final RecipeType<JEIMutandisRecipe> type;
    private final ItemStack icon;
    private final Component title;

    public MutandisCategory(IJeiHelpers jeiHelpers, RecipeType<JEIMutandisRecipe> type, ItemStack icon, Component title) {
        this.jeiHelpers = jeiHelpers;
        this.type = type;
        this.icon=icon;
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, JEIMutandisRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,27,35).addItemStacks(recipe.getInputs());
        builder.addSlot(RecipeIngredientRole.OUTPUT,76,35).addItemStack(recipe.getOutput());
    }

    @Override
    public IDrawable getBackground() {
        return jeiHelpers.getGuiHelper().createDrawable(Enchanted.location("textures/gui/jei/mutandis.png"), 0, 0, 120, 68);
    }

    @Override
    public void draw(JEIMutandisRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        drawText(Minecraft.getInstance(),stack,recipe.getDescription(),120,10,0xFFFFFF);
    }

    private void drawText(Minecraft minecraft, PoseStack poseStack, String text, int x, int y, int mainColor) {
        int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
        int width = minecraft.font.width(text);
        if(width>150){
            drawText(minecraft,poseStack,text.substring(0,text.length()/2),x,y,mainColor);
            drawText(minecraft,poseStack,text.substring(text.length()/2,text.length()),x,y+10,mainColor);
            return;
        }
        int cx = x/2 - width/2 - 1;
        minecraft.font.draw(poseStack, text, cx + 1, y, shadowColor);
    }

    @Override
    public IDrawable getIcon() {
        return this.jeiHelpers.getGuiHelper().createDrawableIngredient(VanillaTypes.ITEM_STACK, this.icon);
    }

    @Override
    public RecipeType<JEIMutandisRecipe> getRecipeType() {
        return type;
    }

}
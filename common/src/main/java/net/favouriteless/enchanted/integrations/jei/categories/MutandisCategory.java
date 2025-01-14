package net.favouriteless.enchanted.integrations.jei.categories;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.integrations.jei.recipes.JeiMutandisRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MutandisCategory implements IRecipeCategory<JeiMutandisRecipe> {

    private final IGuiHelper guiHelper;
    private final RecipeType<JeiMutandisRecipe> type;
    private final Item icon;
    private final Component title;

    public MutandisCategory(IGuiHelper guiHelper, RecipeType<JeiMutandisRecipe> type, Item icon, Component title) {
        this.guiHelper = guiHelper;
        this.type = type;
        this.icon = icon;
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, JeiMutandisRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,27,35).addItemStacks(recipe.getInputs());
        builder.addSlot(RecipeIngredientRole.OUTPUT,76,35).addItemStack(recipe.getOutput());
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(Enchanted.id("textures/gui/jei/mutandis.png"), 0, 0, 120, 68);
    }

    @Override
    public void draw(JeiMutandisRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
        drawText(Minecraft.getInstance(), gui, recipe.getDescription(), 120, 10, 0xFFFFFF);
    }

    private void drawText(Minecraft minecraft, GuiGraphics gui, String text, int x, int y, int mainColor) {
        int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
        int width = minecraft.font.width(text);
        if(width>150){
            drawText(minecraft, gui,text.substring(0,text.length()/2),x,y,mainColor);
            drawText(minecraft, gui,text.substring(text.length()/2,text.length()),x,y+10,mainColor);
            return;
        }
        int cx = x/2 - width/2 - 1;
        gui.drawString(minecraft.font, text, cx + 1, y, shadowColor, false);
    }

    @Override
    public IDrawable getIcon() {
        return this.guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(icon));
    }

    @Override
    public RecipeType<JeiMutandisRecipe> getRecipeType() {
        return type;
    }

}
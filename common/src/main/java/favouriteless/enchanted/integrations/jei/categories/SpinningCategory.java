package favouriteless.enchanted.integrations.jei.categories;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.registry.EItems;
import favouriteless.enchanted.common.recipes.SpinningRecipe;
import favouriteless.enchanted.integrations.jei.EJeiRecipeTypes;
import favouriteless.enchanted.util.RecipeUtils;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SpinningCategory implements IRecipeCategory<SpinningRecipe> {

    private final IGuiHelper guiHelper;

    private final int GUI_WIDTH = 140;

    private final IDrawableAnimated leftArrow;
    private final IDrawableAnimated rightArrow;
    private final IDrawableStatic background;

    public SpinningCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        IDrawableStatic leftArrow = guiHelper.createDrawable(Enchanted.id("textures/gui/spinning_wheel.png"), 176, 0, 15, 20);
        this.leftArrow = guiHelper.createAnimatedDrawable(leftArrow, 120, IDrawableAnimated.StartDirection.BOTTOM, false);
        IDrawableStatic rightArrow = guiHelper.createDrawable(Enchanted.id("textures/gui/spinning_wheel.png"), 176, 20, 15, 20);
        this.rightArrow = guiHelper.createAnimatedDrawable(rightArrow, 120, IDrawableAnimated.StartDirection.BOTTOM, false);
        this.background = guiHelper.createDrawable(Enchanted.id("textures/gui/spinning_wheel.png"), 20, 10, 140, 60);
    }

    @Override
    public void draw(SpinningRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
        this.background.draw(gui);
        this.leftArrow.draw(gui,7,16);
        this.rightArrow.draw(gui,44,16);
        drawPowerCost(Minecraft.getInstance(), gui,"Required Altar Power : "+recipe.getPower(),0xFFFFFFFF);
    }

    private void drawPowerCost(Minecraft minecraft, GuiGraphics gui, String text, int mainColor) {
        int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
        int width = minecraft.font.width(text);
        int x = GUI_WIDTH/2 - width/2 - 1;
        int y = 65;

        gui.drawString(minecraft.font, text, x + 1, y, shadowColor, false);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.enchanted.spinning_wheel");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createBlankDrawable(GUI_WIDTH,80);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EItems.SPINNING_WHEEL.get()));
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
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 25).addIngredient(VanillaTypes.ITEM_STACK, RecipeUtils.getResultItem(recipe));
    }

    @Override
    public RecipeType<SpinningRecipe> getRecipeType() {
        return EJeiRecipeTypes.SPINNING;
    }

}
package favouriteless.enchanted.integrations.jei.categories;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.registry.EItems;
import favouriteless.enchanted.common.recipes.WitchCauldronRecipe;
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

public class CauldronCategory implements IRecipeCategory<WitchCauldronRecipe> {

    private final IGuiHelper guiHelper;

    private final int GUI_WIDTH = 140;
    private final IDrawableAnimated arrow;

    public CauldronCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;

        IDrawableStatic arrow = guiHelper.createDrawable(Enchanted.id("textures/gui/witch_oven.png"), 176, 14, 24, 17);
        this.arrow = guiHelper.createAnimatedDrawable(arrow, 120, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(WitchCauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
        this.arrow.draw(gui, 85, 29);
        drawPowerCost(Minecraft.getInstance(), gui, "Required Altar Power : " + recipe.getPower(), 0xFFFFFFFF);
    }

    private void drawPowerCost(Minecraft minecraft, GuiGraphics gui, String text, int mainColor) {
        int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
        int width = minecraft.font.width(text);
        int x = GUI_WIDTH/2 - width/2 - 1;
        int y = 55;

        gui.drawString(minecraft.font, text, x + 1, y, shadowColor, false);
    }


    @Override
    public Component getTitle() {
        return Component.translatable("jei.enchanted.witch_cauldron");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(Enchanted.id("textures/gui/jei/witch_cauldron.png"), 4, 4, GUI_WIDTH, 70);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WitchCauldronRecipe recipe, IFocusGroup focuses) {
        int offset = 0;
        for(ItemStack i : recipe.getItemsIn()){
            builder.addSlot(RecipeIngredientRole.INPUT,5+offset,5).addIngredient(VanillaTypes.ITEM_STACK,i);
            offset += 20;
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 30).addIngredient(VanillaTypes.ITEM_STACK, RecipeUtils.getResultItem(recipe));
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EItems.WITCH_CAULDRON.get()));
    }

    @Override
    public RecipeType<WitchCauldronRecipe> getRecipeType() {
        return EJeiRecipeTypes.CAULDRON;
    }

}
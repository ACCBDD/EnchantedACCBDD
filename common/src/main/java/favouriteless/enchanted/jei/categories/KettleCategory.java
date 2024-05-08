package favouriteless.enchanted.jei.categories;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.recipes.KettleRecipe;
import favouriteless.enchanted.util.RecipeUtils;
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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class KettleCategory implements IRecipeCategory<KettleRecipe> {

    private final RecipeType<KettleRecipe> type;
    private final IJeiHelpers jeiHelpers;

    private final int GUI_WIDTH = 140;
    private final IDrawableAnimated arrow;

    public KettleCategory(IJeiHelpers jeiHelpers, RecipeType<KettleRecipe> type) {
        this.jeiHelpers = jeiHelpers;
        this.type = type;
        IDrawableStatic arrow = jeiHelpers.getGuiHelper().createDrawable(Enchanted.location("textures/gui/witch_oven.png"), 176, 14, 24, 17);
        this.arrow = jeiHelpers.getGuiHelper().createAnimatedDrawable(arrow, 120, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.enchanted.kettle");
    }

    @Override
    public void draw(KettleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
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
    public void setRecipe(IRecipeLayoutBuilder builder, KettleRecipe recipe, IFocusGroup focuses) {
        int offset = 0;
        for(ItemStack i : recipe.getItemsIn()){
            builder.addSlot(RecipeIngredientRole.INPUT,5+offset,5).addIngredient(VanillaTypes.ITEM_STACK,i);
            offset += 20;
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 30).addIngredient(VanillaTypes.ITEM_STACK, RecipeUtils.getResultItem(recipe));
    }

    @Override
    public IDrawable getBackground() {
        return jeiHelpers.getGuiHelper().createDrawable(Enchanted.location("textures/gui/jei/witch_cauldron.png"), 4, 4, GUI_WIDTH, 70);
    }

    @Override
    public IDrawable getIcon() {
        return jeiHelpers.getGuiHelper().createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EnchantedItems.KETTLE.get()));
    }

    @Override
    public RecipeType<KettleRecipe> getRecipeType() {
        return type;
    }

}
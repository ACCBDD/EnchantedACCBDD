package net.favouriteless.enchanted.integrations.jei.categories;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.init.registry.EItems;
import net.favouriteless.enchanted.common.recipes.ByproductRecipe;
import net.favouriteless.enchanted.integrations.jei.EJeiRecipeTypes;
import net.favouriteless.enchanted.util.RecipeUtils;
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

import java.util.ArrayList;
import java.util.List;

public class ByproductCategory implements IRecipeCategory<ByproductRecipe> {

    private final IGuiHelper helper;

    private final IDrawableAnimated fire;
    private final IDrawableAnimated arrow;

    public ByproductCategory(IGuiHelper helper) {
        this.helper = helper;
        IDrawableStatic fire = helper.createDrawable(Enchanted.id("textures/gui/witch_oven.png"), 176, 0, 14, 14);
        this.fire = helper.createAnimatedDrawable(fire, 120, IDrawableAnimated.StartDirection.BOTTOM, false);
        IDrawableStatic arrow = helper.createDrawable(Enchanted.id("textures/gui/witch_oven.png"), 176, 14, 24, 17);
        this.arrow = helper.createAnimatedDrawable(arrow, 120, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.enchanted.witch_oven");
    }

    @Override
    public IDrawable getBackground() {
        return helper.createDrawable(Enchanted.id("textures/gui/witch_oven.png"), 40, 10, 96, 65);
    }

    @Override
    public IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EItems.WITCH_OVEN.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ByproductRecipe recipe, IFocusGroup focuses) {
        List<ItemStack> itemsOut = new ArrayList<>();
        for(ItemStack stack : recipe.getInput().getItems()) {
            Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(net.minecraft.world.item.crafting.RecipeType.SMELTING)
                    .stream()
                    .filter(smelt -> smelt.getIngredients().get(0).test(stack))
                    .findFirst().ifPresent(smeltingRecipe -> itemsOut.add(RecipeUtils.getResultItem(smeltingRecipe)));
        }

        if(!itemsOut.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 13, 7).addIngredients(recipe.getInput());
            builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 7).addItemStacks(itemsOut);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 43).addIngredient(VanillaTypes.ITEM_STACK, RecipeUtils.getResultItem(recipe));
            builder.addSlot(RecipeIngredientRole.CATALYST, 13, 43).addIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EItems.CLAY_JAR.get(), RecipeUtils.getResultItem(recipe).getCount()));
        }
    }

    @Override
    public void draw(ByproductRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
        fire.draw(gui, 40, 27);
        arrow.draw(gui, 36, 6);
    }

    @Override
    public RecipeType<ByproductRecipe> getRecipeType() {
        return EJeiRecipeTypes.BYPRODUCT;
    }

}
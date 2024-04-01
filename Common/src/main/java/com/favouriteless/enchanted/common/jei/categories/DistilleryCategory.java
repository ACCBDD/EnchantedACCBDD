package com.favouriteless.enchanted.common.jei.categories;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import com.favouriteless.enchanted.common.recipes.DistillingRecipe;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class DistilleryCategory implements IRecipeCategory<DistillingRecipe> {

    private final RecipeType<DistillingRecipe> type;
    private final IJeiHelpers helper;

    private final IDrawableAnimated bubbles;
    private final IDrawableAnimated arrow;


    public DistilleryCategory(IJeiHelpers helper, RecipeType<DistillingRecipe> type) {
        this.helper = helper;
        this.type = type;
        IDrawableStatic bubbles = helper.getGuiHelper().createDrawable(Enchanted.location("textures/gui/distillery.png"), 176, 0, 12, 29);
        this.bubbles = helper.getGuiHelper().createAnimatedDrawable(bubbles, 120, IDrawableAnimated.StartDirection.BOTTOM, false);
        IDrawableStatic arrow = helper.getGuiHelper().createDrawable(Enchanted.location("textures/gui/distillery.png"), 176, 29, 57, 61);
        this.arrow = helper.getGuiHelper().createAnimatedDrawable(arrow, 120, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(DistillingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        this.bubbles.draw(stack, 88, 22);
        this.arrow.draw(stack, 65, 8);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DistillingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 30).addIngredient(VanillaTypes.ITEM_STACK, recipe.getItemsIn().get(0));
        int offset = 0;
        for (ItemStack i : recipe.getItemsIn()) {
            if (offset != 0)
                builder.addSlot(RecipeIngredientRole.INPUT, 50, offset).addIngredient(VanillaTypes.ITEM_STACK, i);
            offset += 20;
        }
        offset = 0;
        for (ItemStack i : recipe.getItemsOut()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 123, 2 + offset).addIngredient(VanillaTypes.ITEM_STACK, i);
            offset += 19;
        }
    }

    @Override
    public Component getTitle() {
        return Component.translatable("container.enchanted.distillery");
    }

    @Override
    public IDrawable getBackground() {
        return this.helper.getGuiHelper().createDrawable(Enchanted.location("textures/gui/distillery.png"), 4, 5, 146, 75);
    }

    @Override
    public IDrawable getIcon() {
        return helper.getGuiHelper().createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EnchantedItems.DISTILLERY.get()));
    }

    @Override
    public RecipeType<DistillingRecipe> getRecipeType() {
        return this.type;
    }
}
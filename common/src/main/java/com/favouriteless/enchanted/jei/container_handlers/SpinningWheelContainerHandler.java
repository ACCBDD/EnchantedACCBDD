package com.favouriteless.enchanted.jei.container_handlers;

import com.favouriteless.enchanted.client.screens.SpinningWheelScreen;
import com.favouriteless.enchanted.jei.JEIRecipeTypes;
import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.runtime.IRecipesGui;
import net.minecraft.client.renderer.Rect2i;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpinningWheelContainerHandler implements IGuiContainerHandler<SpinningWheelScreen> {

    @Override
    public Collection<IGuiClickableArea> getGuiClickableAreas(SpinningWheelScreen containerScreen, double guiMouseX, double guiMouseY) {
        Collection<IGuiClickableArea> areas = new ArrayList<>();
        areas.add(new IGuiClickableArea() {
            @Override
            public Rect2i getArea() {
                return new Rect2i(86,25,34,34);
            }

            @Override
            public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
                List<RecipeType<?>> list = new ArrayList<>();
                list.add(JEIRecipeTypes.RECIPE_TYPE_SPINNING);
                recipesGui.showTypes(list);
            }
        });
        return areas;
    }
}
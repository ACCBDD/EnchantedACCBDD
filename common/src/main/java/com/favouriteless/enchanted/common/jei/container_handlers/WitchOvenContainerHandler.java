package com.favouriteless.enchanted.common.jei.container_handlers;

import com.favouriteless.enchanted.client.screens.WitchOvenScreen;
import com.favouriteless.enchanted.common.jei.JEIRecipeTypes;
import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.runtime.IRecipesGui;
import net.minecraft.client.renderer.Rect2i;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WitchOvenContainerHandler implements IGuiContainerHandler<WitchOvenScreen> {

    @Override
    public Collection<IGuiClickableArea> getGuiClickableAreas(WitchOvenScreen containerScreen, double guiMouseX, double guiMouseY) {
        Collection<IGuiClickableArea> areas = new ArrayList<>();
        areas.add(new IGuiClickableArea() {
            @Override
            public Rect2i getArea() {
                return new Rect2i(76,16,24,17);
            }

            @Override
            public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
                List<RecipeType<?>> list = new ArrayList<>();
                list.add(JEIRecipeTypes.RECIPE_TYPE_BYPRODUCT);
                recipesGui.showTypes(list);
            }
        });
        return areas;
    }
}
package favouriteless.enchanted.integrations.jei.container_handlers;

import favouriteless.enchanted.client.screens.DistilleryScreen;
import favouriteless.enchanted.integrations.jei.EJeiRecipeTypes;
import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.runtime.IRecipesGui;
import net.minecraft.client.renderer.Rect2i;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DistilleryContainerHandler implements IGuiContainerHandler<DistilleryScreen> {

    public Collection<IGuiClickableArea> getGuiClickableAreas(DistilleryScreen containerScreen, double guiMouseX, double guiMouseY) {
        Collection<IGuiClickableArea> areas = new ArrayList<>();
        areas.add(new IGuiClickableArea() {
            @Override
            public Rect2i getArea() {
                return new Rect2i(69,12,56,62);
            }

            @Override
            public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
                List<RecipeType<?>> list = new ArrayList<>();
                list.add(EJeiRecipeTypes.RECIPE_TYPE_DISTILLING);
                recipesGui.showTypes(list);
            }
        });
        return areas;
    }

}
package favouriteless.enchanted.jei;

import favouriteless.enchanted.common.menus.DistilleryMenu;
import favouriteless.enchanted.common.recipes.DistillingRecipe;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

import java.util.List;
import java.util.Optional;

public class DistilleryTransferInfo implements IRecipeTransferInfo<DistilleryMenu, DistillingRecipe> {

    @Override
    public Class<DistilleryMenu> getContainerClass() {
        return DistilleryMenu.class;
    }

    @Override
    public boolean canHandle(DistilleryMenu container, DistillingRecipe recipe) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(DistilleryMenu container, DistillingRecipe recipe) {
        return container.slots.subList(0,3);
    }

    @Override
    public boolean requireCompleteSets(DistilleryMenu container, DistillingRecipe recipe) {
        return false;
    }

    @Override
    public List<Slot> getInventorySlots(DistilleryMenu container, DistillingRecipe recipe) {
        return container.slots.subList(5,container.slots.size());
    }

    @Override
    public RecipeType<DistillingRecipe> getRecipeType() {
        return JEIRecipeTypes.RECIPE_TYPE_DISTILLING;
    }

    @Override
    public Optional<MenuType<DistilleryMenu>> getMenuType() {
        return Optional.empty();
    }

}
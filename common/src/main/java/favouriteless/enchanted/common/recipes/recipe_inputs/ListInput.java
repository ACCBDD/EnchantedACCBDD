package favouriteless.enchanted.common.recipes.recipe_inputs;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public class ListInput implements RecipeInput {

    private final List<ItemStack> items;

    private ListInput(List<ItemStack> items) {
        this.items = items;
    }

    public static ListInput of(ItemStack... items) {
        return new ListInput(List.of(items));
    }

    public static ListInput of(List<ItemStack> items) {
        return new ListInput(items);
    }

    @Override
    public ItemStack getItem(int index) {
        return index < 0 || index >= items.size() ? null : items.get(0);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

}

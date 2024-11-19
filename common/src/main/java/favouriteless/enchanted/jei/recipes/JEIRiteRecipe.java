package favouriteless.enchanted.jei.recipes;

import favouriteless.enchanted.common.circle_magic.rites.RiteRequirements;
import favouriteless.enchanted.common.circle_magic.RiteType;
import net.minecraft.world.item.ItemStack;

public record JEIRiteRecipe(RiteType<?> type, RiteRequirements requirements, ItemStack[] output) {

}

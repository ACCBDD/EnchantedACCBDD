package favouriteless.enchanted.jei.recipes;

import favouriteless.enchanted.common.rites.RiteRequirements;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record JEIRiteRecipe(RiteType<?> type, RiteRequirements requirements, ItemStack[] output) {

}

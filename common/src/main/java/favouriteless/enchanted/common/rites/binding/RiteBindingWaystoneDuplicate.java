package favouriteless.enchanted.common.rites.binding;

import favouriteless.enchanted.api.rites.CreateItemRite;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class RiteBindingWaystoneDuplicate extends CreateItemRite {

    public RiteBindingWaystoneDuplicate(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, new ItemStack(EnchantedItems.BOUND_WAYSTONE.get(), 2));
    }

    @Override
    public void setupItemNbt(int index, ItemStack stack) {
        if(index == 0) {
            for(ItemStack item : itemsConsumed) {
                if(item.getItem() == EnchantedItems.BOUND_WAYSTONE.get()) {
                    stack.setTag(item.getOrCreateTag());
                }
            }
        }
    }

}

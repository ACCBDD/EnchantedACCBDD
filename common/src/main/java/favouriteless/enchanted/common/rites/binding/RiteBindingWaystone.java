package favouriteless.enchanted.common.rites.binding;

import favouriteless.enchanted.api.rites.CreateItemRite;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.rites.RiteType;
import favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class RiteBindingWaystone extends CreateItemRite {

    public RiteBindingWaystone(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, new ItemStack(EnchantedItems.BOUND_WAYSTONE.get()));
    }

    @Override
    public void setupItemNbt(int index, ItemStack stack) {
        if(index == 0) {
            if(getLevel() != null)
                WaystoneHelper.bind(stack, getLevel(), getPos());
        }
    }

}

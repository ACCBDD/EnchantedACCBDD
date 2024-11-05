package favouriteless.enchanted.common.rites.binding;

import favouriteless.enchanted.api.rites.CreateItemRite;
import favouriteless.enchanted.common.items.EItems;
import favouriteless.enchanted.common.items.TaglockFilledItem;
import favouriteless.enchanted.common.rites.RiteType;
import favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class RiteBindingWaystonePlayer extends CreateItemRite {

    public RiteBindingWaystonePlayer(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, new ItemStack(EItems.BLOODED_WAYSTONE.get(), 1));
    }

    @Override
    public void setupItemNbt(int index, ItemStack stack) {
        if(index == 0) {
            if(getTargetUUID() != null)
                WaystoneHelper.bind(stack, getTargetUUID(), tryFindTargetEntity() != null ? getTargetEntity().getDisplayName().getString() : "");
        }
    }

    @Override
    protected boolean checkAdditional() {
        for(ItemStack stack : itemsConsumed) {
            if(stack.getItem() == EItems.TAGLOCK_FILLED.get()) {
                setTargetUUID(TaglockFilledItem.getUUID(stack));
                if(getTargetUUID() == null)
                    return false;
            }
        }
        return true;
    }

}

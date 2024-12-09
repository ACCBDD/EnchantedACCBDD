package net.favouriteless.enchanted.common.circle_magic.rites;

import net.favouriteless.enchanted.common.init.registry.EItems;
import net.favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.world.item.ItemStack;

public class SkyWrathWaystoneRite extends SkyWrathRite {

    public SkyWrathWaystoneRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected void findTargetLocation(RiteParams params) {
        for (ItemStack item : params.consumedItems) {
            if (item.getItem() == EItems.BOUND_WAYSTONE.get()) {
                targetLevel = level.getServer().getLevel(WaystoneHelper.getLevel(level, item).dimension());
                targetPos = WaystoneHelper.getPos(item);
                return;
            }
        }
    }

}

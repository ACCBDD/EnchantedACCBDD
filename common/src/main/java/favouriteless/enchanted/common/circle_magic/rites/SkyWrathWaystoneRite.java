package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.items.component.EDataComponents;
import favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.world.item.ItemStack;

public class SkyWrathWaystoneRite extends SkyWrathRite {

    public SkyWrathWaystoneRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected void findTargetLocation(RiteParams params) {
        for(ItemStack item : params.consumedItems) {
            if(item.getItem() == EnchantedItems.BOUND_WAYSTONE.get()) {
                targetLevel = level.getServer().getLevel(WaystoneHelper.getLevel(level, item).dimension());
                targetPos = WaystoneHelper.getPos(item);
                return;
            }
        }
    }

}

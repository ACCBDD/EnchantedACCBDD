package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ProtectionWaystoneRite extends ProtectionRite {

    public ProtectionWaystoneRite(BaseRiteParams baseParams, RiteParams params, int radius, int duration, boolean blocking) {
        super(baseParams, params, radius, duration, blocking);
    }

    @Override
    protected void findTargetLocation(RiteParams params) {
        for (ItemStack item : params.consumedItems) {
            Level testLevel = WaystoneHelper.getLevel(level, item);
            BlockPos testPos = WaystoneHelper.getPos(item);
            if (testLevel != null && testPos != null) {
                targetLevel = testLevel.getServer().getLevel(level.dimension());
                targetPos = testPos;
                return;
            }
        }
    }

}

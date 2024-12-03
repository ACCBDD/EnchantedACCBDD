package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ProtectionEntityRite extends ProtectionRite {

    public ProtectionEntityRite(BaseRiteParams baseParams, RiteParams params, int radius, int duration, boolean blocking) {
        super(baseParams, params, radius, duration, blocking);
    }

    @Override
    protected void findTargetLocation(RiteParams params) {
        if (params.target != null) {
            Entity target = findEntity(params.target);
            if (target == null)
                return;

            targetLevel = (ServerLevel) target.level();
            targetPos = target.blockPosition();
        } else {
            for (ItemStack item : params.consumedItems) {
                Entity target = WaystoneHelper.getEntity(level, item);
                if (target == null)
                    return;

                params.target = target.getUUID();
                targetLevel = (ServerLevel) target.level();
                targetPos = target.blockPosition();
            }
        }
    }

}

package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SkyWrathBloodedRite extends SkyWrathRite {

    public SkyWrathBloodedRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected void findLocation(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems, @Nullable UUID targetUUID) {
        Entity target = findEntity(targetUUID);
        if(target != null) {
            targetLevel = (ServerLevel)target.level();
            targetPos = target.blockPosition();
        }
    }

}

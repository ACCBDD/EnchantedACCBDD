package net.favouriteless.enchanted.common.util;

import net.favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class EntityUtils {

    public static Entity getControllingEntity(Entity entity) {
        return entity.isPassenger() ? getControllingEntity(entity.getVehicle()) : entity;
    }

    public static @Nullable Entity findEntity(ServerLevel level, EntityRefData data) {
        Entity out = level.getServer().getPlayerList().getPlayer(data.uuid());
        if(out != null)
            return out;

        for(ServerLevel dim : level.getServer().getAllLevels()) {
            out = dim.getEntity(data.uuid());
            if(out != null)
                return out;
        }
        return null;
    }

}

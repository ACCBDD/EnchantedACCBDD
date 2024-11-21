package favouriteless.enchanted.common.util;

import net.minecraft.world.entity.Entity;

public class EntityUtils {
    public static Entity getControllingEntity(Entity entity) {
        return entity.isPassenger() ? getControllingEntity(entity.getVehicle()) : entity;
    }
}

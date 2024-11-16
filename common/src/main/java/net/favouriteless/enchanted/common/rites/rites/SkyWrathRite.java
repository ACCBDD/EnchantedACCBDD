package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SkyWrathRite extends LocationTargetRite {

    public static final int START_RAINING = 120;
    public static final int EXPLODE = 180;
    public static final double LIGHTNING_RADIUS = 5;

    public SkyWrathRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        findLocation(level, pos, consumedItems, targetUUID);
        if(targetLevel == null || targetPos == null)
            return cancel();

        targetLevel.sendParticles(EParticleTypes.SKY_WRATH_SEED.get(),
                pos.getX()+0.5D, pos.getY()+2, pos.getZ()+0.5D,
                1, 0, 0, 0, 0);
        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                             @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        if(ticks == START_RAINING) {
            level.setWeatherParameters(0, 6000, true, true);
        }
        else if(ticks > EXPLODE) {
            spawnLightning(targetLevel, targetPos.getX() + 0.5D, targetPos.getY(), targetPos.getZ() + 0.5D);
            return false;
        }

        return true;
    }

    protected void findLocation(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems, @Nullable UUID targetUUID) {
        targetLevel = level;
        targetPos = pos;
    }

    /**
     * Spawn 6 lightning bolts in a circle around xyz.
     */
    protected void spawnLightning(ServerLevel level, double x, double y, double z) {
        for(int a = 0; a < 360; a += 60) {
            double angle = Math.toRadians(a);
            double cx = x + Math.sin(angle) * LIGHTNING_RADIUS;
            double cz = z + Math.cos(angle) * LIGHTNING_RADIUS;

            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
            bolt.setPos(cx, y, cz);
            level.addFreshEntity(bolt);
        }
    }

}

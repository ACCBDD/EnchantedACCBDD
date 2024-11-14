package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.client.particles.types.ColouredCircleOptions;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.init.ETags.EntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SanctityRite extends Rite {

    public static final float RADIUS = 3.0f;
    public static final float RADIUS_SQR = RADIUS * RADIUS;
    public static final double REPULSE_FACTOR = 3.0d;

    public SanctityRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        Vec3 center = pos.getBottomCenter();
        List<Entity> entities = level.getEntities(
                (Entity)null,
                new AABB(center.subtract(RADIUS+2, 0, RADIUS+2), center.add(RADIUS+2, 6, RADIUS+2)),
                e -> e.getType().is(EntityTypes.MONSTERS) && new Vec3(e.getX()-center.x, 0, e.getZ()-center.z).lengthSqr() < RADIUS_SQR
        );

        for(Entity entity : entities) {
            Entity toApply = getControllingEntity(entity);

            Vec3 local = new Vec3(toApply.getX()-center.x, 0, toApply.getZ()-center.z);
            Vec3 vel = toApply.getDeltaMovement();
            vel = new Vec3(vel.x, 0, vel.y);

            Vec3 toCenter = local.scale(-1).normalize(); // Direction from entity to center
            double dot = toCenter.dot(vel.normalize()); // 1 if entity moving towards center, -1 if away

            if(dot > 0) // If entity moving towards center, neutralize that movement.
                toApply.addDeltaMovement(vel.scale(-dot));


            double d = local.length();
            toApply.addDeltaMovement(local.scale((1 - (d / RADIUS)) * REPULSE_FACTOR / d));
        }

        if(this.ticks % 2 == 0) {
            double cx = pos.getX() + 0.5d;
            double cz = pos.getZ() + 0.5d;
            double dy = pos.getY() + 0.1d;
            double dz = pos.getZ() + 0.5d;

            level.sendParticles(new ColouredCircleOptions(EParticleTypes.CIRCLE_MAGIC.get(), 0xFFFFFFFF,
                    new Vec3(cx, pos.getY(), cz), RADIUS), cx + RADIUS, dy, dz, 1, 0, 0.35d, 0, 0);
            level.sendParticles(new ColouredCircleOptions(EParticleTypes.CIRCLE_MAGIC.get(), 0xFFFFFFFF,
                    new Vec3(cx, pos.getY(), cz), RADIUS), cx - RADIUS, dy, dz, 1, 0, 0.35d, 0, 0);
        }

        return true;
    }

    protected Entity getControllingEntity(Entity entity) {
        return entity.isPassenger() ? getControllingEntity(entity.getVehicle()) : entity;
    }

}

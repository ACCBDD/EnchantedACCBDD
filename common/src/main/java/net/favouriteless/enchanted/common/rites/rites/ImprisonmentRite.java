package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.client.particles.ImprisonmentCageParticle;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.init.ETags.EntityTypes;
import net.favouriteless.enchanted.common.util.EntityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ImprisonmentRite extends Rite {

    public static final double ATTRACT_FACTOR = 0.6d;
    public static final double INNER_RADIUS = 3.0f;
    public static final double OUTER_RADIUS = 4.0f;
    private static final double INNER_RADIUS_SQR = INNER_RADIUS * INNER_RADIUS;
    private static final double OUTER_RADIUS_SQR = OUTER_RADIUS * OUTER_RADIUS;

    public ImprisonmentRite(BaseRiteParams params) {
        super(params);
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                             @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        Vec3 center = pos.getBottomCenter();
        List<Entity> entities = level.getEntities(
                (Entity)null,
                new AABB(center.subtract(OUTER_RADIUS, 0, OUTER_RADIUS), center.add(OUTER_RADIUS, 4, OUTER_RADIUS)),
                e -> e.getType().is(EntityTypes.MONSTERS) &&
                        new Vec3(e.getX()-center.x, 0, e.getZ()-center.z).lengthSqr() > INNER_RADIUS_SQR &&
                        new Vec3(e.getX()-center.x, 0, e.getZ()-center.z).lengthSqr() < OUTER_RADIUS_SQR
        );

        for(Entity entity : entities) {
            Entity toApply = EntityUtils.getControllingEntity(entity);

            Vec3 local = new Vec3(toApply.getX()-center.x, 0, toApply.getZ()-center.z);
            Vec3 vel = toApply.getDeltaMovement();
            vel = new Vec3(vel.x, 0, vel.z);

            double dot = vel.dot(local) / local.dot(local); // + if entity moving away from center, - if towards
            if(dot > 0)
                toApply.addDeltaMovement(local.scale(-dot));

            double d = local.lengthSqr();
            if(d > (INNER_RADIUS - 0.3f) * (INNER_RADIUS - 0.3f))
                toApply.addDeltaMovement(local.scale(-ATTRACT_FACTOR / d));
        }


        if(ticks % (ImprisonmentCageParticle.LIFETIME+15) == 0) { // 15 ticks for the fade time
            level.sendParticles(EParticleTypes.IMPRISONMENT_CAGE_SEED.get(), pos.getX()+0.5d,
                    pos.getY()+0.2d, pos.getZ()+0.5d, 1, 0, 0, 0, 0);
        }
        return true;
    }
}

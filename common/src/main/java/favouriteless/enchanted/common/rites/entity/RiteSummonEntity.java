package favouriteless.enchanted.common.rites.entity;

import favouriteless.enchanted.api.rites.AbstractRite;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class RiteSummonEntity extends AbstractRite {

    public RiteSummonEntity(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster); // Power, power per tick
    }

    @Override
    public void execute() {
        if(getTargetEntity() == null)
            tryFindTargetEntity();

        ServerLevel level = getLevel();
        BlockPos pos = getPos();
        Entity targetEntity = getTargetEntity();
        if(level != null && pos != null && targetEntity != null) {
            spawnParticles(level, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D);
            spawnParticles((ServerLevel)targetEntity.level(), targetEntity.getX(), targetEntity.getY(), targetEntity.getZ());
            level.playSound(null, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1.0F, 1.0F);
            targetEntity.level().playSound(null, targetEntity.getX(), targetEntity.getX(), targetEntity.getY(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER, 1.0F, 1.0F);

            Vec3 destination = new Vec3(pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D);
            if(level != targetEntity.level())
                targetEntity.changeDimension(level);
            else
                targetEntity.teleportTo(destination.x, destination.y, destination.z);
        }
        else {
            cancel();
        }
        stopExecuting();
    }

    protected void spawnParticles(ServerLevel world, double x, double y, double z) {
        for(int i = 0; i < 25; i++) {
            double dx = x - 0.5D + (Math.random() * 1.5D);
            double dy = y + (Math.random() * 2.0D);
            double dz = z - 0.5D + (Math.random() * 1.5D);
            world.sendParticles(ParticleTypes.PORTAL, dx, dy, dz, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

}

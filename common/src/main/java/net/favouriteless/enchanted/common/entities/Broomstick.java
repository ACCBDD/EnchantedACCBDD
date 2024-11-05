package net.favouriteless.enchanted.common.entities;

import net.favouriteless.enchanted.client.client_handlers.entities.BroomstickEntityClientHandler;
import net.favouriteless.enchanted.common.items.EItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class Broomstick extends Entity {

    public static final double ACCELERATION = 0.02D;
    public static final double MAX_SPEED = 1.0D;

    private float deltaRotX = 0.0F;
    private float deltaRotY = 0.0F;

    private int inputAcceleration = 0;
    private int inputClimb = 0;

    private int lerpSteps = 0;
    private double lerpX = 0.0D;
    private double lerpY = 0.0D;
    private double lerpZ = 0.0D;
    private double lerpXRot = 0.0D;
    private double lerpYRot = 0.0D;

    private static final EntityDataAccessor<Integer> DATA_ID_HURT = SynchedEntityData.defineId(Broomstick.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR = SynchedEntityData.defineId(Broomstick.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_ID_DAMAGE = SynchedEntityData.defineId(Broomstick.class, EntityDataSerializers.FLOAT);

    public Broomstick(EntityType<Broomstick> type, Level world) {
        super(type, world);
        blocksBuilding = true;
    }

    @Override
    protected void defineSynchedData(Builder builder) {
        builder.define(DATA_ID_HURT, 0);
        builder.define(DATA_ID_HURTDIR, 1);
        builder.define(DATA_ID_DAMAGE, 0.0F);
    }

    @Override
    public void tick() {
        if (getHurtTime() > 0) {
            setHurtTime(getHurtTime() - 1);
        }

        if (getDamage() > 0.0F) {
            setDamage(getDamage() - 1.0F);
        }

        super.tick();

        tickLerp();
        if(isControlledByLocalInstance()) {
            //setPacketCoordinates(getX(), getY(), getZ());

            if(level().isClientSide) {
                deltaRotX *= 0.8F;
                deltaRotY *= 0.8F;
                BroomstickEntityClientHandler.controlBroom(this);
            }
            else {
                setDeltaMovement(getDeltaMovement().scale(0.75D).add(0.0D, -0.05D, 0.0D));
            }
            move(MoverType.SELF, getDeltaMovement());
        }
        else {
            setDeltaMovement(Vec3.ZERO);
        }
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void playSwimSound(float pVolume) {}

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {}

    private void tickLerp() {
        if(isControlledByLocalInstance()) {
            lerpSteps = 0;
            //setPacketCoordinates(getX(), getY(), getZ());
        }

        if(lerpSteps > 0) {
            double x = getX() + (lerpX - getX()) / lerpSteps;
            double y = getY() + (lerpY - getY()) / lerpSteps;
            double z = getZ() + (lerpZ - getZ()) / lerpSteps;

            double xRot = Mth.wrapDegrees(lerpXRot - getXRot());
            double yRot = Mth.wrapDegrees(lerpYRot - getYRot());

            setXRot((float)(getXRot() + xRot / lerpSteps));
            setYRot((float)(getYRot() + yRot / lerpSteps));;

            --lerpSteps;
            setPos(x, y, z);
            setRot(getYRot(), getXRot());
        }
    }

    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int steps) {
        lerpX = x;
        lerpY = y;
        lerpZ = z;
        lerpYRot = yaw;
        lerpXRot = pitch;
        lerpSteps = steps;
    }

    public Vec3 getNewDeltaMovement() {
        Vec3 velocity = getDeltaMovement();

        Vec3 forward = Vec3.directionFromRotation(new Vec2(getXRot(), getYRot()));
        Vec3 up = Vec3.directionFromRotation(new Vec2(getXRot()-90, getYRot()));
        Vec3 left = up.cross(forward);

        double acceleration = inputAcceleration * ACCELERATION;
        velocity = forward.scale(velocity.dot(forward) * (inputAcceleration == 0 ? 0.85D : 1.0D) + acceleration)
                .add(up.scale(velocity.dot(up) * 0.85D))
                .add(left.scale(velocity.dot(left) * 0.85D));
        velocity = velocity.add(0, inputClimb * ACCELERATION*1.25D, 0);

        double speed = Math.max(Math.min(velocity.length(), MAX_SPEED), 0);
        return velocity.normalize().scale(speed);
    }

    @Override
    public double getEyeY() {
        return 0.7D;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {
        return new Vec3(0, 0.15D, 0);
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if(player.isSecondaryUseActive()) {
            return InteractionResult.SUCCESS;
        }
        else {
            if(!level().isClientSide) {
                return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
            }
            else {
                return InteractionResult.SUCCESS;
            }
        }
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag nbt) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag nbt) {

    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void positionRider(@NotNull Entity passenger, Entity.MoveFunction moveFunction) {
        super.positionRider(passenger, moveFunction);
        clampRotation(passenger);
    }

    public boolean isControlledByLocalInstance() {
        Entity entity = getControllingPassenger();
        if(entity instanceof Player) {
            return ((Player) entity).isLocalPlayer();
        }
        else {
            return !level().isClientSide;
        }
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        List<Entity> list = getPassengers();
        if(list.isEmpty())
            return null;
        return list.get(0) instanceof LivingEntity le ? le : null;
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity passenger) {
        return getPassengers().isEmpty();
    }

    @Override
    public void onPassengerTurned(@NotNull Entity entity) {
        clampRotation(entity);
    }

    protected void clampRotation(Entity entity) {
        entity.setYBodyRot(getYRot());
        float f = Mth.wrapDegrees(entity.getYRot() - getYRot());
        float f1 = Mth.clamp(f, -105.0F, 105.0F);
        entity.yRotO += f1 - f;
        entity.setYRot(entity.getYRot() + f1 - f);
        entity.setYHeadRot(entity.getYRot());
    }

    @Override
    public void push(@NotNull Entity entity) {
        if(entity instanceof Broomstick) {
            if(entity.getBoundingBox().minY < getBoundingBox().maxY) {
                super.push(entity);
            }
        }
        else if(entity.getBoundingBox().minY <= getBoundingBox().minY) {
            super.push(entity);
        }

    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float pAmount) {
        if(isInvulnerableTo(source)) {
            return false;
        }
        else if(!level().isClientSide && !isRemoved()) {
            setHurtDir(-getHurtDir());
            setHurtTime(10);
            setDamage(getDamage() + pAmount * 10.0F);
            markHurt();
            boolean isSurvivalPlayer = source.getEntity() instanceof Player && ((Player) source.getEntity()).getAbilities().instabuild;
            if(isSurvivalPlayer || getDamage() > 40.0F) {
                if(!isSurvivalPlayer && level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    spawnAtLocation(EItems.ENCHANTED_BROOMSTICK.get());
                }

                discard();
            }

            return true;
        }
        else {
            return true;
        }
    }

    public void setHurtDir(int pForwardDirection) {
        entityData.set(DATA_ID_HURTDIR, pForwardDirection);
    }

    public int getHurtDir() {
        return entityData.get(DATA_ID_HURTDIR);
    }

    public void setHurtTime(int pTimeSinceHit) {
        entityData.set(DATA_ID_HURT, pTimeSinceHit);
    }

    public int getHurtTime() {
        return entityData.get(DATA_ID_HURT);
    }

    public void setDamage(float pDamageTaken) {
        entityData.set(DATA_ID_DAMAGE, pDamageTaken);
    }

    public float getDamage() {
        return entityData.get(DATA_ID_DAMAGE);
    }

    @Override
    public void animateHurt(float yaw) {
        setHurtDir(-getHurtDir());
        setHurtTime(10);
        setDamage(getDamage() * 11.0F);
    }

    public void setInputAcceleration(boolean forwards, boolean backwards) {
        if((!forwards && !backwards) || (forwards && backwards))
            inputAcceleration = 0;
        else if(forwards)
                inputAcceleration = 1;
        else
            inputAcceleration = -1;
    }

    public void setInputClimb(boolean up, boolean down) {
        if((!up && !down) || (up && down))
            inputClimb = 0;
        else if(up)
            inputClimb = 1;
        else
            inputClimb = -1;
    }


    public void setDeltaRotX(float value) {
        deltaRotX = value;
    }

    public void setDeltaRotY(float value) {
        deltaRotY = value;
    }

    public float getDeltaRotX() {
        return deltaRotX;
    }

    public float getDeltaRotY() {
        return deltaRotY;
    }

}

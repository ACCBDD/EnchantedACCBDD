package favouriteless.enchanted.common.entities;

import favouriteless.enchanted.common.init.EnchantedDamageTypes;
import favouriteless.enchanted.common.init.registry.EItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class Mandrake extends Monster implements GeoEntity {

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation SPIN_ANIM = RawAnimation.begin().thenPlay("animation.mandrake.walk");

    public Mandrake(EntityType<? extends Monster> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        WaterAvoidingRandomStrollGoal randomWalkingGoal = new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0F);
        randomWalkingGoal.setInterval(1);

        this.goalSelector.addGoal(0, new MandrakeAttackGoal(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, randomWalkingGoal);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_DEATH;
    }

    @Override
    public Fallsounds getFallSounds() {
        return new Fallsounds(SoundEvents.GENERIC_SMALL_FALL, SoundEvents.GENERIC_BIG_FALL);
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }


    @Override
    public void registerControllers(ControllerRegistrar registrar) {
        registrar.add(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(SPIN_ANIM);
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }

    protected class MandrakeAttackGoal extends Goal {

        protected final Mandrake mob;
        protected int ticksUntilNextAttack;

        public MandrakeAttackGoal(Mandrake entity) {
            this.mob = entity;
            this.ticksUntilNextAttack = 0;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void stop() {
            this.ticksUntilNextAttack = 30;
        }

        @Override
        public void tick() {
            if(--this.ticksUntilNextAttack <= 0) {
                List<LivingEntity> entitiesInRange = mob.level().getEntitiesOfClass(LivingEntity.class,
                        new AABB(this.mob.position().x - 8, this.mob.position().y - 8, this.mob.position().z - 8,
                                this.mob.position().x + 8, this.mob.position().y + 8, this.mob.position().z + 8), entity -> !(entity instanceof Mandrake) );

                for(LivingEntity entity : entitiesInRange) {
                    if(entity instanceof Player player && player.isCreative())
                        continue;

                    if(entity.getItemBySlot(EquipmentSlot.HEAD).getItem() != EItems.EARMUFFS.get()) {
                        entity.hurt(EnchantedDamageTypes.source(level(), EnchantedDamageTypes.SOUND, mob), 1.0F);
                        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
                    }
                }
                this.mob.level().playSound(null, this.mob.getX(), this.mob.getY(), this.mob.getZ(), SoundEvents.GHAST_HURT, SoundSource.HOSTILE, 10.0F,0.85F + random.nextFloat() * 0.1F);

                this.ticksUntilNextAttack = 30;
            }
        }

    }

}

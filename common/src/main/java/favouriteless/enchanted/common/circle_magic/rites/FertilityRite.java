package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.registry.EParticleTypes;
import favouriteless.enchanted.common.util.BlockPosUtils;
import favouriteless.enchanted.mixin.common.ZombieVillagerAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FertilityRite extends Rite {

    MobEffect[] cured = new MobEffect[]{MobEffects.BLINDNESS, MobEffects.CONFUSION, MobEffects.HUNGER, MobEffects.POISON,
            MobEffects.WEAKNESS, MobEffects.WITHER};

    public static final int TICKS_PER_BLOCK = 5;

    protected final int radius;
    protected final int radiusSq;
    protected final double bonemealChance;

    protected int step = 1;

    public FertilityRite(BaseRiteParams baseParams, RiteParams params, int radius, double bonemealChance) {
        super(baseParams, params);
        this.radius = radius;
        this.radiusSq = radius * radius;
        this.bonemealChance = bonemealChance;
    }

    @Override
    protected boolean onStart(RiteParams params) {
        UUID caster = params.caster;
        level.getEntitiesOfClass(LivingEntity.class, type.getBounds(pos), e -> e.position().distanceToSqr(pos.getCenter()) < radiusSq)
                .forEach(entity -> applyCureEffects(caster, entity));

        return true;
    }

    @Override
    protected boolean onTick(RiteParams params) {
        if (params.ticks() % TICKS_PER_BLOCK != 0)
            return true;

        BlockPosUtils.iterableSphereHollow(pos, step).forEach(spherePos -> {
            if (Math.random() > bonemealChance)
                return;

            BlockState state = level.getBlockState(spherePos);
            if (state.isAir())
                return;

            if (state.getBlock() instanceof BonemealableBlock block)
                block.performBonemeal(level, level.random, spherePos, state);
        });

        if (params.ticks() % (TICKS_PER_BLOCK * 5) == 0) {
            level.playSound(null, pos, SoundEvents.ENDER_DRAGON_GROWL, SoundSource.MASTER, 0.1F, 1.0F);
            level.sendParticles(EParticleTypes.FERTILITY_SEED.get(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    1, 0, 0, 0, 0);
        }

        return step++ < radius;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, ServerLevel level) {
        tag.putInt("step", step);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, ServerLevel level) {
        step = tag.getInt("step");
    }

    protected void applyCureEffects(UUID casterUUID, LivingEntity target) {
        if (target instanceof ZombieVillager villager) {
            ((ZombieVillagerAccessor) villager).startConverting(casterUUID, Enchanted.RANDOM.nextInt(2401) + 3600);
            return;
        }

        List<MobEffect> toRemove = new ArrayList<>();
        for (MobEffect effect : target.getActiveEffectsMap().keySet()) {
            if (Arrays.asList(cured).contains(effect))
                toRemove.add(effect);
        }

        toRemove.forEach(target::removeEffect);
    }

}

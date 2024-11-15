package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.client.particles.types.DoubleOptions;
import net.favouriteless.enchanted.common.blocks.EBlocks;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.items.EItems;
import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.favouriteless.enchanted.common.stateobservers.ProtectionRiteObserver;
import net.favouriteless.enchanted.common.util.EntityUtils;
import net.favouriteless.stateobserver.api.StateObserverManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProtectionRite extends Rite {

    private final int radius;
    private final int duration;
    private final boolean blocksPlayers;

    protected ProtectionRiteObserver observer = null;
    protected ServerLevel targetLevel = null;
    protected BlockPos targetPos = null;

    public ProtectionRite(BaseRiteParams params, int radius, int duration, boolean blocksPlayers) {
        super(params);
        this.radius = radius;
        this.duration = duration;
        this.blocksPlayers = blocksPlayers;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        findTargetLocation(level, pos);
        generateSphere(targetLevel, targetPos, blocksPlayers? EBlocks.PROTECTION_BARRIER_BLOCKING.get() : EBlocks.PROTECTION_BARRIER.get());
        getOrCreateObserver(targetLevel, targetPos);
        targetLevel.playSound(null, targetPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.MASTER, 1.0f, 1.0f);
        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        observer.checkChanges();

        if(ticks % 20 == 0) {
            targetLevel.sendParticles(new DoubleOptions(EParticleTypes.PROTECTION_SEED.get(), radius),
                    targetPos.getX()+0.5d, targetPos.getY()+0.6d, targetPos.getZ()+0.5d,
                    1, 0, 0, 0, 0);
        }
        return ticks < duration;
    }

    @Override
    protected void onStop(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        generateSphere(targetLevel, targetPos, Blocks.AIR);
        targetLevel.playSound(null, targetPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.MASTER, 1.0f, 0.5f);
        StateObserverManager.get().removeObserver(observer);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, ServerLevel level) {
        tag.putInt("xT", targetPos.getX());
        tag.putInt("yT", targetPos.getY());
        tag.putInt("zT", targetPos.getZ());
        tag.putString("targetLevel", targetLevel.dimension().location().toString());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, ServerLevel level) {
        targetPos = new BlockPos(
                tag.getInt("xT"),
                tag.getInt("yT"),
                tag.getInt("zT")
        );
        targetLevel = level.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(tag.getString("targetLevel"))));
        getOrCreateObserver(targetLevel, targetPos);
    }

    protected void generateSphere(ServerLevel level, BlockPos pos, Block block) {
        MutableBlockPos spherePos = new MutableBlockPos(0, 0, 0);
        float increment = 1.0f / radius; // Total radians / circumference for radians per step
        for(float y = 0; y <= Mth.PI*2 + increment/2; y += increment) {
            for(float p = 0; p <= Mth.PI*2 + increment/2; p += increment) {
                float cosP = Mth.cos(p);
                spherePos.set(
                        Math.round(Mth.sin(y) * cosP * radius) + pos.getX(),
                        Math.round(Mth.sin(p) * radius) + pos.getY(),
                        Math.round(Mth.cos(y) * cosP * radius) + pos.getZ()
                );

                BlockState state = level.getBlockState(spherePos);
                if(state.isAir() || !state.getFluidState().isEmpty() || state.is(EBlocks.PROTECTION_BARRIER.get()) || state.is(EBlocks.PROTECTION_BARRIER_BLOCKING.get()))
                    level.setBlockAndUpdate(spherePos, block.defaultBlockState());
            }
        }
    }

    protected void findTargetLocation(ServerLevel level, BlockPos pos) {
        AABB bounds = new AABB(
                pos.getBottomCenter().subtract(5, 0, 5),
                pos.getBottomCenter().add(5, 1, 5)
        );
        List<ItemEntity> entities = level.getEntitiesOfClass(ItemEntity.class, bounds);

        for(ItemEntity itemEntity : entities) {
            ItemStack item = itemEntity.getItem();
            if(item.has(EDataComponents.LEVEL_KEY.get()) && item.has(EDataComponents.BLOCK_POS.get())) {
                targetLevel = level.getServer().getLevel(item.get(EDataComponents.LEVEL_KEY.get()));
                targetPos = item.get(EDataComponents.BLOCK_POS.get());
                consumeItem(itemEntity);
                return;
            }
            else if(item.is(EItems.BLOODED_WAYSTONE.get()) && item.has(EDataComponents.ENTITY_REF.get())) {
                Entity entity = EntityUtils.findEntity(level, item.get(EDataComponents.ENTITY_REF.get()));
                if(entity != null) {
                    targetLevel = (ServerLevel)entity.level();
                    targetPos = entity.blockPosition();
                    consumeItem(itemEntity);
                    return;
                }
            }
        }

        targetLevel = level;
        targetPos = pos;
    }

    protected void getOrCreateObserver(ServerLevel level, BlockPos pos) {
        if(observer == null)
            observer = StateObserverManager.get().getObserver(level, pos, ProtectionRiteObserver.class);
        if(observer == null)
            observer = StateObserverManager.get().addObserver(new ProtectionRiteObserver(level, pos,
                    radius + 1, radius + 1, radius + 1, blocksPlayers ? EBlocks.PROTECTION_BARRIER_BLOCKING.get() : EBlocks.PROTECTION_BARRIER.get(), radius));
    }

}

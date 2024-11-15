package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.client.particles.types.DoubleOptions;
import net.favouriteless.enchanted.common.blocks.EBlocks;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.stateobservers.ProtectionRiteObserver;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class ProtectionRite extends Rite {

    private final int radius;
    private final int duration;
    private final boolean blocksPlayers;

    protected ProtectionRiteObserver observer = null;
    protected ServerLevel targetLevel = null;
    protected BlockPos targetPos = null;

    public ProtectionRite(BaseRiteParams params, int radius, int duration, boolean blocking) {
        super(params);
        this.radius = radius;
        this.duration = duration;
        this.blocksPlayers = blocking;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        findTargetLocation(level, pos, consumedItems);
        if(targetLevel == null || targetPos == null)
            return cancel();

        Block block = blocksPlayers ? EBlocks.PROTECTION_BARRIER_BLOCKING.get() : EBlocks.PROTECTION_BARRIER.get();
        generateSphere(targetLevel, targetPos, state -> {
            if(!state.getFluidState().isEmpty())
                return state.getFluidState().getType() == Fluids.WATER ? block.defaultBlockState().setValue(BarrierBlock.WATERLOGGED, true) : block.defaultBlockState();
            if(state.isAir())
                return block.defaultBlockState();
            return null;
        });

        getOrCreateObserver(targetLevel, targetPos);
        targetLevel.playSound(null, targetPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.MASTER, 1.0f, 1.0f);
        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        observer.checkChanges();

        if(ticks % 20 == 0) {
            targetLevel.sendParticles(new DoubleOptions(EParticleTypes.PROTECTION_SEED.get(), radius),
                    targetPos.getX()+0.5d, targetPos.getY()+0.6d, targetPos.getZ()+0.5d,
                    1, 0, 0, 0, 0);
        }
        return ticks < duration;
    }

    @Override
    protected void onStop(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable UUID targetUUID, List<ItemStack> consumedItems) {
        targetLevel.playSound(null, targetPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.MASTER, 1.0f, 0.5f);
        StateObserverManager.get().removeObserver(observer);

        generateSphere(targetLevel, targetPos, state -> {
            if(state.is(EBlocks.PROTECTION_BARRIER.get()) || state.is(EBlocks.PROTECTION_BARRIER_BLOCKING.get()))
                return state.getValue(BarrierBlock.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
            return null;
        });

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

    protected void generateSphere(ServerLevel level, BlockPos pos, Function<BlockState, BlockState> stateGetter) {
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

                BlockState state = stateGetter.apply(level.getBlockState(spherePos));
                if(state != null)
                    level.setBlockAndUpdate(spherePos, state);
            }
        }
    }

    protected void findTargetLocation(ServerLevel level, BlockPos pos, List<ItemStack> consumedItems) {
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

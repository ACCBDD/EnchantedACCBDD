package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.client.particles.types.CircleMagicParticleType;
import favouriteless.enchanted.client.particles.types.DoubleParticleType;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
import favouriteless.enchanted.common.stateobservers.ProtectionRiteObserver;
import favouriteless.enchanted.common.util.BlockPosUtils;
import net.favouriteless.stateobserver.api.StateObserverManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Function;

public class ProtectionRite extends LocationTargetRite {

    private final int radius;
    private final int duration;
    private final boolean blocksPlayers;

    protected ProtectionRiteObserver observer = null;

    public ProtectionRite(BaseRiteParams baseParams, RiteParams params, int radius, int duration, boolean blocking) {
        super(baseParams, params);
        this.radius = radius;
        this.duration = duration;
        this.blocksPlayers = blocking;
    }

    @Override
    protected boolean onStart(RiteParams params) {
        super.onStart(params);

        Block block = blocksPlayers ? EnchantedBlocks.PROTECTION_BARRIER_BLOCKING.get() : EnchantedBlocks.PROTECTION_BARRIER.get();
        generateSphere(targetLevel, targetPos, state -> {
            if(!state.getFluidState().isEmpty())
                return state.getFluidState().getType() == Fluids.WATER ? block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true) : block.defaultBlockState();
            if(state.isAir())
                return block.defaultBlockState();
            return null;
        });

        getOrCreateObserver(targetLevel, targetPos);
        targetLevel.playSound(null, targetPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.MASTER, 1.0f, 1.0f);
        return true;
    }

    @Override
    protected boolean onTick(RiteParams params) {
        observer.checkChanges();

        //todo: fix particles?
        if(params.ticks() % 20 == 0) {
            targetLevel.sendParticles(new DoubleParticleType.DoubleParticleData(EnchantedParticleTypes.PROTECTION_SEED.get(), radius),
                    targetPos.getX()+0.5d, targetPos.getY()+0.6d, targetPos.getZ()+0.5d,
                    1, 0, 0, 0, 0);
        }
        return params.ticks() < duration;
    }

    @Override
    protected void onStop(RiteParams params) {
        targetLevel.playSound(null, targetPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.MASTER, 1.0f, 0.5f);
        StateObserverManager.get().removeObserver(observer);

        generateSphere(targetLevel, targetPos, state -> {
            if(state.is(EnchantedBlocks.PROTECTION_BARRIER.get()) || state.is(EnchantedBlocks.PROTECTION_BARRIER_BLOCKING.get()))
                return state.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
            return null;
        });

    }

    @Override
    protected void loadAdditional(CompoundTag tag, ServerLevel level) {
        super.loadAdditional(tag, level);
        getOrCreateObserver(targetLevel, targetPos);
    }

    protected void generateSphere(ServerLevel level, BlockPos pos, Function<BlockState, BlockState> stateGetter) {
        BlockPosUtils.iterableSphereHollow(pos, radius).forEach(spherePos -> {
            BlockState state = stateGetter.apply(level.getBlockState(spherePos));
            if(state != null)
                level.setBlockAndUpdate(spherePos, state);
        });
    }

    protected void getOrCreateObserver(ServerLevel level, BlockPos pos) {
        if(observer == null)
            observer = StateObserverManager.get().getObserver(level, pos, ProtectionRiteObserver.class);
        if(observer == null)
            observer = StateObserverManager.get().addObserver(new ProtectionRiteObserver(level, pos,
                    radius + 1, radius + 1, radius + 1, blocksPlayers ? EnchantedBlocks.PROTECTION_BARRIER_BLOCKING.get() : EnchantedBlocks.PROTECTION_BARRIER.get(), radius));
    }

}

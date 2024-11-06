package net.favouriteless.enchanted.common.blocks.entity;

import net.favouriteless.enchanted.api.power.IPowerConsumer;
import net.favouriteless.enchanted.common.altar.SimplePowerPosHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class ChalkGoldBlockEntity extends BlockEntity implements IPowerConsumer {

    private final SimplePowerPosHolder posHolder;

    public ChalkGoldBlockEntity(BlockPos pos, BlockState state) {
        super(EBlockEntityTypes.CHALK_GOLD.get(), pos, state);
        this.posHolder = new SimplePowerPosHolder(pos);
    }

    public void execute(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(t instanceof ChalkGoldBlockEntity blockEntity) {

        }
    }

    @Override
    public void saveAdditional(CompoundTag tag, Provider registries) {
        tag.put("posHolder", posHolder.serialize());
    }

    @Override
    public void loadAdditional(CompoundTag tag, Provider registries) {
        posHolder.deserialize(tag.getList("posHolder", 10));
    }

    @Override
    public @NotNull IPowerConsumer.IPowerPosHolder getPosHolder() {
        return posHolder;
    }
}

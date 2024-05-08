package com.favouriteless.enchanted.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class TemporaryProtectionBarrierBlock extends BarrierBlock {

	public TemporaryProtectionBarrierBlock() {
		super(Properties.copy(Blocks.BARRIER).noLootTable());
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
		return false;
	}

}

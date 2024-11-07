package net.favouriteless.enchanted.common.blocks;

import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class TemporaryProtectionBarrierBlock extends BarrierBlock {

	public TemporaryProtectionBarrierBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

}

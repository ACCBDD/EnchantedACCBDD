package net.favouriteless.enchanted.common.stateobservers;

import net.favouriteless.stateobserver.api.StateChangeSet.StateChange;
import net.favouriteless.stateobserver.api.StateObserver;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class ProtectionRiteObserver extends StateObserver {

	private final Block block;
	private final int radius;

	public ProtectionRiteObserver(Level level, BlockPos pos, int radiusX, int radiusY, int radiusZ, Block block, int radius) {
		super(level, pos, radiusX, radiusY, radiusZ);
		this.block = block;
		this.radius = radius;
	}

	@Override
	protected void handleChanges() {
		if(!getLevel().isClientSide) {
			for(StateChange change : getChangeSet().getChanges()) { // For all changes

				BlockPos pos = change.pos();
				Vec3 toPos = getPos().getCenter().vectorTo(pos.getCenter());
				Vec3 clamped = toPos.scale((double)radius / Math.round(toPos.length())).add(getPos().getCenter());

				if(!pos.equals(BlockPos.containing(clamped)))
					continue;

				if(change.newState().isAir() || !change.newState().getFluidState().isEmpty())
					getLevel().setBlockAndUpdate(change.pos(), block.defaultBlockState());
			}
		}
	}

	@Override
	public void onInit() {}

	@Override
	public void onRemove() {}

}
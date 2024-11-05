package net.favouriteless.enchanted.common.items;

import net.minecraft.world.item.Item;

// TODO: Update this after rewriting circle magic
public class CircleTalismanItem extends Item {

	public CircleTalismanItem(Properties properties) {
		super(properties);
	}

//	@Override
//	public InteractionResult useOn(UseOnContext context) {
//		Level world = context.getLevel();
//		if(!world.isClientSide) {
//			BlockPos pos = context.getClickedPos().above();
//			ItemStack stack = context.getItemInHand();
//
//
//			if(stack.hasTag()) {
//				CompoundTag nbt = stack.getTag();
//				byte small = nbt.contains("small") ? nbt.getByte("small") : 0;
//				byte medium = nbt.contains("medium") ? nbt.getByte("medium") : 0;
//				byte large = nbt.contains("large") ? nbt.getByte("large") : 0;
//
//				if(small != 0 || medium != 0 || large != 0) {
//					boolean validPlace = world.getBlockState(pos).isAir() && EBlocks.GOLDEN_CHALK.get().canSurvive(null, world, pos);
//					if(validPlace) {
//						if(small != 0 && !CirclePart.SMALL.canPlace(world, pos)) validPlace = false;
//						if(medium != 0 && !CirclePart.MEDIUM.canPlace(world, pos)) validPlace = false;
//						if(large != 0 && !CirclePart.LARGE.canPlace(world, pos)) validPlace = false;
//
//						if(validPlace) {
//							world.setBlockAndUpdate(pos, EBlocks.GOLDEN_CHALK.get().getStateForPlacement(new BlockPlaceContext(context)));
//							if(small != 0)
//								CirclePart.SMALL.place(world, pos, small == 1 ? EBlocks.RITUAL_CHALK.get() : small == 2 ? EBlocks.NETHER_CHALK.get() : EBlocks.OTHERWHERE_CHALK.get(), context);
//							if(medium != 0)
//								CirclePart.MEDIUM.place(world, pos, medium == 1 ? EBlocks.RITUAL_CHALK.get() : medium == 2 ? EBlocks.NETHER_CHALK.get() : EBlocks.OTHERWHERE_CHALK.get(), context);
//							if(large != 0)
//								CirclePart.LARGE.place(world, pos, large == 1 ? EBlocks.RITUAL_CHALK.get() : large == 2 ? EBlocks.NETHER_CHALK.get() : EBlocks.OTHERWHERE_CHALK.get(), context);
//
//							stack.setTag(new CompoundTag());
//
//							return InteractionResult.SUCCESS;
//						}
//					}
//
//					if(context.getPlayer() != null) {
//						context.getPlayer().displayClientMessage(Component.literal("All blocks must be valid spots.").withStyle(ChatFormatting.RED), true);
//						return InteractionResult.PASS;
//					}
//				}
//			}
//		}
//
//		return InteractionResult.FAIL;
//	}

}

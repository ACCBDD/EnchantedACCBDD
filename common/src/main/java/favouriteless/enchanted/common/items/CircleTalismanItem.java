package favouriteless.enchanted.common.items;

import favouriteless.enchanted.common.circle_magic.CircleMagicShape;
import favouriteless.enchanted.common.init.EData;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class CircleTalismanItem extends Item {
	public static final String SHAPE_TAG = "circle_magic_shape_map";

	public CircleTalismanItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();

		if(!level.isClientSide) {
			ItemStack stack = context.getItemInHand();

			if(!(stack.getItem() instanceof CircleTalismanItem))
				return InteractionResult.CONSUME;

			Registry<CircleMagicShape> registry = level.registryAccess().registryOrThrow(EData.CIRCLE_SHAPE_REGISTRY);

			BlockPos clicked = context.getClickedPos();
			BlockPos pos = level.getBlockState(clicked).canBeReplaced() ? clicked : clicked.above();

			if(!stack.getOrCreateTag().contains(SHAPE_TAG))
				return InteractionResult.CONSUME;

			if(stack.getOrCreateTag().getCompound(SHAPE_TAG).isEmpty())
				return InteractionResult.CONSUME;

			Map<ResourceLocation, Block> shapes = new HashMap<>();
            CompoundTag shapesTag = stack.getOrCreateTag().getCompound(SHAPE_TAG);
			for (String key : shapesTag.getAllKeys()) {
				shapes.put(ResourceLocation.tryParse(key), BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(shapesTag.getString(key))));
			}

			boolean valid = level.getBlockState(pos).canBeReplaced() && EnchantedBlocks.GOLDEN_CHALK.get().canSurvive(null, level, pos);
			if(!valid)
				return sendFail(context.getPlayer());

			Map<CircleMagicShape, Block> toPlace = new HashMap<>();
			for(ResourceLocation location : shapes.keySet()) {
				CircleMagicShape shape = registry.get(location);
				if(shape == null)
					continue;

				if(!shape.canPlace(level, pos))
					return sendFail(context.getPlayer());
				else
					toPlace.put(shape, shapes.get(location));
			}
			toPlace.forEach((shape, block) -> shape.place(level, pos, block, context));
			level.setBlockAndUpdate(pos, EnchantedBlocks.GOLDEN_CHALK.get().getStateForPlacement(new BlockPlaceContext(context)));
			stack.getOrCreateTag().put(SHAPE_TAG, new ListTag());
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	protected InteractionResult sendFail(Player player) {
		if(player != null)
			player.displayClientMessage(Component.literal("All blocks must be valid spots.").withStyle(ChatFormatting.RED), true);
		return InteractionResult.CONSUME;
	}

}
package net.favouriteless.enchanted.common.blocks.entity;

import net.favouriteless.enchanted.api.power.IPowerConsumer;
import net.favouriteless.enchanted.api.power.IPowerProvider;
import net.favouriteless.enchanted.api.power.PowerHelper;
import net.favouriteless.enchanted.common.altar.SimplePowerPosHolder;
import net.favouriteless.enchanted.common.menus.SpinningWheelMenu;
import net.favouriteless.enchanted.common.recipes.ERecipeTypes;
import net.favouriteless.enchanted.common.recipes.SpinningRecipe;
import net.favouriteless.enchanted.common.recipes.recipe_inputs.ListInput;
import net.favouriteless.enchanted.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SpinningWheelBlockEntity extends ContainerBlockEntityBase implements IPowerConsumer, MenuProvider, WorldlyContainer {

	private static final int[] INPUT_SLOTS = new int[] { 0, 1, 2 };
	private static final int[] BOTTOM_SLOTS = new int[] { 3 };

	private final RecipeManager.CachedCheck<ListInput, SpinningRecipe> recipeCheck;
	private final SimplePowerPosHolder posHolder;
	private boolean isSpinning = false;

	public static final int SPIN_DURATION = 400;
	private int spinProgress = 0;

	public DataSlot data = new DataSlot() {
		@Override
		public int get() {
			return spinProgress;
		}

		@Override
		public void set(int value) {
			spinProgress = value;
		}
	};

	public SpinningWheelBlockEntity(BlockPos pos, BlockState state) {
		super(EBlockEntityTypes.SPINNING_WHEEL.get(), pos, state, NonNullList.withSize(4, ItemStack.EMPTY));
		this.posHolder = new SimplePowerPosHolder(pos);
		this.recipeCheck = RecipeManager.createCheck(ERecipeTypes.SPINNING.get());
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
		if(t instanceof SpinningWheelBlockEntity be) {
			if(!level.isClientSide) {
				IPowerProvider powerProvider = PowerHelper.tryGetPowerProvider(level, be.posHolder);
				RecipeHolder<SpinningRecipe> holder = be.recipeCheck.getRecipeFor(ListInput.of(be.inventory.subList(0, 3)), level).orElse(null);
				boolean wasSpinning = be.spinProgress > 0;

				if(holder != null) {
					SpinningRecipe recipe = holder.value();
					if(be.canSpin(recipe) && (recipe.getPower() == 0 || powerProvider != null)) {
						if(powerProvider.tryConsumePower((double)recipe.getPower() / SPIN_DURATION)) {
							if(++be.spinProgress == SPIN_DURATION) {
								be.spinProgress = 0;
								be.spin(recipe);
							}
						}
					}
					else
						be.spinProgress = 0;
				}
				if(wasSpinning != be.spinProgress > 0)
					be.updateBlock();
			}
			else {
				if(be.isSpinning)
					be.spinProgress++;
				else
					be.spinProgress = 0;
			}
		}
	}

	/**
	 * Attempt to spin the items in this {@link SpinningWheelBlockEntity}'s input slots.
	 * @param recipe The {@link SpinningRecipe} to use.
	 */
	protected void spin(SpinningRecipe recipe) {
		for(ItemStack recipeStack : recipe.getInputs()) {
			for(int i = 0; i < 3; i++) {
				ItemStack input = inventory.get(i);
				if(ItemUtil.isSameItemPartial(input, recipeStack)) {
					input.shrink(recipeStack.getCount());
					break;
				}
			}
		}

		ItemStack result = recipe.assemble(ListInput.of(inventory.subList(0, 3)), level.registryAccess());
		ItemStack output = inventory.get(3);
		if(output.isEmpty())
			inventory.set(3, result);
		else
			output.grow(result.getCount());
	}

	/**
	 * Check if this {@link SpinningWheelBlockEntity} has enough space for the output of a given recipe.
	 * @param recipe The recipe to check for.
	 * @return True if space is found, otherwise false.
	 */
	protected boolean canSpin(SpinningRecipe recipe) {
		if(recipe != null) {
			ItemStack output = inventory.get(3);
			if(output.isEmpty())
				return true;

			ItemStack result = recipe.assemble(ListInput.of(inventory.subList(0, 3)), level.registryAccess());
			if(ItemStack.isSameItemSameComponents(result, output))
				return output.getCount() + result.getCount() <= output.getMaxStackSize();
		}
		return false;
	}

	@Override
	@NotNull
	public Component getDefaultName() {
		return Component.translatable("container.enchanted.spinning_wheel");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
		return new SpinningWheelMenu(id, playerInventory, this, data);
	}

	@Override
	public void saveAdditional(@NotNull CompoundTag tag, Provider registries) {
		super.saveAdditional(tag, registries);
		tag.put("posHolder", posHolder.serialize());
		tag.putInt("spinProgress", spinProgress);
	}

	@Override
	public void loadAdditional(@NotNull CompoundTag tag, Provider registries) {
		super.loadAdditional(tag, registries);
		if(tag.contains("posHolder"))
			posHolder.deserialize(tag.getList("posHolder", 10));
		if(tag.contains("spinProgress"))
			spinProgress = tag.getInt("spinProgress");
		if(tag.contains("isSpinning"))
			isSpinning = tag.getBoolean("isSpinning");
	}

	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	@NotNull
	public CompoundTag getUpdateTag(Provider registries) {
		CompoundTag nbt = super.getUpdateTag(registries);
		nbt.putBoolean("isSpinning", spinProgress > 0);
		return nbt;
	}

	@Override
	@NotNull
	public IPowerConsumer.IPowerPosHolder getPosHolder() {
		return posHolder;
	}

	public int getSpinProgress() {
		return spinProgress;
	}

	@Override
	public int[] getSlotsForFace(@NotNull Direction face) {
		if(face == Direction.DOWN)
			return BOTTOM_SLOTS;
		else
			return INPUT_SLOTS;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack stack, @Nullable Direction face) {
		return index != 3;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @NotNull ItemStack stack, @Nullable Direction face) {
		return true;
	}

}

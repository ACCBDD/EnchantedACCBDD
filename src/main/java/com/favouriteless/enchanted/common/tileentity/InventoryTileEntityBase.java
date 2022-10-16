/*
 * Copyright (c) 2022. Favouriteless
 * Enchanted, a minecraft mod.
 * GNU GPLv3 License
 *
 *     This file is part of Enchanted.
 *
 *     Enchanted is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Enchanted is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Enchanted.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.favouriteless.enchanted.common.tileentity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public abstract class InventoryTileEntityBase extends RandomizableContainerBlockEntity {

	protected NonNullList<ItemStack> inventoryContents;
	protected IItemHandlerModifiable items = new InvWrapper(this);
	protected LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

	protected int numPlayersUsing;

	public InventoryTileEntityBase(BlockEntityType<?> typeIn, NonNullList<ItemStack> inventoryContents) {
		super(typeIn);
		this.inventoryContents = inventoryContents;
	}

	@Override
	public int getContainerSize() {
		return inventoryContents.size();
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return inventoryContents;
	}

	@Override
	public void setItems(NonNullList<ItemStack> itemsIn) {
		inventoryContents = itemsIn;
	}

	@Override
	protected abstract Component getDefaultName();

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		inventoryContents = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, inventoryContents);
	}

	@Override
	public void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		ContainerHelper.saveAllItems(nbt, inventoryContents);
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		if(id == 1) {
			numPlayersUsing = type;
			return true;
		} else {
			return super.triggerEvent(id, type);
		}
	}

	@Override
	public void startOpen(Player player) {
		if(!player.isSpectator()) {
			if(numPlayersUsing < 0 ) {
				numPlayersUsing = 0;
			}
			numPlayersUsing++;
			onOpenOrClose();
		}
	}

	@Override
	public void stopOpen(Player player) {
		if(!player.isSpectator()) {
			numPlayersUsing--;
			onOpenOrClose();
		}
	}

	protected void onOpenOrClose() {
		Block block = getBlockState().getBlock();
		level.blockEvent(worldPosition, block, 1, numPlayersUsing);
		level.updateNeighborsAt(worldPosition, block);
	}

	@Override
	public void clearCache() {
		super.clearCache();
		if(itemHandler != null) {
			itemHandler.invalidate();
			itemHandler = null;
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return itemHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		if(itemHandler != null) {
			itemHandler.invalidate();
		}
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		inventoryContents.set(index, stack);
		if (stack.getCount() > getMaxStackSize()) {
			stack.setCount(getMaxStackSize());
		}
	}

}

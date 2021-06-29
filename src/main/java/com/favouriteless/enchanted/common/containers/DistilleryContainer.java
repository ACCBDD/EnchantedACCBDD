package com.favouriteless.enchanted.common.containers;

import com.favouriteless.enchanted.common.tileentity.FurnaceTileEntityBase;
import com.favouriteless.enchanted.core.init.EnchantedBlocks;
import com.favouriteless.enchanted.core.init.EnchantedContainerTypes;
import com.favouriteless.enchanted.core.init.EnchantedItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;

public class DistilleryContainer extends FurnaceContainerBase {

    public DistilleryContainer(final int windowId, final PlayerInventory playerInventory, final FurnaceTileEntityBase tileEntity, final IIntArray furnaceDataIn) {
        super(EnchantedContainerTypes.DISTILLERY.get(),
                windowId,
                tileEntity,
                IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()),
                7,
                furnaceDataIn);

        // Container Inventory
        this.addSlot(new SlotJarInput(tileEntity, 0, 32, 35)); // Jar input
        this.addSlot(new SlotInput(tileEntity, 1, 54, 25)); // Ingredient input
        this.addSlot(new SlotInput(tileEntity, 2, 54, 45)); // Ingredient input
        this.addSlot(new SlotOutput(tileEntity, 3, 127, 7)); // Distillery output
        this.addSlot(new SlotOutput(tileEntity, 4, 127, 26)); // Distillery output
        this.addSlot(new SlotOutput(tileEntity, 5, 127, 45)); // Distillery output
        this.addSlot(new SlotOutput(tileEntity, 6, 127, 64)); // Distillery output

        this.AddInventorySlots(playerInventory);
    }

    public DistilleryContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new IntArray(4));
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable, player, EnchantedBlocks.DISTILLERY.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {

            ItemStack slotItem = slot.getItem();
            itemstack = slotItem.copy();

            if (index <= 4) { // If container slot
                if (!this.moveItemStackTo(slotItem, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }
            } else { // If not a container slot

                if (itemstack.getItem() == EnchantedItems.CLAY_JAR.get()) { // Item is clay jar
                    if (!this.moveItemStackTo(slotItem, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(slotItem, 1, 3, false)) { // Item is in player inventory, attempt to fit
                    return ItemStack.EMPTY;
                } else if (index < 32) { // Item is in main player inventory and cannot fit
                    if (!this.moveItemStackTo(slotItem, 5, 34, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 41) { // Item is in player hotbar and cannot fit
                    if(!this.moveItemStackTo(slotItem, 34, 43, false)) {
                        return ItemStack.EMPTY;
                    }
                }

                if (slotItem.isEmpty()) {
                    slot.set(ItemStack.EMPTY);
                } else {
                    slot.setChanged();
                }

                if (slotItem.getCount() == itemstack.getCount()) {
                    return ItemStack.EMPTY;
                }

                slot.onTake(playerIn, slotItem);
            }
        }
        return super.quickMoveStack(playerIn, index);
    }

}
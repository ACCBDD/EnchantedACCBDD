package favouriteless.enchanted.common.menus;

import favouriteless.enchanted.common.blocks.entity.AltarBlockEntity;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EMenuTypes;
import favouriteless.enchanted.util.MenuUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class AltarMenu extends AbstractContainerMenu {

    public final AltarBlockEntity blockEntity;
    private final ContainerLevelAccess canInteractWithCallable;
    private final ContainerData data;

    public AltarMenu(final int windowId, final AltarBlockEntity blockEntity, ContainerData data) {
        super(EMenuTypes.ALTAR.get(), windowId);
        this.blockEntity = blockEntity;
        this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.data = data;
        addDataSlots(this.data);
    }

    public AltarMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, MenuUtils.getBlockEntity(playerInventory, data, AltarBlockEntity.class), new SimpleContainerData(3));
    }

    public int getCurrentPower() {
        return data.get(0);
    }

    public int getMaxPower() {
        return data.get(1);
    }

    public int getRechargeMultiplier() {
        return data.get(2);
    }

    @Override
    public ItemStack quickMoveStack(Player var1, int var2) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(canInteractWithCallable, player, EnchantedBlocks.ALTAR.get());
    }

}

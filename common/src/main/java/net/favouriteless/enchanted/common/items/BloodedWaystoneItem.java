package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.common.util.WaystoneHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BloodedWaystoneItem extends Item {
    public BloodedWaystoneItem() {
        super(new Properties());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if(stack.hasTag()) {
            CompoundTag nbt = stack.getTag();
            if(nbt.contains(WaystoneHelper.NAME))
                tooltip.add(Component.literal(nbt.getString(WaystoneHelper.NAME)).withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(getDescriptionId(stack)).withStyle(ChatFormatting.RED);
    }
}

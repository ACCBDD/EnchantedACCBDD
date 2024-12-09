package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.util.WaystoneHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BoundWaystoneItem extends Item {
    public BoundWaystoneItem() {
        super(new Properties());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if(stack.getOrCreateTag().contains(WaystoneHelper.X))
            tooltip.add(Component.literal(WaystoneHelper.getPos(stack).toShortString()).withStyle(ChatFormatting.GRAY));
        else
            tooltip.add(Component.translatable("item.enchanted.bound_waystone.not_bound").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(getDescriptionId(stack)).withStyle(ChatFormatting.YELLOW);
    }
}

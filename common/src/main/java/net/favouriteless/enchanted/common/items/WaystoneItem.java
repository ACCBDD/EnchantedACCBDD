package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class WaystoneItem extends Item {

    public WaystoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        if(stack.has(EDataComponents.BLOCK_POS.get()))
            tooltip.add(Component.literal(stack.get(EDataComponents.BLOCK_POS.get()).toShortString()).withStyle(ChatFormatting.GRAY));
        else if(stack.has(EDataComponents.ENTITY_REF.get()))
            tooltip.add(Component.literal(stack.get(EDataComponents.ENTITY_REF.get()).name()).withStyle(ChatFormatting.GRAY));
        else
            tooltip.add(Component.translatable("item.enchanted.bound_waystone.not_bound").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Component getName(ItemStack stack) {
        if(stack.has(EDataComponents.BLOCK_POS.get()))
            return MutableComponent.create(super.getName(stack).getContents()).withStyle(ChatFormatting.YELLOW);
        else if(stack.has(EDataComponents.ENTITY_REF.get()))
            return MutableComponent.create(super.getName(stack).getContents()).withStyle(ChatFormatting.RED);
        else
            return super.getName(stack);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        if(stack.has(EDataComponents.BLOCK_POS.get()))
            return "item.enchanted.bound_waystone";
        else if(stack.has(EDataComponents.ENTITY_REF.get()))
            return "item.enchanted.blooded_waystone";
        else
            return "item.enchanted.waystone";
    }

}

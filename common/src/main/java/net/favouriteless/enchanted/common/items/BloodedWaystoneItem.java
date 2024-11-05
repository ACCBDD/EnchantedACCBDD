package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BloodedWaystoneItem extends Item {

    public BloodedWaystoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        EntityRefData data = stack.get(EDataComponents.ENTITY_REF.get());
        data.name().ifPresent(name -> tooltip.add(Component.literal(name).withStyle(ChatFormatting.GRAY)));
        super.appendHoverText(stack, context, tooltip, flag);
    }

}

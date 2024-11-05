package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

import java.util.List;
import java.util.UUID;

public class TaglockFilledItem extends Item {

    public TaglockFilledItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        EntityRefData data = stack.get(EDataComponents.ENTITY_REF.get());
        data.name().ifPresent(name -> tooltip.add(Component.literal(name).withStyle(ChatFormatting.GRAY)));
        super.appendHoverText(stack, context, tooltip, flag);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack item) {
        return UseAnim.DRINK;
    }

    public static UUID getUUID(ItemStack stack) {
        if(stack.getItem() == EItems.TAGLOCK_FILLED.get()) {
            return stack.get(EDataComponents.ENTITY_REF.get()).uuid().orElse(null);
        }
        return null;
    }

}

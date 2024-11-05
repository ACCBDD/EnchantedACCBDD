package favouriteless.enchanted.common.items;

import favouriteless.enchanted.common.items.component.EDataComponentTypes;
import favouriteless.enchanted.common.items.component.TaglockData;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TaglockFilledItem extends Item {

    public TaglockFilledItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        TaglockData data = stack.get(EDataComponentTypes.TAGLOCK.get());
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
            return stack.get(EDataComponentTypes.TAGLOCK.get()).uuid().orElse(null);
        }
        return null;
    }

}

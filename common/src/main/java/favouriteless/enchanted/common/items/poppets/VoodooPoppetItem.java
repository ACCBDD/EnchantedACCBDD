package favouriteless.enchanted.common.items.poppets;

import favouriteless.enchanted.common.entities.VoodooItemEntity;
import favouriteless.enchanted.common.init.EnchantedDamageTypes;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.poppet.PoppetHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class VoodooPoppetItem extends AbstractPoppetItem {

    public VoodooPoppetItem() {
        super(0.0F, 40, null);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if(PoppetHelper.isBound(stack) && entity instanceof Player player) {
            ItemStack offHand = player.getOffhandItem();

            if(offHand.getItem() == EnchantedItems.BONE_NEEDLE.get()) {
                if(PoppetHelper.isBound(stack)) {
                    if(level instanceof ServerLevel serverLevel) {
                        Player target = PoppetHelper.getBoundPlayer(stack, serverLevel);
                        if(target != null && !target.isCreative()) {
                            target.hurt(EnchantedDamageTypes.source(level, EnchantedDamageTypes.VOODOO, player), 2.0F);
                            stack.hurtAndBreak(2, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                            offHand.shrink(1);
                        }
                    }
                }
            }
            else if(level instanceof ServerLevel serverLevel) {
                Player target = PoppetHelper.getBoundPlayer(stack, serverLevel);
                if(target != null && !target.isCreative()) {
                    target.addDeltaMovement(player.getLookAngle().normalize().scale(1.0D));
                    target.hurtMarked = true;
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                }
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getMainHandItem();
            if(PoppetHelper.isBound(stack)) {
                player.startUsingItem(hand);
                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> toolTip, TooltipFlag flag) {
        if(PoppetHelper.isBound(stack))
            toolTip.add(Component.literal(PoppetHelper.getBoundName(stack)).withStyle(ChatFormatting.RED));
    }

    // This overrides an IForgeItem method.
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    // This overrides an IForgeItem method.
    public Entity createEntity(Level level, Entity item, ItemStack stack) {
        VoodooItemEntity voodoo = new VoodooItemEntity(EnchantedEntityTypes.VOODOO_ITEM.get(), level);
        voodoo.setPos(item.position());
        voodoo.setDeltaMovement(item.getDeltaMovement());
        voodoo.setItem(stack);
        voodoo.setPickUpDelay(40);
        return voodoo;
    }

}

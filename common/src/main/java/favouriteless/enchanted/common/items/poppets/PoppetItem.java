package favouriteless.enchanted.common.items.poppets;

import favouriteless.enchanted.common.items.TaglockFilledItem;
import favouriteless.enchanted.common.poppet.PoppetColour;
import favouriteless.enchanted.common.poppet.PoppetUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class PoppetItem extends Item {

	private final float failRate;
	public final PoppetColour colour;

	public PoppetItem(float failRate, int durability, PoppetColour colour) {
		super(new Properties().durability(durability));
		this.failRate = failRate;
		this.colour = colour;
	}

	public float getFailRate() {
		return this.failRate;
	}

	public void appendHoverText(ItemStack stack, Level level, List<Component> toolTip, TooltipFlag flag) {
		toolTip.add(Component.literal((int)(failRate * 100) + "% Chance to fail").withStyle(ChatFormatting.RED));
		if(PoppetUtils.isBound(stack)) {
			toolTip.add(Component.literal(PoppetUtils.getBoundName(stack)).withStyle(ChatFormatting.GRAY));
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
		if(entity instanceof Player player) {
			ItemStack taglockStack = player.getOffhandItem();

			if(taglockStack.getItem() instanceof TaglockFilledItem) {
				CompoundTag nbt = taglockStack.getOrCreateTag();
				Player target = level.getPlayerByUUID(nbt.getUUID(TaglockFilledItem.TARGET_TAG));

				if(target != null) {
					PoppetUtils.bind(itemStack, target);
					if(!player.isCreative())
						taglockStack.shrink(1);
				}
			}
		}
		return itemStack;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if(hand == InteractionHand.MAIN_HAND) {
			if(!PoppetUtils.isBound(player.getMainHandItem())) {
				ItemStack taglockStack = player.getOffhandItem();
				if(taglockStack.getItem() instanceof TaglockFilledItem) {
					CompoundTag nbt = taglockStack.getOrCreateTag();
					Player target = level.getPlayerByUUID(nbt.getUUID(TaglockFilledItem.TARGET_TAG));

					if(target != null) {
						player.startUsingItem(hand);
						return InteractionResultHolder.consume(player.getMainHandItem());
					}
				}
			}
		}
		return super.use(level, player, hand);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack item) {
		return 32;
	}
}

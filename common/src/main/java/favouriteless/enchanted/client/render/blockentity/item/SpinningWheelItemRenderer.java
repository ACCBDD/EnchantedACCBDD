package favouriteless.enchanted.client.render.blockentity.item;

import favouriteless.enchanted.common.blocks.entity.SpinningWheelBlockEntity;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SpinningWheelItemRenderer extends BlockEntityWithoutLevelRenderer {

	private static SpinningWheelBlockEntity dummyBe;

	public SpinningWheelItemRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
		if(dummyBe == null)
			dummyBe = new SpinningWheelBlockEntity(BlockPos.ZERO, EnchantedBlocks.SPINNING_WHEEL.get().defaultBlockState());

		poseStack.pushPose();
		Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(dummyBe, poseStack, buffer, light, overlay);
		poseStack.popPose();
	}

	public static SpinningWheelItemRenderer getInstance() {
		return new SpinningWheelItemRenderer();
	}

}

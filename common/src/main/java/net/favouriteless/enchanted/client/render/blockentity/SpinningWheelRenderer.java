package net.favouriteless.enchanted.client.render.blockentity;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.client.render.model.ModelLayerLocations;
import net.favouriteless.enchanted.common.blocks.SpinningWheelBlock;
import net.favouriteless.enchanted.common.blocks.entity.SpinningWheelBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class SpinningWheelRenderer implements BlockEntityRenderer<SpinningWheelBlockEntity> {

	public static final ResourceLocation TEXTURE = Enchanted.id("textures/block/spinning_wheel.png");

	private final ModelPart wheel;
	private final ModelPart body;
	private final ModelPart frontArm;

	public SpinningWheelRenderer(Context context) {
		ModelPart root = context.bakeLayer(ModelLayerLocations.SPINNING_WHEEL);
		this.wheel = root.getChild("wheel");
		this.body = root.getChild("body");
		this.frontArm = root.getChild("frontArm");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("wheel", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, 2.0F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-3.0F, -2.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-3.0F, -3.0F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6).addBox(2.0F, -3.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 28).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(18, 28).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(16, 31).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(16, 30).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(5.1F, 14.2F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.396F, 18.7149F, 0.0F));
		Body.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -0.5F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4579F, -0.0037F, 0.0F, 0.0F, 0.0F, 0.2618F));
		Body.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(0, 6).addBox(-2.5F, -0.5F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3718F, -1.2978F, 0.0F, 0.0F, 0.0F, 0.2618F));
		Body.addOrReplaceChild("legS", CubeListBuilder.create().texOffs(28, 26).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 26).addBox(-0.5F, -2.5F, -3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9606F, 2.9883F, 1.5F, 0.0F, 0.0F, -0.1745F));
		Body.addOrReplaceChild("legE", CubeListBuilder.create().texOffs(20, 16).addBox(-0.5F, -3.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9391F, 1.8151F, 0.0F, 0.0F, 0.0F, 0.1309F));
		Body.addOrReplaceChild("armS", CubeListBuilder.create().texOffs(24, 16).addBox(-0.5F, -4.8F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 16).addBox(-0.5F, -4.8F, 1.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-0.5F, -4.8F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.354F, -0.7149F, -1.0F, 0.0F, 0.0F, 0.5672F));
		partdefinition.addOrReplaceChild("frontArm", CubeListBuilder.create().texOffs(20, 28).addBox(-0.5F, -2.75F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 27).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.8123F, 16.6753F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void render(SpinningWheelBlockEntity wheel, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		poseStack.pushPose();
		float rotationYDegrees = wheel.getLevel() != null ? wheel.getBlockState().getValue(SpinningWheelBlock.FACING).getOpposite().toYRot() : 0;

		poseStack.translate(0.5F, 1.5F, 0.5F);
		poseStack.mulPose(Axis.YN.rotationDegrees(rotationYDegrees));
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));

		float spinProgress = wheel.isSpinning() ? wheel.getSpinProgress() + partialTicks : 0;
		float turnFactor = 25;
		float rotationDegreesWheel = spinProgress % turnFactor * 360 / turnFactor;
		float rotationDegreesArm = rotationDegreesWheel * 2;

		VertexConsumer buffer = bufferSource.getBuffer((RenderType.entityTranslucentCull(TEXTURE)));
		body.render(poseStack, buffer, packedLight, packedOverlay);

		this.wheel.zRot = (float)(Math.PI + Math.toRadians(rotationDegreesWheel));
		this.wheel.render(poseStack, buffer, packedLight, packedOverlay);

		frontArm.yRot = (float)(Math.PI + Math.toRadians(rotationDegreesArm));
		frontArm.render(poseStack, buffer, packedLight, packedOverlay);

		poseStack.popPose();
	}

}
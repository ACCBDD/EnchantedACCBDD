package favouriteless.enchanted.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import favouriteless.enchanted.common.blocks.cauldrons.CauldronBlockBase;
import favouriteless.enchanted.common.blocks.entity.CauldronBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class CauldronWaterRenderer<T extends CauldronBlockEntity<?>> implements BlockEntityRenderer<T> {

    public static final ResourceLocation WATER_TEXTURE = new ResourceLocation("block/water_still");

    private static final Vector3f[] positions = new Vector3f[] {
            new Vector3f(0, 0, 0),
            new Vector3f(0, 0, 1),
            new Vector3f(1, 0, 1),
            new Vector3f(1, 0, 0)
    };

    private final int width;

    public CauldronWaterRenderer(int width) {
        this.width = width;
    }

    @Override
    public void render(T be, float partialTicks, PoseStack poseStack, MultiBufferSource renderBuffer, int packedLight, int packedOverlay) {
        BlockState state = be.getLevel().getBlockState(be.getBlockPos());
        if(state.getBlock() instanceof CauldronBlockBase) {
            int waterAmount = be.getWater();
            if(waterAmount > 0) {

                VertexConsumer consumer = renderBuffer.getBuffer(RenderType.translucent());
                TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(WATER_TEXTURE);

                long time = System.currentTimeMillis() - be.startTime;
                float r = be.getRed(time) / 255F;
                float g = be.getGreen(time) / 255F;
                float b = be.getBlue(time) / 255F;
                float a = 160 / 255F;

                poseStack.pushPose();
                poseStack.translate(0, be.getWaterY(state), 0);

                for(int i = 0; i < 4; i++) {
                    Vector3f localPos = positions[i];
                    Vector4f posVector = new Vector4f(localPos.x()/16.0F, localPos.y()/16.0F, localPos.z()/16.0F, 1.0F);
                    poseStack.last().pose().transform(posVector);

                    consumer.vertex(posVector.x(), posVector.y(), posVector.z())
                            .color(r, g, b, a)
                            .uv(sprite.getU(16 * localPos.x), sprite.getU(16 * localPos.z))
                            .uv2(packedLight)
                            .normal(0.0F, 1.0F, 0.0F)
                            .endVertex();
                }

                poseStack.popPose();
            }
        }
    }

}

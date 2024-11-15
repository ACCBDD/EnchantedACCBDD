package net.favouriteless.enchanted.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.favouriteless.enchanted.common.init.EKeybinds;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureManager;
import org.jetbrains.annotations.Nullable;

public class EnchantedClient {

    // Don't make this final, the iris compat sourceset will change it.
    public static ParticleRenderType TRANSLUCENT_NO_CUTOFF = new ParticleRenderType() {
        @Override
        public @Nullable BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
//            RenderSystem.setShader(EShaders.);
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        @Override
        public String toString() {
            return "ENCHANTED_TRANSLUCENT";
        }

    };

    public static void init() {
        EKeybinds.load();
    }

}

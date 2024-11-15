package net.favouriteless.enchanted.client;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.favouriteless.enchanted.platform.ClientServices;
import net.favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.renderer.ShaderInstance;
import org.jetbrains.annotations.Nullable;

public class EShaders {

    public @Nullable ShaderInstance PARTICLE_NO_CUTOFF;

    public static void load() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

//        registry.registerShader(new ShaderInstance(, "particle", VertexFormat.));
    }

}

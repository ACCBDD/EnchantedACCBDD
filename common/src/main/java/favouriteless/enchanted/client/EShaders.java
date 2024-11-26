package favouriteless.enchanted.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import favouriteless.enchanted.platform.ClientServices;
import favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.renderer.ShaderInstance;

import javax.annotation.Nullable;

public class EShaders {

    public static @Nullable ShaderInstance PARTICLE_NO_CUTOFF;

    public static void load() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;
        registry.registerShader("particle", DefaultVertexFormat.PARTICLE, i -> PARTICLE_NO_CUTOFF = i);
    }
}

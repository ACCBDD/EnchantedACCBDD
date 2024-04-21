package com.favouriteless.enchanted.client;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.platform.services.FabricClientRegistryHelper;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraftforge.fml.config.ModConfig.Type;

public class EnchantedFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        new FabricClientRegistryHelper();
        EnchantedClient.init();
        FabricClientRegistry.registerAll();
        ClientRegistry.registerItemModelPredicates();
        ClientRegistry.registerLayerDefinitions();
        ClientRegistry.registerEntityRenderers();
        ClientEventsFabric.register();

        ForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.CLIENT, ClientConfig.SPEC, "enchanted-client.toml");
    }

}

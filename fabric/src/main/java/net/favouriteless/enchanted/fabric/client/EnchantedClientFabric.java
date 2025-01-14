package net.favouriteless.enchanted.fabric.client;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.client.ClientConfig;
import net.favouriteless.enchanted.client.ClientRegistry;
import net.favouriteless.enchanted.client.EnchantedClient;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.favouriteless.enchanted.platform.services.FabricNetworkHelper;
import net.favouriteless.enchanted.platform.services.FabricNetworkHelper.ClientPayloadRegisterable;
import net.neoforged.fml.config.ModConfig.Type;

public class EnchantedClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EnchantedClient.init();
        FabricClientRegistry.registerAll();
        ClientEventsFabric.register();

        NeoForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.CLIENT, ClientConfig.SPEC, "enchanted-client.toml");
    }

}

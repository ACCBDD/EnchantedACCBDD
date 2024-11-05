package favouriteless.enchanted.fabric.client;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.client.ClientConfig;
import favouriteless.enchanted.client.ClientRegistry;
import favouriteless.enchanted.client.EnchantedClient;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig.Type;

public class EnchantedFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EnchantedClient.init();
        FabricClientRegistry.registerAll();
        ClientRegistry.registerItemModelPredicates();
        ClientRegistry.registerLayerDefinitions();
        ClientRegistry.registerEntityRenderers();
        ClientEventsFabric.register();

        NeoForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.CLIENT, ClientConfig.SPEC, "enchanted-client.toml");
    }

}

package net.favouriteless.enchanted.neoforge.client;

import net.favouriteless.enchanted.client.ClientConfig;
import net.favouriteless.enchanted.client.EnchantedClient;
import net.favouriteless.enchanted.common.Enchanted;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;

@Mod(value = Enchanted.MOD_ID, dist = Dist.CLIENT)
public class EnchantedNeoClient {

    public EnchantedNeoClient(IEventBus bus, ModContainer container) {
        EnchantedClient.init();

        container.registerConfig(Type.CLIENT, ClientConfig.SPEC, "enchanted-client.toml");
    }

}

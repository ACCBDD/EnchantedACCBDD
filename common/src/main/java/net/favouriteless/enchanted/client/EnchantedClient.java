package net.favouriteless.enchanted.client;

import net.favouriteless.enchanted.common.init.EnchantedKeybinds;

public class EnchantedClient {

    public static void init() {
        ClientRegistry.register();
        EnchantedKeybinds.load();
        EShaders.load();
    }

}

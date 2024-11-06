package net.favouriteless.enchanted.client;

import net.favouriteless.enchanted.common.init.EKeybinds;

public class EnchantedClient {

    public static void init() {
        ClientRegistry.registerMenuScreens();
        EKeybinds.load();
    }

}

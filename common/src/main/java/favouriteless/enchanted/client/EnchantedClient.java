package favouriteless.enchanted.client;

import favouriteless.enchanted.common.init.EKeybinds;

public class EnchantedClient {

    public static void init() {
        ClientRegistry.register();
        EKeybinds.load();
    }

}

package net.favouriteless.enchanted.platform;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.platform.services.ICommonRegistryHelper;
import net.favouriteless.enchanted.platform.services.INetworkHelper;
import net.favouriteless.enchanted.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class CommonServices {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final ICommonRegistryHelper COMMON_REGISTRY = load(ICommonRegistryHelper.class);
    public static final INetworkHelper NETWORK = load(INetworkHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Enchanted.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

}
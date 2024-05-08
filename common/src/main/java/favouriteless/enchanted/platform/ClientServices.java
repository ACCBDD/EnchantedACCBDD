package favouriteless.enchanted.platform;

import favouriteless.enchanted.platform.services.IClientRegistryHelper;

// Client services are separated because the dedi servers crash.
public class ClientServices {

    public static final IClientRegistryHelper CLIENT_REGISTRY = CommonServices.load(IClientRegistryHelper.class);

}

package net.favouriteless.enchanted.integrations;

import net.irisshaders.iris.api.v0.IrisApi;

public class IrisIntegrations {

    public static boolean isShaderpackLoaded() {
        return IrisApi.getInstance().isShaderPackInUse();
    }

}

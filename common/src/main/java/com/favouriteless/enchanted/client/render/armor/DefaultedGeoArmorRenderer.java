package com.favouriteless.enchanted.client.render.armor;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.items.NonAnimatedArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DefaultedGeoArmorRenderer extends GeoArmorRenderer<NonAnimatedArmorItem> {

    public DefaultedGeoArmorRenderer(String name) {
        super(new DefaultedItemGeoModel<>(Enchanted.location("armor/" + name)));
    }

}
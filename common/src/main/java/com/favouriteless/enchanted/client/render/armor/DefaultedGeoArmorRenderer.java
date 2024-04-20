package com.favouriteless.enchanted.client.render.armor;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.items.EarmuffsItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DefaultedGeoArmorRenderer extends GeoArmorRenderer<EarmuffsItem> {

    public DefaultedGeoArmorRenderer(String name) {
        super(new DefaultedItemGeoModel<>(Enchanted.location(name)));
    }

}
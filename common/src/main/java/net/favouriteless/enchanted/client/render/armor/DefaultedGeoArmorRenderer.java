package net.favouriteless.enchanted.client.render.armor;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.items.NonAnimatedArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DefaultedGeoArmorRenderer extends GeoArmorRenderer<NonAnimatedArmorItem> {

    public DefaultedGeoArmorRenderer(String name) {
        super(new DefaultedItemGeoModel<>(Enchanted.id("armor/" + name)));
    }

}
package favouriteless.enchanted.client.render.entity;

import favouriteless.enchanted.common.Enchanted;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SimpleAnimatedGeoRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {

    public SimpleAnimatedGeoRenderer(Context context, String name) {
        super(context, new DefaultedEntityGeoModel<>(Enchanted.id(name)));
    }

}

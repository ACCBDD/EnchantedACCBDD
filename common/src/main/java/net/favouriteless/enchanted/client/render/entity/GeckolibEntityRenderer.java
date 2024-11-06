package net.favouriteless.enchanted.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeckolibEntityRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {

    public GeckolibEntityRenderer(Context context, EntityType<?> type) {
        super(context, new DefaultedEntityGeoModel<>(BuiltInRegistries.ENTITY_TYPE.getKey(type)));
    }

}

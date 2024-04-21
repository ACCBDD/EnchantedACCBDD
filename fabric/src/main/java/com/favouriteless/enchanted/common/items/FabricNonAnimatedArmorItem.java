package com.favouriteless.enchanted.common.items;

import com.favouriteless.enchanted.client.render.armor.DefaultedGeoArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FabricNonAnimatedArmorItem extends NonAnimatedArmorItem {

    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public FabricNonAnimatedArmorItem(ArmorMaterials material, Type type, String assetPath, Properties properties) {
        super(material, type, assetPath, properties);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private DefaultedGeoArmorRenderer renderer;

            @Override
            public HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<LivingEntity> original) {
                if(renderer == null)
                    renderer = new DefaultedGeoArmorRenderer(assetPath);

                renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

}

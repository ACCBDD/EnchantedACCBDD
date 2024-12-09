package net.favouriteless.enchanted.common.items;

import net.favouriteless.enchanted.client.render.armor.DefaultedGeoArmorRenderer;
import net.favouriteless.enchanted.common.items.NonAnimatedArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ForgeNonAnimatedArmorItem extends NonAnimatedArmorItem {

    public ForgeNonAnimatedArmorItem(ArmorMaterials material, Type type, String assetPath, Properties properties) {
        super(material, type, assetPath, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private DefaultedGeoArmorRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> original) {
                if(renderer == null)
                    renderer = new DefaultedGeoArmorRenderer(assetPath);

                renderer.prepForRender(livingEntity, itemStack, slot, original);

                return renderer;
            }
        });
    }

}

package com.favouriteless.enchanted.mixin.forge.client;

import com.favouriteless.enchanted.common.items.EarmuffsItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.function.Consumer;

@Mixin(EarmuffsItem.class)
public abstract class CEarmuffsItemMixin {

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                                   EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return (HumanoidModel<?>) GeoArmorRenderer.getRenderer(EarmuffsItem.class, livingEntity)
                        .applyEntityStats(original).setCurrentItem(livingEntity, itemStack, equipmentSlot)
                        .applySlot(equipmentSlot);
            }
        });
    }

    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        GeoArmorRenderer renderer = GeoArmorRenderer.getRenderer(EarmuffsItem.class, entity);
        return renderer.getTextureLocation((ArmorItem) stack.getItem()).toString();
    }

}

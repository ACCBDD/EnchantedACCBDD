package com.favouriteless.enchanted.mixin.forge.client;

import com.favouriteless.enchanted.client.render.blockentity.item.SpinningWheelItemRenderer;
import com.favouriteless.enchanted.common.items.SpinningWheelBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(SpinningWheelBlockItem.class)
public abstract class CSpinningWheelBlockItemMixin {

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private SpinningWheelItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(renderer == null)
                    renderer = new SpinningWheelItemRenderer();

                return renderer;
            }
        });
    }

}

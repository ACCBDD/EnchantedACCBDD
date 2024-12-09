package net.favouriteless.enchanted.mixin.forge.common;

import net.favouriteless.enchanted.client.render.blockentity.item.SpinningWheelItemRenderer;
import net.favouriteless.enchanted.common.items.SpinningWheelBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(SpinningWheelBlockItem.class)
public abstract class SpinningWheelBlockItemMixin extends Item {

    public SpinningWheelBlockItemMixin(Properties properties) {
        super(properties);
    }

    @Override
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

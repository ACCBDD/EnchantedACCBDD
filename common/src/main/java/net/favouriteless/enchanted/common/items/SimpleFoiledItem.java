package net.favouriteless.enchanted.common.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SimpleFoiledItem extends Item {

    public SimpleFoiledItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
    
}

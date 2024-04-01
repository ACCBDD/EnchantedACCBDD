package com.favouriteless.enchanted.common.init;

import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import com.favouriteless.enchanted.platform.Services;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantedCreativeTab {

    public static final CreativeModeTab TAB = Services.COMMON_REGISTRY.getCreativeTab("main",
            () -> EnchantedItems.ENCHANTED_BROOMSTICK.get().getDefaultInstance(),
            (items, tab) -> {
                for(Item item : Registry.ITEM)
                    item.fillItemCategory(tab, (NonNullList<ItemStack>)items);

                for(int i = 1; i < 4; i++) {
                    for(String key : new String[] { "small", "medium", "large" }) {
                        ItemStack stack = new ItemStack(EnchantedItems.CIRCLE_TALISMAN.get());
                        CompoundTag nbt = stack.getOrCreateTag();
                        nbt.putInt(key, i);
                        items.add(stack);
                    }
                }
            });

}

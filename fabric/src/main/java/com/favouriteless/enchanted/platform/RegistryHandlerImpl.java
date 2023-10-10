package com.favouriteless.enchanted.platform;

import com.favouriteless.enchanted.Enchanted;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class RegistryHandlerImpl extends RegistryHandler.Impl {

	@Override
	public <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> entry) {
		Registry.register(registry, Enchanted.location(name), entry.get());
		return entry;
	}

	@Override
	public CreativeModeTab registerTab(String id, Supplier<Item> iconSupplier) {
		return null;
	}

}

package com.favouriteless.enchanted.platform.services;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.mixin.fabric.DamageSourceAccessor;
import com.favouriteless.enchanted.platform.JsonDataLoaderWrapper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FabricCommonRegistryHelper implements ICommonRegistryHelper {

	@Override
	public <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> entry) {
		T value = entry.get();
		Registry.register(registry, Enchanted.location(name), value);
		return () -> value;
	}

	@Override
	public <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, TriFunction<Integer, Inventory, FriendlyByteBuf, T> create) {
		return register(BuiltInRegistries.MENU, name, () -> new ExtendedScreenHandlerType<>(create::apply));
	}

	@Override
	public void register(ResourceLocation id, SimpleJsonResourceReloadListener loader) {
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new JsonDataLoaderWrapper(id, loader)); // Fabric impl adds a wrapper for loaders.
	}

	@Override
	public DamageSource getDamageSource(String id, boolean bypassArmour, boolean bypassMagic, boolean bypassInvul, boolean isMagic) {
		DamageSource source = new EnchantedDamageSource(id);
		DamageSourceAccessor accessor = (DamageSourceAccessor)source;

		if(bypassArmour)
			accessor.setBypassArmor();
		if(bypassMagic)
			accessor.setBypassMagic();
		if(bypassInvul)
			accessor.setBypassInvul();
		if(isMagic)
			source.setMagic();

		return source;
	}

	@Override
	public SoundType getSoundType(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
		return new SoundType(volume, pitch, breakSound.get(), stepSound.get(), placeSound.get(), hitSound.get(), fallSound.get());
	}

	@Override
	public CreativeModeTab getCreativeTab(String name, Supplier<ItemStack> iconSupplier, DisplayItemsGenerator itemsGenerator) {
		return FabricItemGroup.builder()
				.title(Component.translatable(Enchanted.MOD_ID + "." + name))
				.icon(iconSupplier)
				.displayItems(itemsGenerator)
				.build();
	}

	@Override
	public void setFlammable(Block block, int igniteOdds, int burnOdds) {
		FlammableBlockRegistry.getDefaultInstance().add(block, igniteOdds, burnOdds);
	}


	private static class EnchantedDamageSource extends DamageSource {

		public EnchantedDamageSource(String string) {
			super(string);
		}

	}

}

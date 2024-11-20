package net.favouriteless.enchanted.platform.services;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries.SyncOption;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.platform.JsonDataLoaderWrapper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FabricCommonRegistryHelper implements ICommonRegistryHelper {

	@Override
	public <C, T extends C> Supplier<T> register(Registry<C> registry, String name, Supplier<T> entry) {
		T value = entry.get();
		Registry.register(registry, Enchanted.id(name), value);
		return () -> value;
	}

	@Override
	public <T extends AbstractContainerMenu, C> Supplier<MenuType<T>> registerMenu(String name, TriFunction<Integer, Inventory, C, T> factory, StreamCodec<? super RegistryFriendlyByteBuf, C> codec) {
		return register(BuiltInRegistries.MENU, name, () -> new ExtendedScreenHandlerType<>(factory::apply, codec));
	}

	@Override
	public <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, BiFunction<Integer, Inventory, T> factory) {
		return register(BuiltInRegistries.MENU, name, () -> new MenuType<>(factory::apply, FeatureFlags.VANILLA_SET));
	}

	@Override
	public void register(ResourceLocation id, SimpleJsonResourceReloadListener loader) {
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new JsonDataLoaderWrapper(id, loader)); // Fabric impl adds a wrapper for loaders.
	}

	@Override
	public SoundType createSoundType(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
		return new SoundType(volume, pitch, breakSound.get(), stepSound.get(), placeSound.get(), hitSound.get(), fallSound.get());
	}

	@Override
	public Supplier<CreativeModeTab> registerCreativeTab(String name, Supplier<ItemStack> iconSupplier, DisplayItemsGenerator itemsGenerator) {
		return register(BuiltInRegistries.CREATIVE_MODE_TAB, name,
				() -> FabricItemGroup.builder()
						.title(Component.translatable("tab." + Enchanted.MOD_ID + "." + name))
						.icon(iconSupplier)
						.displayItems(itemsGenerator)
						.build());
	}

	@Override
	public <T> ResourceKey<Registry<T>> registerDataRegistry(ResourceKey<Registry<T>> key, Codec<T> codec) {
		DynamicRegistries.register(key, codec);
		return key;
	}

	@Override
	public <T> ResourceKey<Registry<T>> registerSyncedDataRegistry(ResourceKey<Registry<T>> key, Codec<T> codec, Codec<T> networkCodec) {
		DynamicRegistries.registerSynced(key, codec, networkCodec);
		return key;
	}

	@Override
	public void setFlammable(Block block, int igniteOdds, int burnOdds) {
		FlammableBlockRegistry.getDefaultInstance().add(block, igniteOdds, burnOdds);
	}

}

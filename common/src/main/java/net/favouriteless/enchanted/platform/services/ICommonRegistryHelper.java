package net.favouriteless.enchanted.platform.services;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface ICommonRegistryHelper {

    /**
     * Register an object into either A.) A vanilla registry (fabric) or B.) A deferred registry (forge).
     *
     * @param registry The registry to be used for this object.
     * @param name The name of the object. Will automatically use Enchanted's namespace.
     * @param entry A supplier providing a *new* instance of the object.
     *
     * @return A {@link Supplier} providing the registered object.
     */
    <C, T extends C> Supplier<T> register(Registry<C> registry, String name, Supplier<T> entry);

    /**
     * Create a {@link MenuType} with extra data and register it via
     * {@link ICommonRegistryHelper#register(Registry, String, Supplier)}.
     *
     * @return A {@link Supplier} providing the registered MenuType.
     */
    <T extends AbstractContainerMenu, C> Supplier<MenuType<T>> registerMenu(String name,
                                                                            TriFunction<Integer, Inventory, C, T> factory,
                                                                            StreamCodec<? super RegistryFriendlyByteBuf, C> codec);

    /**
     * Create a {@link MenuType} and register it via {@link ICommonRegistryHelper#register(Registry, String, Supplier)}.
     *
     * @return A {@link Supplier} providing the registered MenuType.
     */
    <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name,
                                                                            BiFunction<Integer, Inventory, T> factory);

    /**
     * Register a {@link SimpleJsonResourceReloadListener}.
     *
     * @param id ID of the loader, only used by Fabric.
     * @param loader An instance of the ReloadListener.
     */
    void register(ResourceLocation id, SimpleJsonResourceReloadListener loader);

    /**
     * Create a new {@link SoundType}, necessary because Forge's deferred registers cause crashes when the
     * {@link SoundType} tries to load it's {@link SoundEvent}s.
     *
     * @return A new instance of {@link SoundType} with the provided properties.
     */
    SoundType createSoundType(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound,
                              Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound);

    /**
     * Creates a new {@link CreativeModeTab} and registers it.
     *
     * @param name The name of the tab.
     * @param iconSupplier A {@link Supplier} returning the icon of the tab.
     * @param itemsGenerator A {@link DisplayItemsGenerator} used to add items to the tab.
     *
     * @return A new {@link CreativeModeTab} with the specified icon and generator.
     */
    Supplier<CreativeModeTab> registerCreativeTab(String name, Supplier<ItemStack> iconSupplier,
                                                  DisplayItemsGenerator itemsGenerator);

    /**
     * Register a non-synced datapack registry.
     */
    <T> ResourceKey<Registry<T>> registerDataRegistry(ResourceKey<Registry<T>> key, Codec<T> codec);

    /**
     * Register a synced datapack registry.
     */
    <T> ResourceKey<Registry<T>> registerSyncedDataRegistry(ResourceKey<Registry<T>> key, Codec<T> codec, Codec<T> networkCodec);

    /**
     * Register a block as flammable (i.e. can catch on fire).
     */
    void setFlammable(Block block, int igniteOdds, int burnOdds);

}

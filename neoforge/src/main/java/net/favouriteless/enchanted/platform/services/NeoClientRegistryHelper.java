package net.favouriteless.enchanted.platform.services;

import com.mojang.blaze3d.platform.InputConstants.Type;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens.ScreenConstructor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeoClientRegistryHelper implements IClientRegistryHelper {

	public static final List<KeyMapping> KEY_MAPPINGS = new ArrayList<>();
	public static final List<Pair<ModelLayerLocation, Supplier<LayerDefinition>>> LAYER_DEFINITIONS = new ArrayList<>();
	public static final List<MenuFactoryRegisterable<?, ?>> MENU_FACTORY_REGISTERABLES = new ArrayList<>();
	public static final List<ShaderInstanceRegisterable> SHADER_INSTANCES = new ArrayList<>();

	@Override
	public <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> constructor) {
		EntityRenderers.register(type, constructor);
	}

	@Override
	public <T extends BlockEntity> void register(BlockEntityType<? extends T> type, BlockEntityRendererProvider<T> constructor) {
		BlockEntityRenderers.register(type, constructor);
	}

	@Override
	public KeyMapping register(String name, int keyCode, String category, IClientRegistryHelper.KeyConflictContext conflictContext) {
		KeyMapping mapping = new KeyMapping(name, net.neoforged.neoforge.client.settings.KeyConflictContext.values()[conflictContext.ordinal()], Type.KEYSYM, keyCode, category);
		KEY_MAPPINGS.add(mapping);
		return mapping;
	}

	@Override
	public void register(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
		LAYER_DEFINITIONS.add(Pair.of(layerLocation, supplier));
	}

	@Override
	public void register(Item item, ResourceLocation location, ClampedItemPropertyFunction function) {
		ItemProperties.register(item, location, function);
	}

	@Override
	public <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<M> type, ScreenConstructor<M, U> factory) {
		MENU_FACTORY_REGISTERABLES.add(new MenuFactoryRegisterable<>(type, factory));
	}

	@Override
	public void registerShader(String name, VertexFormat vertexFormat, Consumer<ShaderInstance> consumer) {
		SHADER_INSTANCES.add(new ShaderInstanceRegisterable(name, vertexFormat, consumer));
	}

	public record MenuFactoryRegisterable<M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>>(MenuType<M> type, ScreenConstructor<M, U> factory) {

		public void register(RegisterMenuScreensEvent event) {
			event.register(type, factory);
		}

	}

	public record ShaderInstanceRegisterable(String name, VertexFormat vertexFormat, Consumer<ShaderInstance> loadCallback) {}

}

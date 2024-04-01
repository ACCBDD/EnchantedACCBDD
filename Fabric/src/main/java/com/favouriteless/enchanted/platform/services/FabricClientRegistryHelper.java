package com.favouriteless.enchanted.platform.services;

import com.favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.function.Supplier;

public class FabricClientRegistryHelper implements IClientRegistryHelper {

	@Override
	public <T extends Entity> void register(EntityType<T> type, EntityRendererProvider<T> constructor) {
		EntityRendererRegistry.register(type, constructor);
	}

	@Override
	public <T extends BlockEntity> void register(BlockEntityType<T> type, BlockEntityRendererProvider<T> constructor) {
		BlockEntityRenderers.register(type, constructor);
	}

	@Override
	public KeyMapping register(String name, int keyCode, String category, KeyConflictContext conflictContext) {
		return new KeyMapping(name, keyCode, category);
	}

	@Override
	public void register(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
		EntityModelLayerRegistry.registerModelLayer(layerLocation, supplier::get);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void register(Class<? extends ArmorItem> clazz, Supplier<GeoArmorRenderer> rendererSupplier, Item... items) {
		GeoArmorRenderer.registerArmorRenderer(rendererSupplier.get(), items);
	}

}
package net.favouriteless.enchanted.platform.services;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FabricClientRegistryHelper implements IClientRegistryHelper {

	public static final List<ShaderInstanceRegisterable> SHADER_INSTANCE_REGISTERABLES = new ArrayList<>();


	@Override
	public <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> constructor) {
		EntityRendererRegistry.register(type, constructor);
	}

	@Override
	public <T extends BlockEntity> void register(BlockEntityType<? extends T> type, BlockEntityRendererProvider<T> constructor) {
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
	public void register(Item item, ResourceLocation location, ClampedItemPropertyFunction function) {
		ItemProperties.register(item, location, function);
	}

	@Override
	public void registerShader(String name, VertexFormat vertexFormat, Consumer<ShaderInstance> consumer) {
		SHADER_INSTANCE_REGISTERABLES.add(new ShaderInstanceRegisterable(name, vertexFormat, consumer));
	}

	public record ShaderInstanceRegisterable(String name, VertexFormat vertexFormat, Consumer<ShaderInstance> loadCallback) {}

}

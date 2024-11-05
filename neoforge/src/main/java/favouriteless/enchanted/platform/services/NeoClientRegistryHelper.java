package favouriteless.enchanted.platform.services;

import com.mojang.blaze3d.platform.InputConstants.Type;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
import java.util.function.Supplier;

public class NeoClientRegistryHelper implements IClientRegistryHelper {

	public static final List<KeyMapping> KEY_MAPPINGS = new ArrayList<>();
	public static final List<Pair<ModelLayerLocation, Supplier<LayerDefinition>>> LAYER_DEFINITIONS = new ArrayList<>();

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

}

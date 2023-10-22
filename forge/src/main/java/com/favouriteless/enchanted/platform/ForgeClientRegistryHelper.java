package com.favouriteless.enchanted.platform;

import com.favouriteless.enchanted.platform.services.IClientRegistryHelper;
import com.mojang.blaze3d.platform.InputConstants.Type;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.List;

public class ForgeClientRegistryHelper implements IClientRegistryHelper {

	public static final List<KeyMapping> KEY_MAPPINGS = new ArrayList<>();

	@Override
	public <T extends Entity> void register(EntityType<T> type, EntityRendererProvider<T> constructor) {
		EntityRenderers.register(type, constructor);
	}

	@Override
	public <T extends BlockEntity> void register(BlockEntityType<T> type, BlockEntityRendererProvider<T> constructor) {
		BlockEntityRenderers.register(type, constructor);
	}

	@Override
	public KeyMapping register(String name, int keyCode, String category, IClientRegistryHelper.KeyConflictContext conflictContext) {
		KeyMapping mapping = new KeyMapping(name, net.minecraftforge.client.settings.KeyConflictContext.values()[conflictContext.ordinal()], Type.KEYSYM, keyCode, category);
		KEY_MAPPINGS.add(mapping);
		return mapping;
	}

}
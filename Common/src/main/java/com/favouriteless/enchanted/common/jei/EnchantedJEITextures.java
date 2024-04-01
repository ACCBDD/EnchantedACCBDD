package com.favouriteless.enchanted.common.jei;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.rites.CirclePart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class EnchantedJEITextures {

	private static final Map<CirclePart, String> CIRCLE_PART_TEXTURE_NAMES = new HashMap<>();
	private static final Map<Block, String> BLOCK_TEXTURE_SUFFIXES = new HashMap<>();

	public static void registerCirclePartPrefix(CirclePart part, String name) {
		CIRCLE_PART_TEXTURE_NAMES.putIfAbsent(part, name);
	}

	public static void registerBlockSuffix(Block block, String name) {
		BLOCK_TEXTURE_SUFFIXES.putIfAbsent(block, name);
	}

	public static ResourceLocation getCircleTextureLocation(CirclePart circle, Block block) {
		return (CIRCLE_PART_TEXTURE_NAMES.containsKey(circle) && BLOCK_TEXTURE_SUFFIXES.containsKey(block)) ?
				Enchanted.location("textures/gui/jei/" + CIRCLE_PART_TEXTURE_NAMES.get(circle) + "_" + BLOCK_TEXTURE_SUFFIXES.get(block) + ".png") : null;
	}

}
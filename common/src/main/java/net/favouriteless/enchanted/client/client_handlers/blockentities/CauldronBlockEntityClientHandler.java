package net.favouriteless.enchanted.client.client_handlers.blockentities;

import net.favouriteless.enchanted.common.blocks.entity.CauldronBlockEntity;
import net.favouriteless.enchanted.common.sounds.CauldronBubblingSoundInstance;
import net.minecraft.client.Minecraft;

public class CauldronBlockEntityClientHandler {

	public static void startCauldronBubbling(CauldronBlockEntity<?> cauldron) {
		Minecraft.getInstance().getSoundManager().play(new CauldronBubblingSoundInstance(cauldron));
	}

}

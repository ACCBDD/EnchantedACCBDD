package favouriteless.enchanted.client.client_handlers.blockentities;

import favouriteless.enchanted.common.blocks.entity.CauldronBlockEntity;
import favouriteless.enchanted.common.sounds.CauldronBubblingSoundInstance;
import net.minecraft.client.Minecraft;

public class CauldronBlockEntityClientHandler {

	public static void startCauldronBubbling(CauldronBlockEntity<?> cauldron) {
		Minecraft.getInstance().getSoundManager().play(new CauldronBubblingSoundInstance(cauldron));
	}

}
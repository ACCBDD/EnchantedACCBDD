package favouriteless.enchanted.common.init;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.platform.ClientServices;
import favouriteless.enchanted.platform.services.IClientRegistryHelper.KeyConflictContext;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class EKeybinds {

	public static final String CATEGORY_BROOMSTICK = "key.enchanted.categories.broomstick";

	public static final KeyMapping BROOM_AIM_UP = ClientServices.CLIENT_REGISTRY.register(getKeyName("broom_aim_up"), InputConstants.KEY_C, CATEGORY_BROOMSTICK, KeyConflictContext.IN_GAME);
	public static final KeyMapping BROOM_AIM_DOWN = ClientServices.CLIENT_REGISTRY.register(getKeyName("broom_aim_down"), InputConstants.KEY_V, CATEGORY_BROOMSTICK, KeyConflictContext.IN_GAME);



	private static String getKeyName(String name) {
		return "key." + Enchanted.MOD_ID + "." + name;
	}

	public static void load() {}

}

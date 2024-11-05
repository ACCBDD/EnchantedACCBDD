package favouriteless.enchanted.common;

import favouriteless.enchanted.common.blocks.EBlocks;
import favouriteless.enchanted.common.blocks.entity.EBlockEntityTypes;
import favouriteless.enchanted.common.effects.EEffects;
import favouriteless.enchanted.common.entities.EEntityTypes;
import favouriteless.enchanted.common.init.ECreativeTab;
import favouriteless.enchanted.common.init.EData;
import favouriteless.enchanted.common.init.EParticleTypes;
import favouriteless.enchanted.common.items.EItems;
import favouriteless.enchanted.common.items.component.EDataComponentTypes;
import favouriteless.enchanted.common.menus.EMenuTypes;
import favouriteless.enchanted.common.network.EPackets;
import favouriteless.enchanted.common.recipes.ERecipeTypes;
import favouriteless.enchanted.common.sounds.ESoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Enchanted {

    public static final String MOD_ID = "enchanted";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static final Random RANDOM = new Random();
    public static final RandomSource RANDOMSOURCE = RandomSource.create();


    public static void init() {
        EPackets.register();
        loadRegistries();
    }

    public static void loadRegistries() {
        ESoundEvents.load();
        EItems.load();
        EDataComponentTypes.load();
        EBlocks.load();
        EBlockEntityTypes.load();
        EEntityTypes.load();
        EEffects.load();
        EParticleTypes.load();
        EMenuTypes.load();
        ERecipeTypes.load();
        EData.load();
        ECreativeTab.load();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static String translationKey(String prefix, String suffix) {
        return String.format("%s.%s.%s", prefix, MOD_ID, suffix);
    }

}
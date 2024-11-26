package favouriteless.enchanted.common;

import favouriteless.enchanted.common.circle_magic.ERiteFactories;
import favouriteless.enchanted.common.init.EnchantedCreativeTab;
import favouriteless.enchanted.common.init.EData;
import favouriteless.enchanted.common.init.registry.*;
import favouriteless.enchanted.common.network.EnchantedPackets;
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
        EnchantedPackets.register();
        Enchanted.loadRegistries();
    }

    public static void loadRegistries() {
        EnchantedSoundEvents.load();
        EItems.load();
        EnchantedBlocks.load();
        EnchantedBlockEntityTypes.load();
        EnchantedEntityTypes.load();
        EnchantedEffects.load();
        EParticleTypes.load();
        EMenuTypes.load();
        ERecipeTypes.load();
        EData.load();
        EnchantedCreativeTab.load();
        ERiteFactories.load();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String translationKey(String prefix, String suffix) {
        return String.format("%s.%s.%s", prefix, MOD_ID, suffix);
    }

}
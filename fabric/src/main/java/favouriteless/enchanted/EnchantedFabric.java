package favouriteless.enchanted;

import favouriteless.enchanted.common.CommonConfig;
import favouriteless.enchanted.common.CommonEventsFabric;
import favouriteless.enchanted.common.altar.PowerProvider;
import favouriteless.enchanted.common.altar.AltarUpgrade;
import favouriteless.enchanted.common.blocks.EnchantedLog;
import favouriteless.enchanted.common.entities.FamiliarCat;
import favouriteless.enchanted.common.entities.Mandrake;
import favouriteless.enchanted.common.init.EnchantedData;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraftforge.fml.config.ModConfig.Type;

public class EnchantedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Enchanted.init();
        registerEntityAttributes();
        CommonEventsFabric.register();
        registerDataRegistries();

        EnchantedItems.registerCompostables();
        registerStrippables();
        EnchantedBlocks.registerFlammables();

        ForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");
    }

    private static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EnchantedEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        FabricDefaultAttributeRegistry.register(EnchantedEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    private static void registerDataRegistries() {
        DynamicRegistries.register(EnchantedData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        DynamicRegistries.register(EnchantedData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        DynamicRegistries.register(EnchantedData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(EnchantedBlocks.ALDER_LOG.get(), EnchantedBlocks.STRIPPED_ALDER_LOG.get());
        StrippableBlockRegistry.register(EnchantedBlocks.HAWTHORN_LOG.get(), EnchantedBlocks.STRIPPED_HAWTHORN_LOG.get());
        StrippableBlockRegistry.register(EnchantedBlocks.ROWAN_LOG.get(), EnchantedBlocks.STRIPPED_ROWAN_LOG.get());
    }

}

package net.favouriteless.enchanted;

import net.favouriteless.enchanted.common.CommonConfig;
import net.favouriteless.enchanted.common.CommonEventsFabric;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.altar.AltarUpgrade;
import net.favouriteless.enchanted.common.altar.PowerProvider;
import net.favouriteless.enchanted.common.entities.FamiliarCat;
import net.favouriteless.enchanted.common.entities.Mandrake;
import net.favouriteless.enchanted.common.init.EData;
import net.favouriteless.enchanted.common.init.registry.EBlocks;
import net.favouriteless.enchanted.common.init.registry.EEntityTypes;
import net.favouriteless.enchanted.common.init.registry.EItems;
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

        EItems.registerCompostables();
        registerStrippables();
        EBlocks.registerFlammables();

        ForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");
    }

    private static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        FabricDefaultAttributeRegistry.register(EEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    private static void registerDataRegistries() {
        DynamicRegistries.register(EData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        DynamicRegistries.register(EData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        DynamicRegistries.register(EData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(EBlocks.ALDER_LOG.get(), EBlocks.STRIPPED_ALDER_LOG.get());
        StrippableBlockRegistry.register(EBlocks.HAWTHORN_LOG.get(), EBlocks.STRIPPED_HAWTHORN_LOG.get());
        StrippableBlockRegistry.register(EBlocks.ROWAN_LOG.get(), EBlocks.STRIPPED_ROWAN_LOG.get());
    }

}

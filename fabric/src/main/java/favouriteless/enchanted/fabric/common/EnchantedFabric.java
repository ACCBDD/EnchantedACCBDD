package favouriteless.enchanted.fabric.common;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.CommonConfig;
import favouriteless.enchanted.common.altar.AltarUpgrade;
import favouriteless.enchanted.common.altar.PowerProvider;
import favouriteless.enchanted.common.entities.FamiliarCat;
import favouriteless.enchanted.common.entities.Mandrake;
import favouriteless.enchanted.common.init.EData;
import favouriteless.enchanted.common.init.registry.EBlocks;
import favouriteless.enchanted.common.init.registry.EEntityTypes;
import favouriteless.enchanted.common.items.EItems;
import favouriteless.enchanted.common.rites.RiteRequirements;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.neoforged.fml.config.ModConfig.Type;

public class EnchantedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Enchanted.init();
        registerEntityAttributes();
        CommonEventsFabric.register();
        registerDataRegistries();

        EItems.registerCompostables();
        registerStrippables();
        EBlocks.registerFlammables();

        NeoForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");
    }

    private static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        FabricDefaultAttributeRegistry.register(EEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    private static void registerDataRegistries() {
        DynamicRegistries.register(EData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        DynamicRegistries.register(EData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        DynamicRegistries.register(EData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
        DynamicRegistries.registerSynced(EData.RITE_REQUIREMENTS_REGISTRY, RiteRequirements.CODEC);
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(EBlocks.ALDER_LOG.get(), EBlocks.STRIPPED_ALDER_LOG.get());
        StrippableBlockRegistry.register(EBlocks.HAWTHORN_LOG.get(), EBlocks.STRIPPED_HAWTHORN_LOG.get());
        StrippableBlockRegistry.register(EBlocks.ROWAN_LOG.get(), EBlocks.STRIPPED_ROWAN_LOG.get());
    }

}

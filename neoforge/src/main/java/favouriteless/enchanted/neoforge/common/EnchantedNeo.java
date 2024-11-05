package favouriteless.enchanted.neoforge.common;

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
import favouriteless.enchanted.platform.services.NeoCommonRegistryHelper;
import favouriteless.enchanted.platform.services.NeoNetworkHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@Mod(Enchanted.MOD_ID)
public class EnchantedNeo {
    
    public EnchantedNeo(IEventBus bus, ModContainer container) {
        NeoCommonRegistryHelper.TAB_REGISTRY.register(bus);
        Enchanted.init();

        container.registerConfig(Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");

        NeoCommonRegistryHelper.getRegistryMap().register(bus);
        bus.addListener(NeoNetworkHelper::registerPayloads);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        event.put(EEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    public static void commonSetup(FMLCommonSetupEvent events) {
        EItems.registerCompostables();
        EBlocks.registerFlammables();
    }

    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(EData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        event.dataPackRegistry(EData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        event.dataPackRegistry(EData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
        event.dataPackRegistry(EData.RITE_REQUIREMENTS_REGISTRY, RiteRequirements.CODEC, RiteRequirements.CODEC);
    }

}
package favouriteless.enchanted;

import favouriteless.enchanted.client.ClientConfig;
import favouriteless.enchanted.common.CommonConfig;
import favouriteless.enchanted.common.altar.AltarUpgrade;
import favouriteless.enchanted.common.altar.PowerProvider;
import favouriteless.enchanted.common.entities.FamiliarCat;
import favouriteless.enchanted.common.entities.Mandrake;
import favouriteless.enchanted.common.init.EnchantedData;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.rites.RiteRequirements;
import favouriteless.enchanted.platform.services.ForgeCommonRegistryHelper;
import favouriteless.enchanted.platform.services.NeoNetworkHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.common.Mod;

@Mod(Enchanted.MOD_ID)
public class EnchantedNeo {
    
    public EnchantedNeo(IEventBus bus, ModContainer container) {
        ForgeCommonRegistryHelper.TAB_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");
        ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.SPEC, "enchanted-client.toml");
        Enchanted.init();

        bus.addListener(NeoNetworkHelper::registerPayloads);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EnchantedEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        event.put(EnchantedEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    public static void commonSetup(FMLCommonSetupEvent events) {
        EnchantedItems.registerCompostables();
        EnchantedBlocks.registerFlammables();
    }

    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(EnchantedData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        event.dataPackRegistry(EnchantedData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        event.dataPackRegistry(EnchantedData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
        event.dataPackRegistry(EnchantedData.RITE_REQUIREMENTS_REGISTRY, RiteRequirements.CODEC, RiteRequirements.CODEC);
    }

}
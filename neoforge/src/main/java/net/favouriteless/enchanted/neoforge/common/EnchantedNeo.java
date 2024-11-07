package net.favouriteless.enchanted.neoforge.common;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.CommonConfig;
import net.favouriteless.enchanted.common.altar.AltarUpgrade;
import net.favouriteless.enchanted.common.altar.PowerProvider;
import net.favouriteless.enchanted.common.blocks.entity.EBlockEntityTypes;
import net.favouriteless.enchanted.common.entities.FamiliarCat;
import net.favouriteless.enchanted.common.entities.Mandrake;
import net.favouriteless.enchanted.common.init.EData;
import net.favouriteless.enchanted.common.blocks.EBlocks;
import net.favouriteless.enchanted.common.entities.EEntityTypes;
import net.favouriteless.enchanted.common.items.EItems;
import net.favouriteless.enchanted.platform.services.NeoCommonRegistryHelper;
import net.favouriteless.enchanted.platform.services.NeoNetworkHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@Mod(Enchanted.MOD_ID)
@EventBusSubscriber(modid = Enchanted.MOD_ID, bus = Bus.MOD)
public class EnchantedNeo {
    
    public EnchantedNeo(IEventBus bus, ModContainer container) {
        NeoCommonRegistryHelper.TAB_REGISTRY.register(bus);
        Enchanted.init();

        container.registerConfig(Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");

        NeoCommonRegistryHelper.getRegistryMap().register(bus);
        bus.addListener(NeoNetworkHelper::registerPayloads);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent events) {
        EItems.registerCompostables();
        EBlocks.registerFlammables();
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        event.put(EEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(EData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        event.dataPackRegistry(EData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        event.dataPackRegistry(EData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
//        event.dataPackRegistry(EData.RITE_REQUIREMENTS_REGISTRY, RiteRequirements.CODEC, RiteRequirements.CODEC);
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EBlockEntityTypes.WITCH_OVEN.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EBlockEntityTypes.DISTILLERY.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EBlockEntityTypes.SPINNING_WHEEL.get(), SidedInvWrapper::new);
    }

}
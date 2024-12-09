package net.favouriteless.enchanted;

import net.favouriteless.enchanted.client.ClientConfig;
import net.favouriteless.enchanted.common.CommonConfig;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.entities.FamiliarCat;
import net.favouriteless.enchanted.common.entities.Mandrake;
import net.favouriteless.enchanted.common.init.registry.EItems;
import net.favouriteless.enchanted.common.init.registry.EBlocks;
import net.favouriteless.enchanted.common.init.registry.EEntityTypes;
import net.favouriteless.enchanted.platform.services.ForgeCommonRegistryHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataPackRegistryEvent;

@Mod(Enchanted.MOD_ID)
@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.MOD)
public class EnchantedForge {
    
    public EnchantedForge() {
        ForgeCommonRegistryHelper.TAB_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");
        ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.SPEC, "enchanted-client.toml");
        Enchanted.init();
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        event.put(EEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent events) {
        EItems.registerCompostables();
        EBlocks.registerFlammables();
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        Enchanted.LOG.debug("loaded datapack registries! dadfadf");
        for (ForgeCommonRegistryHelper.DataRegistryRegisterable<?> registerable : ForgeCommonRegistryHelper.dataRegistryRegisterables) {
            Enchanted.LOG.debug("loaded datapack registries! 2dazaxz");
            registerable.register(event);
        }
    }

}
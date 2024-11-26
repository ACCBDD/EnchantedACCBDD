package favouriteless.enchanted;

import favouriteless.enchanted.client.ClientConfig;
import favouriteless.enchanted.common.CommonConfig;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.altar.PowerProvider;
import favouriteless.enchanted.common.altar.AltarUpgrade;
import favouriteless.enchanted.common.entities.FamiliarCat;
import favouriteless.enchanted.common.entities.Mandrake;
import favouriteless.enchanted.common.init.EnchantedData;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import favouriteless.enchanted.common.init.registry.EItems;
import favouriteless.enchanted.common.circle_magic.rites.RiteRequirements;
import favouriteless.enchanted.platform.services.ForgeCommonRegistryHelper;
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
        event.put(EnchantedEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        event.put(EnchantedEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent events) {
        EItems.registerCompostables();
        EnchantedBlocks.registerFlammables();
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(EnchantedData.ALTAR_UPGRADE_REGISTRY, AltarUpgrade.CODEC);
        event.dataPackRegistry(EnchantedData.ALTAR_BLOCK_REGISTRY, PowerProvider.BLOCK_CODEC);
        event.dataPackRegistry(EnchantedData.ALTAR_TAG_REGISTRY, PowerProvider.TAG_CODEC);
        event.dataPackRegistry(EnchantedData.RITE_REQUIREMENTS_REGISTRY, RiteRequirements.CODEC, RiteRequirements.CODEC);
    }

}
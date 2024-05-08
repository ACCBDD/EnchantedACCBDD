package favouriteless.enchanted;

import favouriteless.enchanted.client.ClientConfig;
import favouriteless.enchanted.common.CommonConfig;
import favouriteless.enchanted.common.entities.FamiliarCat;
import favouriteless.enchanted.common.entities.Mandrake;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import favouriteless.enchanted.platform.services.ForgeCommonRegistryHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

}
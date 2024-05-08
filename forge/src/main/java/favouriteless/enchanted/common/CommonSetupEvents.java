package favouriteless.enchanted.common;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.MOD)
public class CommonSetupEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent events) {
        EnchantedItems.registerCompostables();
        EnchantedBlocks.registerFlammables();
    }

}

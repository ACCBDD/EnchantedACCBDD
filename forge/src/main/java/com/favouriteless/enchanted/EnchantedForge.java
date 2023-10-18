package com.favouriteless.enchanted;

import com.favouriteless.enchanted.platform.RegistryHandlerImpl;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Enchanted.MOD_ID)
@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.MOD)
public class EnchantedForge {
    
    public EnchantedForge() {
        new RegistryHandlerImpl();
        Enchanted.init();
    }

    @SubscribeEvent
    public static void onRegistry(RegisterEvent event) {
        Enchanted.loadRegistries();
    }

}
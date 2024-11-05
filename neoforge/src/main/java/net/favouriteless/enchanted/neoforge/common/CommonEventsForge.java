package net.favouriteless.enchanted.neoforge.common;

import net.favouriteless.enchanted.common.CommonEvents;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.effects.EffectEvents;
import net.favouriteless.enchanted.common.poppet.PoppetEvents;
import net.favouriteless.enchanted.platform.services.NeoCommonRegistryHelper;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.entity.player.PlayerDestroyItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import static net.neoforged.fml.common.EventBusSubscriber.Bus.GAME;

@EventBusSubscriber(modid = Enchanted.MOD_ID, bus= GAME)
public class CommonEventsForge {

    @SubscribeEvent
    public static void onPlayerItemBreak(PlayerDestroyItemEvent event) {
        PoppetEvents.onPlayerItemBreak(event.getEntity(), event.getOriginal(), event.getHand());
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
        if(EffectEvents.onLivingHurt(event.getEntity(), event.getSource(), event.getOriginalDamage())) {
            event.setNewDamage(0);
            return;
        }
        if(PoppetEvents.onLivingEntityHurt(event.getEntity(), event.getOriginalDamage(), event.getSource()))
            event.setNewDamage(0);
    }

    @SubscribeEvent
    public static void onPlayerSleep(CanPlayerSleepEvent event) {
        CommonEvents.onPlayerSleeping(event.getEntity(), event.getPos());
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Pre event) {
        CommonEvents.onLevelTick(event.getLevel());
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        CommonEvents.onPlayerLoggedIn(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
        CommonEvents.onPlayerLoggedOut(event.getEntity());
    }

    @SubscribeEvent
    public static void addReloadListenerEvent(AddReloadListenerEvent event) {
        for (SimpleJsonResourceReloadListener loader : NeoCommonRegistryHelper.dataLoaders)
            event.addListener(loader);
    }

}

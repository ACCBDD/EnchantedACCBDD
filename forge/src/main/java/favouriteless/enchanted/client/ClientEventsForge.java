package favouriteless.enchanted.client;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.client.particles.*;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.FORGE, value=Dist.CLIENT)
public class ClientEventsForge {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        if(event.phase == Phase.END)
            ClientEvents.clientTickPost();
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ClientEvents.onItemTooltip(event.getItemStack(), event.getToolTip(), event.getFlags());
    }

    @SubscribeEvent
    public static void onRenderGuiPost(RenderGuiEvent.Post event) {
        ClientEvents.onRenderGui(event.getGuiGraphics(), event.getPartialTick(), event.getWindow());
    }

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        ClientEvents.playSound(event.getSound());
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(EnchantedParticleTypes.BOILING.get(), BoilingParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.CAULDRON_BREW.get(), CauldronBrewParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.CAULDRON_COOK.get(), CauldronCookParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.KETTLE_COOK.get(), KettleCookParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.CIRCLE_MAGIC.get(), CircleMagicParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.POPPET.get(), PoppetParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.IMPRISONMENT_CAGE.get(), ImprisonmentCageParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.IMPRISONMENT_CAGE_SEED.get(), ImprisonmentCageSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.TRANSPOSITION_IRON_SEED.get(), TranspositionIronSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.BROILING_SEED.get(), BroilingSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.SKY_WRATH_SEED.get(), SkyWrathSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.SKY_WRATH.get(), SkyWrathParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.CURSE_SEED.get(), CurseSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.CURSE_BLIGHT_SEED.get(), CurseBlightSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.CURSE_BLIGHT.get(), RepellingParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.REMOVE_CURSE_SEED.get(), RemoveCurseSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.REMOVE_CURSE.get(), RemoveCurseParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.FERTILITY_SEED.get(), FertilitySeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.FERTILITY.get(), RepellingParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.PROTECTION_SEED.get(), ProtectionSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.PROTECTION.get(), ProtectionParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.BIND_FAMILIAR_SEED.get(), BindFamiliarSeedParticle.Factory::new);
        event.registerSpriteSet(EnchantedParticleTypes.BIND_FAMILIAR.get(), BindFamiliarParticle.Factory::new);
    }
}

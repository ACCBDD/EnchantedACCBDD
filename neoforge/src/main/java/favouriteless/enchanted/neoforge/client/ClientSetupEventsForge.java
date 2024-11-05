package favouriteless.enchanted.neoforge.client;

import com.mojang.datafixers.util.Pair;
import favouriteless.enchanted.client.ClientRegistry;
import favouriteless.enchanted.client.EnchantedClient;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.client.particles.*;
import favouriteless.enchanted.common.blocks.EBlocks;
import favouriteless.enchanted.common.init.EParticleTypes;
import favouriteless.enchanted.platform.services.NeoClientRegistryHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Enchanted.MOD_ID, bus = Bus.GAME, value = Dist.CLIENT)
public class ClientSetupEventsForge {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		EnchantedClient.init();

		event.enqueueWork(ClientRegistry::registerItemModelPredicates);
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((a, b, c, d) -> 0xF0F0F0, EBlocks.RITUAL_CHALK.get());
		event.register((a, b, c, d) -> 0x801818, EBlocks.NETHER_CHALK.get());
		event.register((a, b, c, d) -> 0x4F2F78, EBlocks.OTHERWHERE_CHALK.get());
	}

	@SubscribeEvent
	public static void onRegisterKeybinds(RegisterKeyMappingsEvent event) {
		for(KeyMapping mapping : NeoClientRegistryHelper.KEY_MAPPINGS)
			event.register(mapping);
	}

	@SubscribeEvent
	public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		ClientRegistry.registerEntityRenderers();
	}

	@SubscribeEvent
	public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		ClientRegistry.registerLayerDefinitions();
		for(Pair<ModelLayerLocation, Supplier<LayerDefinition>> pair : NeoClientRegistryHelper.LAYER_DEFINITIONS)
			event.registerLayerDefinition(pair.getFirst(), pair.getSecond());
	}

	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EParticleTypes.BOILING.get(), BoilingParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.CAULDRON_BREW.get(), CauldronBrewParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.CAULDRON_COOK.get(), CauldronCookParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.KETTLE_COOK.get(), KettleCookParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.CIRCLE_MAGIC.get(), CircleMagicParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.POPPET.get(), PoppetParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.IMPRISONMENT_CAGE.get(), ImprisonmentCageParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.IMPRISONMENT_CAGE_SEED.get(), ImprisonmentCageSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.TRANSPOSITION_IRON_SEED.get(), TranspositionIronSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.BROILING_SEED.get(), BroilingSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.SKY_WRATH_SEED.get(), SkyWrathSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.SKY_WRATH.get(), SkyWrathParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.CURSE_SEED.get(), CurseSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.CURSE_BLIGHT_SEED.get(), CurseBlightSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.CURSE_BLIGHT.get(), RepellingParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.REMOVE_CURSE_SEED.get(), RemoveCurseSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.REMOVE_CURSE.get(), RemoveCurseParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.FERTILITY_SEED.get(), FertilitySeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.FERTILITY.get(), RepellingParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.PROTECTION_SEED.get(), ProtectionSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.PROTECTION.get(), ProtectionParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.BIND_FAMILIAR_SEED.get(), BindFamiliarSeedParticle.Factory::new);
		event.registerSpriteSet(EParticleTypes.BIND_FAMILIAR.get(), BindFamiliarParticle.Factory::new);
	}

}

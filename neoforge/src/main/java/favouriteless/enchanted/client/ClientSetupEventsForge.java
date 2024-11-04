package favouriteless.enchanted.client;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.client.particles.*;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
import favouriteless.enchanted.platform.services.ForgeClientRegistryHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Supplier;

@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientSetupEventsForge {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		EnchantedClient.init();

		event.enqueueWork(ClientRegistry::registerItemModelPredicates);
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((a, b, c, d) -> 0xF0F0F0, EnchantedBlocks.RITUAL_CHALK.get());
		event.register((a, b, c, d) -> 0x801818, EnchantedBlocks.NETHER_CHALK.get());
		event.register((a, b, c, d) -> 0x4F2F78, EnchantedBlocks.OTHERWHERE_CHALK.get());
	}

	@SubscribeEvent
	public static void onRegisterKeybinds(RegisterKeyMappingsEvent event) {
		for(KeyMapping mapping : ForgeClientRegistryHelper.KEY_MAPPINGS)
			event.register(mapping);
	}

	@SubscribeEvent
	public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		ClientRegistry.registerEntityRenderers();
	}

	@SubscribeEvent
	public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		ClientRegistry.registerLayerDefinitions();
		for(Pair<ModelLayerLocation, Supplier<LayerDefinition>> pair : ForgeClientRegistryHelper.LAYER_DEFINITIONS)
			event.registerLayerDefinition(pair.getKey(), pair.getValue());
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

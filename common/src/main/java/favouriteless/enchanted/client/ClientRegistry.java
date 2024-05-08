package favouriteless.enchanted.client;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.client.particles.*;
import favouriteless.enchanted.client.render.blockentity.CauldronWaterRenderer;
import favouriteless.enchanted.client.render.blockentity.PoppetShelfRenderer;
import favouriteless.enchanted.client.render.blockentity.SpinningWheelRenderer;
import favouriteless.enchanted.client.render.entity.BroomstickRenderer;
import favouriteless.enchanted.client.render.entity.FamiliarCatRenderer;
import favouriteless.enchanted.client.render.entity.SimpleAnimatedGeoRenderer;
import favouriteless.enchanted.client.render.model.ModelLayerLocations;
import favouriteless.enchanted.client.render.model.entity.BroomstickModel;
import favouriteless.enchanted.client.screens.*;
import favouriteless.enchanted.common.init.registry.*;
import favouriteless.enchanted.platform.ClientServices;
import favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ClientRegistry {

    public static void register() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        // Armor renderers
        //registry.register(EarmuffsItem.class, () -> new DefaultedGeoArmorRenderer("armor/earmuffs"), EnchantedItems.EARMUFFS.get());

        // MenuScreens
        MenuScreens.register(EnchantedMenuTypes.WITCH_OVEN.get(), WitchOvenScreen::new);
        MenuScreens.register(EnchantedMenuTypes.DISTILLERY.get(), DistilleryScreen::new);
        MenuScreens.register(EnchantedMenuTypes.ALTAR.get(), AltarScreen::new);
        MenuScreens.register(EnchantedMenuTypes.SPINNING_WHEEL.get(), SpinningWheelScreen::new);
        MenuScreens.register(EnchantedMenuTypes.POPPET_SHELF.get(), PoppetShelfScreen::new);
    }

    public static void registerEntityRenderers() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        // Entity renderers
        registry.register(EnchantedEntityTypes.MANDRAKE.get(), context -> new SimpleAnimatedGeoRenderer<>(context, "mandrake"));
        registry.register(EnchantedEntityTypes.BROOMSTICK.get(), BroomstickRenderer::new);
        registry.register(EnchantedEntityTypes.THROWABLE_BREW.get(), ThrownItemRenderer::new);
        registry.register(EnchantedEntityTypes.FAMILIAR_CAT.get(), FamiliarCatRenderer::new);

        // Block entity renderers
        registry.register(EnchantedBlockEntityTypes.WITCH_CAULDRON.get(), context -> new CauldronWaterRenderer<>(10));
        registry.register(EnchantedBlockEntityTypes.KETTLE.get(), context -> new CauldronWaterRenderer<>(8));
        registry.register(EnchantedBlockEntityTypes.SPINNING_WHEEL.get(), SpinningWheelRenderer::new);
        registry.register(EnchantedBlockEntityTypes.POPPET_SHELF.get(), PoppetShelfRenderer::new);
    }

    public static void registerLayerDefinitions() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        // Layer definitions
        registry.register(ModelLayerLocations.BROOMSTICK, BroomstickModel::createLayerDefinition);
        registry.register(ModelLayerLocations.SPINNING_WHEEL, SpinningWheelRenderer::createLayerDefinition);
    }


    public static void registerParticleFactories(ParticleEngine particleEngine) {
        particleEngine.register(EnchantedParticleTypes.BOILING.get(), BoilingParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.CAULDRON_BREW.get(), CauldronBrewParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.CAULDRON_COOK.get(), CauldronCookParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.KETTLE_COOK.get(), KettleCookParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.CIRCLE_MAGIC.get(), CircleMagicParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.POPPET.get(), PoppetParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.IMPRISONMENT_CAGE.get(), ImprisonmentCageParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.IMPRISONMENT_CAGE_SEED.get(), ImprisonmentCageSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.TRANSPOSITION_IRON_SEED.get(), TranspositionIronSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.BROILING_SEED.get(), BroilingSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.SKY_WRATH_SEED.get(), SkyWrathSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.SKY_WRATH.get(), SkyWrathParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.CURSE_SEED.get(), CurseSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.CURSE_BLIGHT_SEED.get(), CurseBlightSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.CURSE_BLIGHT.get(), RepellingParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.REMOVE_CURSE_SEED.get(), RemoveCurseSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.REMOVE_CURSE.get(), RemoveCurseParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.FERTILITY_SEED.get(), FertilitySeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.FERTILITY.get(), RepellingParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.PROTECTION_SEED.get(), ProtectionSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.PROTECTION.get(), ProtectionParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.BIND_FAMILIAR_SEED.get(), BindFamiliarSeedParticle.Factory::new);
        particleEngine.register(EnchantedParticleTypes.BIND_FAMILIAR.get(), BindFamiliarParticle.Factory::new);
    }

    public static void registerItemModelPredicates() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        registry.register(EnchantedItems.CIRCLE_TALISMAN.get(), Enchanted.location("small"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("small") * 0.3F : 0F);
        registry.register(EnchantedItems.CIRCLE_TALISMAN.get(), Enchanted.location("medium"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("medium") * 0.3F : 0F);
        registry.register(EnchantedItems.CIRCLE_TALISMAN.get(), Enchanted.location("large"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("large") * 0.3F : 0F);
    }

}

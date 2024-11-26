package favouriteless.enchanted.client;

import favouriteless.enchanted.client.particles.*;
import favouriteless.enchanted.client.render.blockentity.item.SpinningWheelItemRenderer;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EItems;
import favouriteless.enchanted.common.init.registry.EParticleTypes;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.RenderType;

public class FabricClientRegistry {

    public static void registerAll() {
        registerItemRenderers();
        registerBlockColors();
        registerBlockRenderTypes();
        registerParticleFactories();
    }

    private static void registerItemRenderers() {
        BuiltinItemRendererRegistry.INSTANCE.register(EItems.SPINNING_WHEEL.get(), SpinningWheelItemRenderer.getInstance()::renderByItem);
    }

    private static void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register((a, b, c, d) -> 0xF0F0F0, EnchantedBlocks.RITUAL_CHALK.get());
        ColorProviderRegistry.BLOCK.register((a, b, c, d) -> 0x801818, EnchantedBlocks.NETHER_CHALK.get());
        ColorProviderRegistry.BLOCK.register((a, b, c, d) -> 0x4F2F78, EnchantedBlocks.OTHERWHERE_CHALK.get());
    }

    private static void registerBlockRenderTypes() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.GOLDEN_CHALK.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.RITUAL_CHALK.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.NETHER_CHALK.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.OTHERWHERE_CHALK.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.ROWAN_SAPLING.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.HAWTHORN_SAPLING.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.ALDER_SAPLING.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.BELLADONNA.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.SNOWBELL.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.WATER_ARTICHOKE.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.MANDRAKE.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.GARLIC.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.WOLFSBANE.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.GLINT_WEED.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.EMBER_MOSS.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.SPANISH_MOSS.get());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EnchantedBlocks.BLOOD_POPPY.get());
    }

    private static void registerParticleFactories() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        registry.register(EParticleTypes.BOILING.get(), BoilingParticle.Factory::new);
        registry.register(EParticleTypes.CAULDRON_BREW.get(), CauldronBrewParticle.Factory::new);
        registry.register(EParticleTypes.CAULDRON_COOK.get(), CauldronCookParticle.Factory::new);
        registry.register(EParticleTypes.KETTLE_COOK.get(), KettleCookParticle.Factory::new);
        registry.register(EParticleTypes.CIRCLE_MAGIC.get(), CircleMagicParticle.Factory::new);
        registry.register(EParticleTypes.POPPET.get(), PoppetParticle.Factory::new);
        registry.register(EParticleTypes.IMPRISONMENT_CAGE.get(), ImprisonmentCageParticle.Factory::new);
        registry.register(EParticleTypes.IMPRISONMENT_CAGE_SEED.get(), ImprisonmentCageSeedParticle.Factory::new);
        registry.register(EParticleTypes.TRANSPOSITION_IRON_SEED.get(), TranspositionIronSeedParticle.Factory::new);
        registry.register(EParticleTypes.BROILING_SEED.get(), BroilingSeedParticle.Factory::new);
        registry.register(EParticleTypes.SKY_WRATH_SEED.get(), SkyWrathSeedParticle.Factory::new);
        registry.register(EParticleTypes.SKY_WRATH.get(), SkyWrathParticle.Factory::new);
        registry.register(EParticleTypes.CURSE_SEED.get(), CurseSeedParticle.Factory::new);
        registry.register(EParticleTypes.BLIGHT_SEED.get(), BlightSeedParticle.Factory::new);
        registry.register(EParticleTypes.BLIGHT.get(), RepellingParticle.Factory::new);
        registry.register(EParticleTypes.REMOVE_CURSE_SEED.get(), RemoveCurseSeedParticle.Factory::new);
        registry.register(EParticleTypes.REMOVE_CURSE.get(), RemoveCurseParticle.Factory::new);
        registry.register(EParticleTypes.FERTILITY_SEED.get(), FertilitySeedParticle.Factory::new);
        registry.register(EParticleTypes.FERTILITY.get(), RepellingParticle.Factory::new);
        registry.register(EParticleTypes.PROTECTION_SEED.get(), ProtectionSeedParticle.Factory::new);
        registry.register(EParticleTypes.PROTECTION.get(), ProtectionParticle.Factory::new);
        registry.register(EParticleTypes.BIND_FAMILIAR_SEED.get(), BindFamiliarSeedParticle.Factory::new);
        registry.register(EParticleTypes.BIND_FAMILIAR.get(), BindFamiliarParticle.Factory::new);
    }

}

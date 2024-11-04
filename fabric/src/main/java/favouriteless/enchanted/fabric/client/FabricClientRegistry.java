package favouriteless.enchanted.fabric.client;

import com.ibm.icu.text.MessagePattern.Part;
import favouriteless.enchanted.client.particles.*;
import favouriteless.enchanted.client.render.blockentity.item.SpinningWheelItemRenderer;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
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
        BuiltinItemRendererRegistry.INSTANCE.register(EnchantedItems.SPINNING_WHEEL.get(), SpinningWheelItemRenderer.getInstance()::renderByItem);
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
        registry.register(EnchantedParticleTypes.BOILING.get(), BoilingParticle.Factory::new);
        registry.register(EnchantedParticleTypes.CAULDRON_BREW.get(), CauldronBrewParticle.Factory::new);
        registry.register(EnchantedParticleTypes.CAULDRON_COOK.get(), CauldronCookParticle.Factory::new);
        registry.register(EnchantedParticleTypes.KETTLE_COOK.get(), KettleCookParticle.Factory::new);
        registry.register(EnchantedParticleTypes.CIRCLE_MAGIC.get(), CircleMagicParticle.Factory::new);
        registry.register(EnchantedParticleTypes.POPPET.get(), PoppetParticle.Factory::new);
        registry.register(EnchantedParticleTypes.IMPRISONMENT_CAGE.get(), ImprisonmentCageParticle.Factory::new);
        registry.register(EnchantedParticleTypes.IMPRISONMENT_CAGE_SEED.get(), ImprisonmentCageSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.TRANSPOSITION_IRON_SEED.get(), TranspositionIronSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.BROILING_SEED.get(), BroilingSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.SKY_WRATH_SEED.get(), SkyWrathSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.SKY_WRATH.get(), SkyWrathParticle.Factory::new);
        registry.register(EnchantedParticleTypes.CURSE_SEED.get(), CurseSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.CURSE_BLIGHT_SEED.get(), CurseBlightSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.CURSE_BLIGHT.get(), RepellingParticle.Factory::new);
        registry.register(EnchantedParticleTypes.REMOVE_CURSE_SEED.get(), RemoveCurseSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.REMOVE_CURSE.get(), RemoveCurseParticle.Factory::new);
        registry.register(EnchantedParticleTypes.FERTILITY_SEED.get(), FertilitySeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.FERTILITY.get(), RepellingParticle.Factory::new);
        registry.register(EnchantedParticleTypes.PROTECTION_SEED.get(), ProtectionSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.PROTECTION.get(), ProtectionParticle.Factory::new);
        registry.register(EnchantedParticleTypes.BIND_FAMILIAR_SEED.get(), BindFamiliarSeedParticle.Factory::new);
        registry.register(EnchantedParticleTypes.BIND_FAMILIAR.get(), BindFamiliarParticle.Factory::new);
    }

}

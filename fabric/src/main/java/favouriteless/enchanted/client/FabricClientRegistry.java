package favouriteless.enchanted.client;

import favouriteless.enchanted.client.render.blockentity.item.SpinningWheelItemRenderer;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.RenderType;

public class FabricClientRegistry {

    public static void registerAll() {
        registerItemRenderers();
        registerBlockColors();
        registerBlockRenderTypes();
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

}
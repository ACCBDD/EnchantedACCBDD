package net.favouriteless.enchanted.client;

import net.favouriteless.enchanted.client.screens.*;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.client.render.blockentity.CauldronWaterRenderer;
import net.favouriteless.enchanted.client.render.blockentity.PoppetShelfRenderer;
import net.favouriteless.enchanted.client.render.blockentity.SpinningWheelRenderer;
import net.favouriteless.enchanted.client.render.entity.BroomstickRenderer;
import net.favouriteless.enchanted.client.render.entity.FamiliarCatRenderer;
import net.favouriteless.enchanted.client.render.entity.SimpleAnimatedGeoRenderer;
import net.favouriteless.enchanted.client.render.model.ModelLayerLocations;
import net.favouriteless.enchanted.client.render.model.entity.BroomstickModel;
import net.favouriteless.enchanted.common.init.registry.*;
import net.favouriteless.enchanted.common.items.CircleTalismanItem;
import net.favouriteless.enchanted.platform.ClientServices;
import net.favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ClientRegistry {

    public static void register() {
        // MenuScreens
        MenuScreens.register(EMenuTypes.WITCH_OVEN.get(), WitchOvenScreen::new);
        MenuScreens.register(EMenuTypes.DISTILLERY.get(), DistilleryScreen::new);
        MenuScreens.register(EMenuTypes.ALTAR.get(), AltarScreen::new);
        MenuScreens.register(EMenuTypes.SPINNING_WHEEL.get(), SpinningWheelScreen::new);
        MenuScreens.register(EMenuTypes.POPPET_SHELF.get(), PoppetShelfScreen::new);
    }

    public static void registerEntityRenderers() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        // Entity renderers
        registry.register(EEntityTypes.MANDRAKE.get(), context -> new SimpleAnimatedGeoRenderer<>(context, "mandrake"));
        registry.register(EEntityTypes.BROOMSTICK.get(), BroomstickRenderer::new);
        registry.register(EEntityTypes.THROWABLE_BREW.get(), ThrownItemRenderer::new);
        registry.register(EEntityTypes.FAMILIAR_CAT.get(), FamiliarCatRenderer::new);
        registry.register(EEntityTypes.VOODOO_ITEM.get(), ItemEntityRenderer::new);

        // Block entity renderers
        registry.register(EnchantedBlockEntityTypes.WITCH_CAULDRON.get(), context -> new CauldronWaterRenderer<>(12));
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

    public static void registerItemModelPredicates() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        registry.register(EItems.CIRCLE_TALISMAN.get(), Enchanted.id("small"), (stack, level, entity, seed) -> talismanTexturePred(stack, Enchanted.id("small_circle")));
        registry.register(EItems.CIRCLE_TALISMAN.get(), Enchanted.id("medium"), (stack, level, entity, seed) -> talismanTexturePred(stack, Enchanted.id("medium_circle")));
        registry.register(EItems.CIRCLE_TALISMAN.get(), Enchanted.id("large"), (stack, level, entity, seed) -> talismanTexturePred(stack, Enchanted.id("large_circle")));
    }

    protected static float talismanTexturePred(ItemStack stack, ResourceLocation id) {
        if(!stack.hasTag())
            return 0.0F;

        Block block = BuiltInRegistries.BLOCK.get(new ResourceLocation(stack.getTag().getCompound(CircleTalismanItem.SHAPE_TAG).getString(id.toString())));

        if(block == EBlocks.RITUAL_CHALK.get())
            return 0.3F;
        if(block == EBlocks.NETHER_CHALK.get())
            return 0.6F;
        if(block == EBlocks.OTHERWHERE_CHALK.get())
            return 0.9F;

        return 0.0F;
    }

}

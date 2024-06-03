package favouriteless.enchanted.client;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.client.render.blockentity.CauldronWaterRenderer;
import favouriteless.enchanted.client.render.blockentity.PoppetShelfRenderer;
import favouriteless.enchanted.client.render.blockentity.SpinningWheelRenderer;
import favouriteless.enchanted.client.render.entity.BroomstickRenderer;
import favouriteless.enchanted.client.render.entity.FamiliarCatRenderer;
import favouriteless.enchanted.client.render.entity.SimpleAnimatedGeoRenderer;
import favouriteless.enchanted.client.render.model.ModelLayerLocations;
import favouriteless.enchanted.client.render.model.entity.BroomstickModel;
import favouriteless.enchanted.client.screens.*;
import favouriteless.enchanted.common.init.registry.EnchantedBlockEntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.common.init.registry.EnchantedMenuTypes;
import favouriteless.enchanted.platform.ClientServices;
import favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ClientRegistry {

    public static void register() {
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
        registry.register(EnchantedEntityTypes.VOODOO_ITEM.get(), ItemEntityRenderer::new);

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

        registry.register(EnchantedItems.CIRCLE_TALISMAN.get(), Enchanted.id("small"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("small") * 0.3F : 0F);
        registry.register(EnchantedItems.CIRCLE_TALISMAN.get(), Enchanted.id("medium"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("medium") * 0.3F : 0F);
        registry.register(EnchantedItems.CIRCLE_TALISMAN.get(), Enchanted.id("large"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("large") * 0.3F : 0F);
    }

}

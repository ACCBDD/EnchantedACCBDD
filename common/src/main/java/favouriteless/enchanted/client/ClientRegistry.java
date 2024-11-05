package favouriteless.enchanted.client;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.client.render.blockentity.CauldronWaterRenderer;
import favouriteless.enchanted.client.render.blockentity.PoppetShelfRenderer;
import favouriteless.enchanted.client.render.blockentity.SpinningWheelRenderer;
import favouriteless.enchanted.client.render.entity.BroomstickRenderer;
import favouriteless.enchanted.client.render.entity.FamiliarCatRenderer;
import favouriteless.enchanted.client.render.entity.SimpleAnimatedGeoRenderer;
import favouriteless.enchanted.client.render.model.ModelLayerLocations;
import favouriteless.enchanted.client.render.model.entity.BroomstickModel;
import favouriteless.enchanted.client.screens.*;
import favouriteless.enchanted.common.blocks.entity.EBlockEntityTypes;
import favouriteless.enchanted.common.entities.EEntityTypes;
import favouriteless.enchanted.common.items.EItems;
import favouriteless.enchanted.common.menus.EMenuTypes;
import favouriteless.enchanted.platform.ClientServices;
import favouriteless.enchanted.platform.services.IClientRegistryHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

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
        registry.register(EBlockEntityTypes.WITCH_CAULDRON.get(), context -> new CauldronWaterRenderer<>(12));
        registry.register(EBlockEntityTypes.KETTLE.get(), context -> new CauldronWaterRenderer<>(8));
        registry.register(EBlockEntityTypes.SPINNING_WHEEL.get(), SpinningWheelRenderer::new);
        registry.register(EBlockEntityTypes.POPPET_SHELF.get(), PoppetShelfRenderer::new);
    }

    public static void registerLayerDefinitions() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        // Layer definitions
        registry.register(ModelLayerLocations.BROOMSTICK, BroomstickModel::createLayerDefinition);
        registry.register(ModelLayerLocations.SPINNING_WHEEL, SpinningWheelRenderer::createLayerDefinition);
    }

    public static void registerItemModelPredicates() {
        IClientRegistryHelper registry = ClientServices.CLIENT_REGISTRY;

        registry.register(EItems.CIRCLE_TALISMAN.get(), Enchanted.id("small"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("small") * 0.3F : 0F);
        registry.register(EItems.CIRCLE_TALISMAN.get(), Enchanted.id("medium"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("medium") * 0.3F : 0F);
        registry.register(EItems.CIRCLE_TALISMAN.get(), Enchanted.id("large"), (stack, world, living, seed) -> stack.hasTag() ? stack.getTag().getByte("large") * 0.3F : 0F);
    }

}

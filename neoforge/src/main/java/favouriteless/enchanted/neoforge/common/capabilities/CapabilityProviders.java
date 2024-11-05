package favouriteless.enchanted.neoforge.common.capabilities;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.blocks.entity.EBlockEntityTypes;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@EventBusSubscriber(modid = Enchanted.MOD_ID, bus = Bus.GAME)
public class CapabilityProviders {

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EBlockEntityTypes.WITCH_OVEN.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EBlockEntityTypes.DISTILLERY.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EBlockEntityTypes.SPINNING_WHEEL.get(), SidedInvWrapper::new);
    }

}

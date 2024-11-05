package favouriteless.enchanted.neoforge.common.capabilities;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.registry.EnchantedBlockEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.IBlockCapabilityProvider;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = Enchanted.MOD_ID, bus = Bus.GAME)
public class CapabilityProviders {

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EnchantedBlockEntityTypes.WITCH_OVEN.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EnchantedBlockEntityTypes.DISTILLERY.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EnchantedBlockEntityTypes.SPINNING_WHEEL.get(), SidedInvWrapper::new);
    }

}

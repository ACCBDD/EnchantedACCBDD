package favouriteless.enchanted.neoforge.common.capabilities;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.init.registry.EnchantedBlockEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.FORGE)
public class CapabilityProviders {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {

        BlockEntity be = event.getObject();
        BlockEntityType<?> type = be.getType();
        if(type == EnchantedBlockEntityTypes.WITCH_OVEN.get())
            event.addCapability(Enchanted.id("inventory"), new ICapabilityProvider() {

                final LazyOptional<IItemHandlerModifiable>[] inv = SidedInvWrapper.create(
                        (WorldlyContainer)be, Direction.values());

                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if(side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
                        return inv[side.ordinal()].cast();
                    }
                    return LazyOptional.empty();
                }
            });
        else if(type == EnchantedBlockEntityTypes.DISTILLERY.get())
            event.addCapability(Enchanted.id("inventory"), new ICapabilityProvider() {

                final LazyOptional<IItemHandlerModifiable>[] inv = SidedInvWrapper.create((WorldlyContainer)be,
                        Direction.UP, Direction.DOWN, Direction.NORTH);

                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if(side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
                        if (side == Direction.UP)
                            return inv[0].cast();
                        else if (side == Direction.DOWN)
                            return inv[1].cast();
                        else
                            return inv[2].cast();
                    }
                    return LazyOptional.empty();
                }
            });
        else if(type == EnchantedBlockEntityTypes.SPINNING_WHEEL.get())
            event.addCapability(Enchanted.id("inventory"), new ICapabilityProvider() {

                final LazyOptional<IItemHandlerModifiable>[] inv = SidedInvWrapper.create((WorldlyContainer)be,
                        Direction.DOWN, Direction.DOWN);

                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if(side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
                        if (side == Direction.DOWN)
                            return inv[0].cast();
                        return inv[1].cast();
                    }
                    return LazyOptional.empty();
                }
            });

    }

}

package net.favouriteless.enchanted.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public interface IPlatformHelper {

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    /**
     * Open a {@link AbstractContainerMenu} for a {@link BlockEntity}, the {@link BlockPos} of the entity will be written to the buffer
     * sent to clients.
     *
     * @param player The {@link ServerPlayer} to open the screen for.
     * @param provider The {@link MenuProvider} the screen is being opened for.
     * @param data The data to be passed to the menu.
     * @param codec The codec used to encode the data.
     */
    <T> void openMenu(ServerPlayer player, MenuProvider provider,
                      T data, StreamCodec<? super RegistryFriendlyByteBuf, T> codec);

    /**
     * Open a {@link AbstractContainerMenu} for a {@link BlockEntity}, the {@link BlockPos} of the entity will be written to the buffer
     * sent to clients.
     *
     * @param player The {@link ServerPlayer} to open the screen for.
     * @param provider The {@link MenuProvider} the screen is being opened for.
     */
    void openMenu(ServerPlayer player, MenuProvider provider);

    /**
     * Grab the burn time for a given {@link ItemStack} and {@link RecipeType}.
     * @param stack The {@link ItemStack} to be checked.
     * @param recipeType The {@link RecipeType} of the recipe this is being used for.
     * @return burn time (in ticks)
     */
    int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType);

    /**
     * Check if an {@link ItemStack} has a crafting remainder (e.g. buckets)
     * @param item The {@link ItemStack} to check.
     * @return True if a remainder item is found, otherwise false.
     */
    boolean hasCraftingRemainingItem(ItemStack item);

    /**
     * Grab the craft remainder {@link ItemStack} (e.g. buckets) from the given {@link ItemStack}
     * @param item The {@link ItemStack} to get a remainder for.
     * @return An {@link ItemStack} instance generated by item.
     */
    ItemStack getCraftingRemainingItem(ItemStack item);

}
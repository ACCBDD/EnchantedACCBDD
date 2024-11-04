package favouriteless.enchanted.platform.services;

import favouriteless.enchanted.common.network.PacketContext;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.BiConsumer;

public interface INetworkHelper {

    <T extends CustomPacketPayload> void registerClient(CustomPacketPayload.Type<T> type,
                                                        StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
                                                        BiConsumer<T, PacketContext> handler);

    <T extends CustomPacketPayload> void registerServer(CustomPacketPayload.Type<T> type,
                                                        StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
                                                        BiConsumer<T, PacketContext> handler);

    <T extends CustomPacketPayload> void registerBidirectional(CustomPacketPayload.Type<T> type,
                                                               StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
                                                               BiConsumer<T, PacketContext> handler);

    /**
     * Attempt to send a packet to a specific player FROM the server.
     *
     * @param payload The {@link CustomPacketPayload} to be sent.
     * @param player The {@link ServerPlayer} receiving the packet.
     */
    void sendToPlayer(CustomPacketPayload payload, ServerPlayer player);

    /**
     * Attempt to send a packet to all players FROM the server.
     *
     * @param payload The {@link CustomPacketPayload} to be sent.
     * @param server Access to {@link MinecraftServer} for Fabric to grab players from.
     */
    void sendToAllPlayers(CustomPacketPayload payload, MinecraftServer server);

    /**
     * Attempt to send a packet to the server FROM a client.
     *
     * @param payload The {@link CustomPacketPayload} to be sent.
     */
    void sendToServer(CustomPacketPayload payload);

}

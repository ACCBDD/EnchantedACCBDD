package favouriteless.enchanted.platform.services;

import favouriteless.enchanted.common.network.PacketContext;
import favouriteless.enchanted.fabric.common.network.FabricClientPacketContext;
import favouriteless.enchanted.fabric.common.network.FabricServerPacketContext;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.BiConsumer;

public class FabricNetworkHelper implements INetworkHelper {

    @Override
    public <T extends CustomPacketPayload> void registerClient(Type<T> type,
                                                               StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
                                                               BiConsumer<T, PacketContext> handler) {
        PayloadTypeRegistry.playS2C().register(type, codec);
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> handler.accept(payload, new FabricClientPacketContext(context)));
    }

    @Override
    public <T extends CustomPacketPayload> void registerServer(Type<T> type,
                                                               StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
                                                               BiConsumer<T, PacketContext> handler) {
        PayloadTypeRegistry.playC2S().register(type, codec);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> handler.accept(payload, new FabricServerPacketContext(context)));
    }

    @Override
    public <T extends CustomPacketPayload> void registerBidirectional(Type<T> type,
                                                                      StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
                                                                      BiConsumer<T, PacketContext> handler) {
        registerClient(type, codec, handler);
        registerServer(type, codec, handler);
    }

    @Override
    public void sendToPlayer(CustomPacketPayload payload, ServerPlayer player) {
        ServerPlayNetworking.send(player, payload);
    }

    @Override
    public void sendToAllPlayers(CustomPacketPayload payload, MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(player -> ServerPlayNetworking.send(player, payload));
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }

}

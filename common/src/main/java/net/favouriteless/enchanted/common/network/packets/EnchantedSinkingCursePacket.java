package net.favouriteless.enchanted.common.network.packets;

import net.favouriteless.enchanted.client.client_handlers.misc.EnchantedClientValues;
import net.favouriteless.enchanted.common.network.EnchantedPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class EnchantedSinkingCursePacket implements EnchantedPacket {

	private final double sinkingFactor;

	public EnchantedSinkingCursePacket(double sinkingFactor) {
		this.sinkingFactor = sinkingFactor;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeDouble(sinkingFactor);
	}


	public static EnchantedSinkingCursePacket decode(FriendlyByteBuf buffer) {
		return new EnchantedSinkingCursePacket(buffer.readDouble());
	}

	@Override
	public void handle(ServerPlayer sender) {
		EnchantedClientValues.CURSE_SINKING_SPEED = sinkingFactor;
	}

}

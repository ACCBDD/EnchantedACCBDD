package favouriteless.enchanted.common.network.packets.client;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.client.client_handlers.misc.EnchantedClientValues;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public class SinkingCursePacket implements CustomPacketPayload {

	public static final Type<SinkingCursePacket> TYPE = new Type<>(Enchanted.id("sinking_curse"));

	public static final StreamCodec<ByteBuf, SinkingCursePacket> STREAM_CODEC = StreamCodec.of(
			(buf, p) -> buf.writeFloat(p.sinkingFactor), (buf) -> new SinkingCursePacket(buf.readFloat()));

	private final float sinkingFactor;

	public SinkingCursePacket(float sinkingFactor) {
		this.sinkingFactor = sinkingFactor;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void handle(ServerPlayer sender) {
		EnchantedClientValues.CURSE_SINKING_SPEED = sinkingFactor;
	}


}

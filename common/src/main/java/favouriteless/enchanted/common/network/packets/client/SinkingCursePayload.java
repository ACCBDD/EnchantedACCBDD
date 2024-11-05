package favouriteless.enchanted.common.network.packets.client;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.client.client_handlers.misc.EnchantedClientValues;
import favouriteless.enchanted.common.network.PacketContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class SinkingCursePayload implements CustomPacketPayload {

	public static final Type<SinkingCursePayload> TYPE = new Type<>(Enchanted.id("sinking_curse"));

	public static final StreamCodec<ByteBuf, SinkingCursePayload> STREAM_CODEC = StreamCodec.of(
			(buf, p) -> buf.writeFloat(p.sinkingFactor), (buf) -> new SinkingCursePayload(buf.readFloat()));

	private final float sinkingFactor;

	public SinkingCursePayload(float sinkingFactor) {
		this.sinkingFactor = sinkingFactor;
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(SinkingCursePayload payload, PacketContext context) {
		EnchantedClientValues.CURSE_SINKING_SPEED = payload.sinkingFactor;
	}


}

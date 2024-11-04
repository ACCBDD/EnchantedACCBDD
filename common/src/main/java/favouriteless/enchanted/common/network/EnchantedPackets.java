package favouriteless.enchanted.common.network;

import favouriteless.enchanted.common.network.packets.client.PoppetAnimationPacket;
import favouriteless.enchanted.common.network.packets.client.SinkingCursePacket;
import favouriteless.enchanted.platform.CommonServices;

public class EnchantedPackets {

    public static void register() {
        CommonServices.NETWORK.register("poppet_animation", PoppetAnimationPacket.class, PoppetAnimationPacket::decode);
        CommonServices.NETWORK.register("sinking", SinkingCursePacket.class, SinkingCursePacket::decode);
    }

}

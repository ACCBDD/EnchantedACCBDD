package net.favouriteless.enchanted.common.network;

import net.favouriteless.enchanted.common.network.packets.EnchantedPoppetAnimationPacket;
import net.favouriteless.enchanted.common.network.packets.EnchantedSinkingCursePacket;
import net.favouriteless.enchanted.platform.CommonServices;

public class EnchantedPackets {

    public static void register() {
        CommonServices.NETWORK.register("poppet_animation", EnchantedPoppetAnimationPacket.class, EnchantedPoppetAnimationPacket::decode);
        CommonServices.NETWORK.register("sinking", EnchantedSinkingCursePacket.class, EnchantedSinkingCursePacket::decode);
    }

}

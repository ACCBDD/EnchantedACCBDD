package com.favouriteless.enchanted.common.network;

import com.favouriteless.enchanted.common.network.packets.EnchantedPoppetAnimationPacket;
import com.favouriteless.enchanted.common.network.packets.EnchantedSinkingCursePacket;
import com.favouriteless.enchanted.platform.CommonServices;

public class EnchantedPackets {

    public static void register() {
        CommonServices.NETWORK.register("poppet_animation", EnchantedPoppetAnimationPacket.class, EnchantedPoppetAnimationPacket::decode);
        CommonServices.NETWORK.register("sinking", EnchantedSinkingCursePacket.class, EnchantedSinkingCursePacket::decode);
    }

}

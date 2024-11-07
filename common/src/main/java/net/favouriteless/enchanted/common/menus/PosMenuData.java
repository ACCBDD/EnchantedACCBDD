package net.favouriteless.enchanted.common.menus;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;

public record PosMenuData(BlockPos pos) {

    public static final StreamCodec<ByteBuf, PosMenuData> STREAM_CODEC = BlockPos.STREAM_CODEC.map(PosMenuData::new, d -> d.pos);

}

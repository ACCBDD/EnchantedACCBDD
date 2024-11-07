package net.favouriteless.enchanted.common.items.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record OptionalBlockPosData(Optional<BlockPos> pos) {

    public static final OptionalBlockPosData EMPTY = new OptionalBlockPosData(Optional.empty());

    public static final Codec<OptionalBlockPosData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockPos.CODEC.optionalFieldOf("key").forGetter(data -> data.pos)
    ).apply(instance, optional -> OptionalBlockPosData.of(optional.orElse(null))));

    public static final StreamCodec<ByteBuf, OptionalBlockPosData> STREAM_CODEC = ByteBufCodecs.optional(BlockPos.STREAM_CODEC)
            .map(pos -> OptionalBlockPosData.of(pos.orElse(null)), OptionalBlockPosData::pos);

    public static OptionalBlockPosData of(@Nullable BlockPos pos) {
        if(pos == null)
            return OptionalBlockPosData.EMPTY;

        return new OptionalBlockPosData(Optional.of(pos));
    }

}

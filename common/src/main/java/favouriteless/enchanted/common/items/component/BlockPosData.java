package favouriteless.enchanted.common.items.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public record BlockPosData(Optional<BlockPos> pos) {

    public static final BlockPosData EMPTY = new BlockPosData(Optional.empty());

    public static final Codec<BlockPosData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockPos.CODEC.optionalFieldOf("pos").forGetter(data -> data.pos)
    ).apply(instance, optional -> BlockPosData.of(optional.orElse(null))));

    public static final StreamCodec<ByteBuf, BlockPosData> STREAM_CODEC = ByteBufCodecs.optional(BlockPos.STREAM_CODEC)
            .map(pos -> BlockPosData.of(pos.orElse(null)), BlockPosData::pos);

    public static BlockPosData of(@Nullable BlockPos pos) {
        if(pos == null)
            return BlockPosData.EMPTY;

        return new BlockPosData(Optional.of(pos));
    }

}

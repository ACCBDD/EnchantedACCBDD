package favouriteless.enchanted.common.items.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;
import java.util.UUID;

public record TaglockData(Optional<UUID> uuid, Optional<String> name) {

    public static final TaglockData EMPTY = new TaglockData(null, null);

    public static final Codec<TaglockData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UUIDUtil.CODEC.optionalFieldOf("uuid").forGetter(data -> data.uuid),
            Codec.STRING.optionalFieldOf("name").forGetter(data -> data.name)
    ).apply(instance, (uuid, name) -> TaglockData.of(uuid.orElse(null), name.orElse(null))));

    public static final StreamCodec<ByteBuf, TaglockData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(UUIDUtil.STREAM_CODEC), data -> data.uuid,
            ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8), data -> data.name,
            TaglockData::new
    );

    public static TaglockData of(UUID uuid, String name) {
        if(uuid == null || name == null)
            return TaglockData.EMPTY;

        return new TaglockData(Optional.of(uuid), Optional.of(name));
    }

}

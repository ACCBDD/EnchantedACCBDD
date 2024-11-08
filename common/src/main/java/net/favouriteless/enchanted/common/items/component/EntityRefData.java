package net.favouriteless.enchanted.common.items.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public record EntityRefData(Optional<UUID> uuid, Optional<String> name) {

    public static final EntityRefData EMPTY = new EntityRefData(Optional.empty(), Optional.empty());

    public static final Codec<EntityRefData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UUIDUtil.CODEC.optionalFieldOf("uuid").forGetter(data -> data.uuid),
            Codec.STRING.optionalFieldOf("name").forGetter(data -> data.name)
    ).apply(instance, (uuid, name) -> EntityRefData.of(uuid.orElse(null), name.orElse(null))));

    public static final StreamCodec<ByteBuf, EntityRefData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(UUIDUtil.STREAM_CODEC), data -> data.uuid,
            ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8), data -> data.name,
            EntityRefData::new
    );

    public static EntityRefData of(@Nullable UUID uuid, @Nullable String name) {
        if(uuid == null || name == null)
            return EntityRefData.EMPTY;

        return new EntityRefData(Optional.of(uuid), Optional.of(name));
    }

}
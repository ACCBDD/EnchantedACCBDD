package net.favouriteless.enchanted.common.items.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record OptionalLevelKeyData(Optional<ResourceKey<Level>> key) {

    public static final OptionalLevelKeyData EMPTY = new OptionalLevelKeyData(Optional.empty());

    public static final Codec<OptionalLevelKeyData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceKey.codec(Registries.DIMENSION).optionalFieldOf("key").forGetter(data -> data.key)
    ).apply(instance, optional -> OptionalLevelKeyData.of(optional.orElse(null))));

    public static final StreamCodec<ByteBuf, OptionalLevelKeyData> STREAM_CODEC = ByteBufCodecs.optional(
            ResourceKey.streamCodec(Registries.DIMENSION)).map(pos ->
            OptionalLevelKeyData.of(pos.orElse(null)), OptionalLevelKeyData::key);

    public static OptionalLevelKeyData of(@Nullable ResourceKey<Level> key) {
        if(key == null)
            return OptionalLevelKeyData.EMPTY;

        return new OptionalLevelKeyData(Optional.of(key));
    }

}

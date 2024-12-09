package net.favouriteless.enchanted.common.altar;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.common.init.EData;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public record PowerProvider<T>(T key, int power, int limit) {

    public static final Codec<PowerProvider<Block>> BLOCK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("block").forGetter(data -> BuiltInRegistries.BLOCK.getKey(data.key).toString()),
            Codec.INT.fieldOf("power").forGetter(data -> data.power),
            Codec.INT.fieldOf("limit").forGetter(data -> data.limit)
    ).apply(instance, (block, power, limit) -> new PowerProvider<>(BuiltInRegistries.BLOCK.get(new ResourceLocation(block)), power, limit)));

    public static final Codec<PowerProvider<TagKey<Block>>> TAG_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("tag").forGetter(data -> data.key.location().toString()),
            Codec.INT.fieldOf("power").forGetter(data -> data.power),
            Codec.INT.fieldOf("limit").forGetter(data -> data.limit)
    ).apply(instance, (tag, power, limit) -> new PowerProvider<>(TagKey.create(Registries.BLOCK, new ResourceLocation(tag)), power, limit)));

    public boolean is(T key) {
        return this.key == key;
    }

    public static PowerProvider<Block> getBlock(Level level, Block block) {
        Optional<Registry<PowerProvider<Block>>> optional = level.registryAccess().registry(EData.ALTAR_BLOCK_REGISTRY);
        if(optional.isPresent()) {
            for(PowerProvider<Block> provider : optional.get()) {
                if(provider.key == block)
                    return provider;
            }
        }
        return null;
    }

    public static PowerProvider<TagKey<Block>> getTag(Level level, TagKey<Block> tag) {
        Optional<Registry<PowerProvider<TagKey<Block>>>> optional = level.registryAccess().registry(EData.ALTAR_TAG_REGISTRY);
        if(optional.isPresent()) {
            for(PowerProvider<TagKey<Block>> provider : optional.get()) {
                if(provider.key == tag)
                    return provider;
            }
        }
        return null;
    }

}
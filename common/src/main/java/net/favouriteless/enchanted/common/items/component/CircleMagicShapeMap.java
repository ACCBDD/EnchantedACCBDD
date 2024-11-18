package net.favouriteless.enchanted.common.items.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class CircleMagicShapeMap {

    public static Codec<Map<ResourceLocation, Block>> CODEC = Codec.unboundedMap(ResourceLocation.CODEC, BuiltInRegistries.BLOCK.byNameCodec());

    public static StreamCodec<RegistryFriendlyByteBuf, Map<ResourceLocation, Block>> STREAM_CODEC = ByteBufCodecs.map(HashMap::new, ResourceLocation.STREAM_CODEC, ByteBufCodecs.registry(Registries.BLOCK));

}

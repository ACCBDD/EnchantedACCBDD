package net.favouriteless.enchanted.common.init;

import com.mojang.serialization.Codec;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.altar.AltarUpgrade;
import net.favouriteless.enchanted.common.altar.PowerProvider;
import net.favouriteless.enchanted.common.circle_magic.CircleMagicShape;
import net.favouriteless.enchanted.common.circle_magic.RiteType;
import net.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class EData {

    public static final ResourceKey<Registry<AltarUpgrade>> ALTAR_UPGRADE_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("altar/upgrade")), AltarUpgrade.CODEC);
    public static final ResourceKey<Registry<PowerProvider<Block>>> ALTAR_BLOCK_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("altar/block")), PowerProvider.BLOCK_CODEC);
    public static final ResourceKey<Registry<PowerProvider<TagKey<Block>>>> ALTAR_TAG_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("altar/tag")), PowerProvider.TAG_CODEC);
    public static final ResourceKey<Registry<CircleMagicShape>> CIRCLE_SHAPE_REGISTRY = registerSynced(ResourceKey.createRegistryKey(Enchanted.id("circle_magic/shape")), CircleMagicShape.CODEC, CircleMagicShape.CODEC);
    public static final ResourceKey<Registry<RiteType>> RITE_TYPES_REGISTRY = registerSynced(ResourceKey.createRegistryKey(Enchanted.id("circle_magic/rite")), RiteType.CODEC, RiteType.CODEC);



    private static <T> ResourceKey<Registry<T>> register(ResourceKey<Registry<T>> key, Codec<T> codec) {
        return CommonServices.COMMON_REGISTRY.registerDataRegistry(key, codec);
    }

    private static <T> ResourceKey<Registry<T>> registerSynced(ResourceKey<Registry<T>> key, Codec<T> codec, Codec<T> networkCodec) {
        return CommonServices.COMMON_REGISTRY.registerSyncedDataRegistry(key, codec, networkCodec);
    }

    private static Block createBlockKey(ResourceLocation key) {
        Block block = BuiltInRegistries.BLOCK.get(key);
        if(block != Blocks.AIR)
            return block;
        else
            return null;
    }

    private static TagKey<Block> createBlockTagKey(ResourceLocation key) {
        return TagKey.create(Registries.BLOCK, key);
    }

    public static void load() {}

}

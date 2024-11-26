package favouriteless.enchanted.common.init;

import com.mojang.serialization.Codec;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.altar.PowerProvider;
import favouriteless.enchanted.common.altar.AltarUpgrade;
import favouriteless.enchanted.common.circle_magic.CircleMagicShape;
import favouriteless.enchanted.common.circle_magic.RiteType;
import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class EnchantedData {

    // These datapack registries need to be registered separately because Forge defers it. See EnchantedForge and EnchantedFabric.
    public static final ResourceKey<Registry<AltarUpgrade>> ALTAR_UPGRADE_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("altar/upgrades")), AltarUpgrade.CODEC);
    public static final ResourceKey<Registry<PowerProvider<Block>>> ALTAR_BLOCK_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("altar/blocks")), PowerProvider.BLOCK_CODEC);
    public static final ResourceKey<Registry<PowerProvider<TagKey<Block>>>> ALTAR_TAG_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("altar/tags")), PowerProvider.TAG_CODEC);
    public static final ResourceKey<Registry<CircleMagicShape>> CIRCLE_SHAPE_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("circle_magic/shape")), CircleMagicShape.CODEC);
    public static final ResourceKey<Registry<RiteType>> RITE_TYPES_REGISTRY = register(ResourceKey.createRegistryKey(Enchanted.id("circle_magic/rite")), RiteType.CODEC);

    private static <T> ResourceKey<Registry<T>> register(ResourceKey<Registry<T>> key, Codec<T> codec) {
        return CommonServices.COMMON_REGISTRY.registerDataRegistry(key, codec);
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

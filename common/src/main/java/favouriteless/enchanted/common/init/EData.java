package favouriteless.enchanted.common.init;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.altar.PowerProvider;
import favouriteless.enchanted.common.altar.AltarUpgrade;
import favouriteless.enchanted.common.rites.RiteRequirements;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class EData {

    // These datapack registries need to be registered separately because Forge defers it. See EnchantedForge and EnchantedFabric.
    public static final ResourceKey<Registry<AltarUpgrade>> ALTAR_UPGRADE_REGISTRY = ResourceKey.createRegistryKey(Enchanted.id("altar/upgrades"));
    public static final ResourceKey<Registry<PowerProvider<Block>>> ALTAR_BLOCK_REGISTRY = ResourceKey.createRegistryKey(Enchanted.id("altar/blocks"));
    public static final ResourceKey<Registry<PowerProvider<TagKey<Block>>>> ALTAR_TAG_REGISTRY = ResourceKey.createRegistryKey(Enchanted.id("altar/tags"));
    public static final ResourceKey<Registry<RiteRequirements>> RITE_REQUIREMENTS_REGISTRY = ResourceKey.createRegistryKey(Enchanted.id("rite_requirements"));

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

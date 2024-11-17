package net.favouriteless.enchanted.common.init;

import net.favouriteless.enchanted.common.Enchanted;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ETags {

    public static class Blocks {
        public static final TagKey<Block> BLIGHT_DECAYABLE_BLOCKS = createBlockTag("blight_decayable_blocks");
        public static final TagKey<Block> BLIGHT_DECAYABLE_PLANTS = createBlockTag("blight_decayable_plants");
        public static final TagKey<Block> BLIGHT_DECAY_BLOCKS = createBlockTag("blight_decay_blocks");
        public static final TagKey<Block> CHALICES = createBlockTag("chalices");
        public static final TagKey<Block> CHALKS = createBlockTag("chalks");
        public static final TagKey<Block> CROPS = createBlockTag("crops");
        public static final TagKey<Block> FENCES = createBlockTag("fences");
        public static final TagKey<Block> FENCE_GATES = createBlockTag("fence_gates");
        public static final TagKey<Block> LEAVES = createBlockTag("leaves");
        public static final TagKey<Block> LOGS = createBlockTag("logs");
        public static final TagKey<Block> MUTANDIS_BLACKLIST = createBlockTag("mutandis_blacklist_plants");
        public static final TagKey<Block> MUTANDIS_EXTREMIS_BLACKLIST = createBlockTag("mutandis_extremis_blaclist");
        public static final TagKey<Block> MUTANDIS_EXTREMIS_PLANTS = createBlockTag("mutandis_extremis_plants");
        public static final TagKey<Block> MUTANDIS_PLANTS = createBlockTag("mutandis_plants");
        public static final TagKey<Block> PLANKS = createBlockTag("planks");
        public static final TagKey<Block> RITE_FOREST_REPLACEABLE = createBlockTag("rite_forest_replaceable");
        public static final TagKey<Block> SAPLINGS = createBlockTag("saplings");
        public static final TagKey<Block> SLABS = createBlockTag("slabs");
        public static final TagKey<Block> STAIRS = createBlockTag("stairs");
        public static final TagKey<Block> TRANSPOSE_IRON = createBlockTag("transpose_iron");
        public static final TagKey<Block> WOODEN_FENCES = createBlockTag("wooden_fences");
        public static final TagKey<Block> WOODEN_SLABS = createBlockTag("wooden_slabs");
        public static final TagKey<Block> WOODEN_STAIRS = createBlockTag("wooden_stairs");
    }

    public static class Items {
        public static final TagKey<Item> ARMORS = createItemTag("armors");
        public static final TagKey<Item> ARMOR_POPPET_BLACKLIST = createItemTag("armor_poppet_blacklist");
        public static final TagKey<Item> CHALICES = createItemTag("chalices");
        public static final TagKey<Item> CHALKS = createItemTag("chalks");
        public static final TagKey<Item> LEAVES = createItemTag("leaves");
        public static final TagKey<Item> LOGS = createItemTag("logs");
        public static final TagKey<Item> PLANKS = createItemTag("planks");
        public static final TagKey<Item> RAW_FOODS = createItemTag("raw_foods");
        public static final TagKey<Item> SAPLINGS = createItemTag("saplings");
        public static final TagKey<Item> SLABS = createItemTag("slabs");
        public static final TagKey<Item> STAIRS = createItemTag("stairs");
        public static final TagKey<Item> SWORDS = createItemTag("swords");
        public static final TagKey<Item> TOOLS = createItemTag("tools");
        public static final TagKey<Item> TOOL_POPPET_BLACKLIST = createItemTag("tool_poppet_blacklist");
        public static final TagKey<Item> TOOL_POPPET_WHITELIST = createItemTag("tool_poppet_whitelist");
        public static final TagKey<Item> WITCH_OVEN_BLACKLIST = createItemTag("witch_oven_blacklist");
        public static final TagKey<Item> WOODEN_SLABS = createItemTag("wooden_slabs");
        public static final TagKey<Item> WOODEN_STAIRS = createItemTag("wooden_stairs");
    }

    public static class MobEffects {
        public static final TagKey<MobEffect> MISFORTUNE_EFFECTS = createEffectTag("misfortune_effects");
        public static final TagKey<MobEffect> BLIGHT_EFFECTS = createEffectTag("blight_effects");
        public static final TagKey<MobEffect> FERTILITY_CURE_EFFECTS = createEffectTag("fertility_cure_effects");
    }

    public static class Biomes {
        public static final TagKey<Biome> OVERHEATING_BIOMES = createBiomeTag("overheating_biomes");
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> MONSTERS = createEntityTag("monsters");
        public static final TagKey<EntityType<?>> TAGLOCK_BLACKLIST = createEntityTag("taglock_blacklist");
    }



    private static <T> TagKey<T> createTag(ResourceKey<? extends Registry<T>> registry, String name) {
        return TagKey.create(registry, Enchanted.id(name));
    }

    public static TagKey<Item> createItemTag(String name) {
        return createTag(Registries.ITEM, name);
    }

    public static TagKey<Block> createBlockTag(String name) {
        return createTag(Registries.BLOCK, name);
    }

    public static TagKey<EntityType<?>> createEntityTag(String name) {
        return createTag(Registries.ENTITY_TYPE, name);
    }

    public static TagKey<Biome> createBiomeTag(String name) {
        return createTag(Registries.BIOME, name);
    }

    public static TagKey<MobEffect> createEffectTag(String name) {
        return createTag(Registries.MOB_EFFECT, name);
    }

}

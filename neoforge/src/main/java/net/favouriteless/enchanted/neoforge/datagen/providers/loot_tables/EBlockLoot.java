package net.favouriteless.enchanted.neoforge.datagen.providers.loot_tables;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.blocks.EBlocks;
import net.favouriteless.enchanted.common.blocks.crops.CropsBlockAgeFive;
import net.favouriteless.enchanted.common.items.EItems;
import net.favouriteless.enchanted.platform.services.NeoCommonRegistryHelper;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class EBlockLoot extends BlockLootSubProvider {

    private final Set<Block> usedBlocks = new HashSet<>();

    public EBlockLoot(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        add(EBlocks.ALDER_LEAVES.get(), block -> createLeavesDrops(block, EBlocks.ALDER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(EBlocks.ALDER_SLAB.get(), this::createSlabItemTable);
        createCropBlockAgeFiveDrops(EBlocks.BELLADONNA.get(), EItems.BELLADONNA_FLOWER.get(), EItems.BELLADONNA_SEEDS.get());
        add(EBlocks.EMBER_MOSS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(EItems.EMBER_MOSS.get()))));
        add(EBlocks.GARLIC.get(),
                applyExplosionDecay(EBlocks.GARLIC.get(),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(EItems.GARLIC.get())))
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(EItems.GARLIC.get())
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(EBlocks.GARLIC.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlockAgeFive.AGE_FIVE, 4)))
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registries.holderOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))
                )
        );
        add(EBlocks.HAWTHORN_LEAVES.get(), block -> createLeavesDrops(block, EBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(EBlocks.HAWTHORN_SLAB.get(), this::createSlabItemTable);
        createCropBlockAgeFiveDrops(EBlocks.MANDRAKE.get(), EItems.MANDRAKE_ROOT.get(), EItems.MANDRAKE_SEEDS.get());
        add(EBlocks.ROWAN_LEAVES.get(), block -> createFruitLeavesDrop(block, EBlocks.ROWAN_SAPLING.get(), EItems.ROWAN_BERRIES.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(EBlocks.ROWAN_SLAB.get(), this::createSlabItemTable);
        createDualCropBlockAgeFiveDrops(EBlocks.SNOWBELL.get(), EItems.ICY_NEEDLE.get(), Items.SNOWBALL, EItems.SNOWBELL_SEEDS.get());
        add(EBlocks.SPANISH_MOSS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(EItems.SPANISH_MOSS.get()))));
        createCropBlockAgeFiveDrops(EBlocks.WATER_ARTICHOKE.get(), EItems.WATER_ARTICHOKE.get(), EItems.WATER_ARTICHOKE_SEEDS.get());
        createCropBlockAgeFiveDrops(EBlocks.WOLFSBANE.get(), EItems.WOLFSBANE_FLOWER.get(), EItems.WOLFSBANE_SEEDS.get());

        autoGenerateDefaults(); // Blocks without datagen will automatically dropSelf.
    }

    protected void autoGenerateDefaults() {
        for(Entry<ResourceKey<Block>, Block> entry : BuiltInRegistries.BLOCK.entrySet()) {
            if(!entry.getKey().location().getNamespace().equals(Enchanted.MOD_ID))
                continue;
            if(entry.getValue().getLootTable() == BuiltInLootTables.EMPTY)
                continue;
            if(usedBlocks.contains(entry.getValue()))
                continue;

            dropSelf(entry.getValue());
        }
    }

    protected void createDualCropBlockAgeFiveDrops(Block block, ItemLike crop, ItemLike otherCrop, ItemLike seeds) {
        LootItemCondition.Builder ageCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlockAgeFive.AGE_FIVE, 4));

        add(block, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(seeds))
        ).withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(crop))
                        .add(LootItem.lootTableItem(otherCrop))
                        .when(ageCondition)
        ).withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(seeds)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registries.holderOrThrow(Enchantments.FORTUNE), 0.1714286F, 3)))
                        .when(ageCondition)
        ));
    }

    protected LootTable.Builder createFruitLeavesDrop(Block leaves, Block sapling, ItemLike fruit, float... chances) {
        return createLeavesDrops(leaves, sapling, chances)
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_SHEARS.or(hasSilkTouch()).invert())
                                .add(applyExplosionCondition(leaves, LootItem.lootTableItem(fruit))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(registries.holderOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    protected void createCropBlockAgeFiveDrops(CropsBlockAgeFive block, ItemLike crop, ItemLike seeds) {
        LootItemCondition.Builder itemCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlockAgeFive.AGE_FIVE, 4));
        add(block, createCropDrops(block, crop.asItem(), seeds.asItem(), itemCondition));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return NeoCommonRegistryHelper.getRegistryMap().getDeferred(BuiltInRegistries.BLOCK).getEntries()
                .stream()
                .map(h -> (Block)h.get())
                ::iterator;
    }

    @Override
    protected void add(Block block, Builder builder) {
        usedBlocks.add(block);
        super.add(block, builder);
    }

}

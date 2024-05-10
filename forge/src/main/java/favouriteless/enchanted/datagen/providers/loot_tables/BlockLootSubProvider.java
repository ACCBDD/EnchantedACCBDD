package favouriteless.enchanted.datagen.providers.loot_tables;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.blocks.*;
import favouriteless.enchanted.common.blocks.altar.CandelabraBlock;
import favouriteless.enchanted.common.blocks.altar.ChaliceBlock;
import favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import favouriteless.enchanted.common.blocks.chalk.ChalkCircleBlock;
import favouriteless.enchanted.common.blocks.crops.*;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.platform.services.ForgeCommonRegistryHelper;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;

public class BlockLootSubProvider extends net.minecraft.data.loot.BlockLootSubProvider {

    private final Set<Block> usedBlocks = new HashSet<>();

    public BlockLootSubProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(EnchantedBlocks.ALDER_LEAVES.get(), block -> createLeavesDrops(block, EnchantedBlocks.ALDER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(EnchantedBlocks.ALDER_SLAB.get(), this::createSlabItemTable);
        createCropBlockAgeFiveDrops(EnchantedBlocks.BELLADONNA.get(), EnchantedItems.BELLADONNA_FLOWER.get(), EnchantedItems.BELLADONNA_SEEDS.get());
        add(EnchantedBlocks.EMBER_MOSS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(EnchantedItems.EMBER_MOSS.get()))));
        add(EnchantedBlocks.GARLIC.get(),
                applyExplosionDecay(EnchantedBlocks.GARLIC.get(),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(EnchantedItems.GARLIC.get())))
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(EnchantedItems.GARLIC.get())
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(EnchantedBlocks.GARLIC.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlockAgeFive.AGE_FIVE, 4)))
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))
                )
        );
        add(EnchantedBlocks.HAWTHORN_LEAVES.get(), block -> createLeavesDrops(block, EnchantedBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(EnchantedBlocks.HAWTHORN_SLAB.get(), this::createSlabItemTable);
        createCropBlockAgeFiveDrops(EnchantedBlocks.MANDRAKE.get(), EnchantedItems.MANDRAKE_ROOT.get(), EnchantedItems.MANDRAKE_SEEDS.get());
        add(EnchantedBlocks.ROWAN_LEAVES.get(), block -> createFruitLeavesDrop(block, EnchantedBlocks.ROWAN_SAPLING.get(), EnchantedItems.ROWAN_BERRIES.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(EnchantedBlocks.ROWAN_SLAB.get(), this::createSlabItemTable);
        createDualCropBlockAgeFiveDrops(EnchantedBlocks.SNOWBELL.get(), EnchantedItems.ICY_NEEDLE.get(), Items.SNOWBALL, EnchantedItems.SNOWBELL_SEEDS.get());
        add(EnchantedBlocks.SPANISH_MOSS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(EnchantedItems.SPANISH_MOSS.get()))));
        createCropBlockAgeFiveDrops(EnchantedBlocks.WATER_ARTICHOKE.get(), EnchantedItems.WATER_ARTICHOKE.get(), EnchantedItems.WATER_ARTICHOKE_SEEDS.get());
        createCropBlockAgeFiveDrops(EnchantedBlocks.WOLFSBANE.get(), EnchantedItems.WOLFSBANE_FLOWER.get(), EnchantedItems.WOLFSBANE_SEEDS.get());

        autoGenerateDefaults(); // Blocks without datagen will automatically dropSelf.
    }

    protected void autoGenerateDefaults() {
        for(Entry<ResourceKey<Block>, Block> entry : ForgeRegistries.BLOCKS.getEntries()) {
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
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.1714286F, 3)))
                        .when(ageCondition)
        ));
    }

    protected LootTable.Builder createFruitLeavesDrop(Block leaves, Block sapling, ItemLike fruit, float... chances) {
        return createLeavesDrops(leaves, sapling, chances)
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_SHEARS.or(HAS_SILK_TOUCH).invert())
                                .add(applyExplosionCondition(leaves, LootItem.lootTableItem(fruit))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    protected void createCropBlockAgeFiveDrops(CropsBlockAgeFive block, ItemLike crop, ItemLike seeds) {
        LootItemCondition.Builder itemCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlockAgeFive.AGE_FIVE, 4));
        add(block, createCropDrops(block, crop.asItem(), seeds.asItem(), itemCondition));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeCommonRegistryHelper.getRegistryMap().getDeferred(BuiltInRegistries.BLOCK).getEntries()
                .stream()
                .flatMap(RegistryObject::stream)
                ::iterator;
    }

    @Override
    protected void add(Block block, Builder builder) {
        usedBlocks.add(block);
        super.add(block, builder);
    }

}

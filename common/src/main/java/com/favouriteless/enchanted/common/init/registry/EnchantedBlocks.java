package com.favouriteless.enchanted.common.init.registry;

import com.favouriteless.enchanted.common.blocks.*;
import com.favouriteless.enchanted.common.blocks.access.EnchantedSaplingBlock;
import com.favouriteless.enchanted.common.blocks.access.EnchantedStairBlock;
import com.favouriteless.enchanted.common.blocks.altar.AltarBlock;
import com.favouriteless.enchanted.common.blocks.altar.CandelabraBlock;
import com.favouriteless.enchanted.common.blocks.altar.ChaliceBlock;
import com.favouriteless.enchanted.common.blocks.altar.ChaliceBlockMilk;
import com.favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import com.favouriteless.enchanted.common.blocks.cauldrons.WitchCauldronBlock;
import com.favouriteless.enchanted.common.blocks.chalk.ChalkCircleBlock;
import com.favouriteless.enchanted.common.blocks.chalk.GoldChalkBlock;
import com.favouriteless.enchanted.common.blocks.crops.*;
import com.favouriteless.enchanted.common.world.features.EnchantedTreeGrower;
import com.favouriteless.enchanted.platform.CommonServices;
import com.favouriteless.enchanted.platform.services.ICommonRegistryHelper;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class EnchantedBlocks {

    public static final Supplier<Block> ALDER_LEAVES = register("alder_leaves", () -> new LeavesBlock(Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<RotatedPillarBlock> ALDER_LOG = register("alder_log", () -> log(MapColor.COLOR_ORANGE, MapColor.STONE));
    public static final Supplier<Block> ALDER_PLANKS = register("alder_planks", () -> new Block(Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<SaplingBlock> ALDER_SAPLING = register("alder_sapling", () -> new EnchantedSaplingBlock(new EnchantedTreeGrower("alder_tree"), Properties.copy(Blocks.OAK_SAPLING)));
    public static final Supplier<SlabBlock> ALDER_SLAB = register("alder_slab", () -> new SlabBlock(Properties.copy(Blocks.OAK_SLAB)));
    public static final Supplier<StairBlock> ALDER_STAIRS = register("alder_stairs", () -> new EnchantedStairBlock(ALDER_PLANKS.get().defaultBlockState(), Properties.copy(ALDER_PLANKS.get())));
    public static final Supplier<Block> ALTAR = register("altar", () -> new AltarBlock(Properties.copy(Blocks.STONE).pushReaction(PushReaction.BLOCK).requiresCorrectToolForDrops()));
    public static final Supplier<CropsBlockAgeFive> ARTICHOKE = register("artichoke", () -> new ArtichokeBlock(Properties.copy(Blocks.WHEAT).sound(SoundType.LILY_PAD)));
    public static final Supplier<CropsBlockAgeFive> BELLADONNA = register("belladonna", () -> new BelladonnaBlock(Properties.copy(Blocks.WHEAT)));
    public static final Supplier<Block> BLOOD_POPPY = register("blood_poppy", () -> new BloodPoppyBlock(Properties.copy(Blocks.POPPY)));
    public static final Supplier<Block> CANDELABRA = register("candelabra", () -> new CandelabraBlock(Properties.of().strength(1.0F, 6.0F).lightLevel((state) -> 14)));
    public static final Supplier<Block> CHALICE = register("chalice", () -> new ChaliceBlock(Properties.copy(CANDELABRA.get()).noOcclusion(), false));
    public static final Supplier<Block> CHALICE_FILLED = register("chalice_filled", () -> new ChaliceBlock(Properties.copy(CHALICE.get()), true));
    public static final Supplier<Block> CHALICE_FILLED_MILK = register("chalice_filled_milk", () -> new ChaliceBlockMilk(Properties.copy(CHALICE.get())));
    public static final Supplier<Block> CHALK_GOLD = register("chalk_gold", GoldChalkBlock::new);
    public static final Supplier<Block> CHALK_PURPLE = register("chalk_purple", () -> new ChalkCircleBlock(ParticleTypes.DRAGON_BREATH));
    public static final Supplier<Block> CHALK_RED = register("chalk_red", () -> new ChalkCircleBlock(ParticleTypes.FLAME));
    public static final Supplier<Block> CHALK_WHITE = register("chalk_white", () -> new ChalkCircleBlock(null));
    public static final Supplier<Block> DISTILLERY = register("distillery", () -> new DistilleryBlock(Properties.copy(Blocks.ANVIL).lightLevel(getLightValueLit(13)).noOcclusion()));
    public static final Supplier<Block> EMBER_MOSS = register("ember_moss", () -> new EmberMossBlock(Properties.copy(Blocks.POPPY).lightLevel((a) -> 6).randomTicks()));
    public static final Supplier<Block> FUME_FUNNEL = register("fume_funnel", () -> new FumeFunnelBlock(Properties.copy(DISTILLERY.get())));
    public static final Supplier<Block> FUME_FUNNEL_FILTERED = register("fume_funnel_filtered", () -> new FumeFunnelBlock(Properties.copy(FUME_FUNNEL.get())));
    public static final Supplier<CropsBlockAgeFive> GARLIC = register("garlic", () -> new GarlicBlock(Properties.copy(Blocks.WHEAT)));
    public static final Supplier<Block> GLINT_WEED = register("glint_weed", () -> new GlintWeedBlock(Properties.copy(Blocks.POPPY).lightLevel((a) -> 14).randomTicks()));
    public static final Supplier<Block> HAWTHORN_LEAVES = register("hawthorn_leaves", () -> new LeavesBlock(Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<RotatedPillarBlock> HAWTHORN_LOG = register("hawthorn_log", () -> log(MapColor.CLAY, MapColor.CLAY));
    public static final Supplier<Block> HAWTHORN_PLANKS = register("hawthorn_planks", () -> new Block(Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<SaplingBlock> HAWTHORN_SAPLING = register("hawthorn_sapling", () -> new EnchantedSaplingBlock(new EnchantedTreeGrower("hawthorn_tree"), Properties.copy(Blocks.OAK_SAPLING)));
    public static final Supplier<SlabBlock> HAWTHORN_SLAB = register("hawthorn_slab", () -> new SlabBlock(Properties.copy(Blocks.OAK_SLAB)));
    public static final Supplier<StairBlock> HAWTHORN_STAIRS = register("hawthorn_stairs", () -> new EnchantedStairBlock(HAWTHORN_PLANKS.get().defaultBlockState(), Properties.copy(HAWTHORN_PLANKS.get())));
    public static final Supplier<Block> INFINITY_EGG = register("infinity_egg", () -> new InfinityEggBlock(Properties.copy(Blocks.DRAGON_EGG)));
    public static final Supplier<Block> KETTLE = register("kettle", () -> new KettleBlock(Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final Supplier<CropsBlockAgeFive> MANDRAKE = register("mandrake", () -> new MandrakeBlock(Properties.copy(Blocks.WHEAT)));
    public static final Supplier<Block> POPPET_SHELF = register("poppet_shelf", () -> new PoppetShelfBlock(Properties.copy(Blocks.ENCHANTING_TABLE).noOcclusion()));
    public static final Supplier<Block> PROTECTION_BARRIER = register("protection_barrier", () -> new ProtectionBarrierBlock(Properties.copy(Blocks.BARRIER)));
    public static final Supplier<Block> PROTECTION_BARRIER_TEMPORARY = register("protection_barrier_temporary", () -> new TemporaryProtectionBarrierBlock(Properties.copy(Blocks.BARRIER)));
    public static final Supplier<Block> ROWAN_LEAVES = register("rowan_leaves", () -> new LeavesBlock(Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<RotatedPillarBlock> ROWAN_LOG = register("rowan_log", () -> log(MapColor.WOOD, MapColor.PODZOL));
    public static final Supplier<Block> ROWAN_PLANKS = register("rowan_planks", () -> new Block(Properties.copy(Blocks.OAK_PLANKS)));
    public static final Supplier<SaplingBlock> ROWAN_SAPLING = register("rowan_sapling", () -> new EnchantedSaplingBlock(new EnchantedTreeGrower("rowan_tree"), Properties.copy(Blocks.OAK_SAPLING)));
    public static final Supplier<SlabBlock> ROWAN_SLAB = register("rowan_slab", () -> new SlabBlock(Properties.copy(Blocks.OAK_SLAB)));
    public static final Supplier<StairBlock> ROWAN_STAIRS = register("rowan_stairs", () -> new EnchantedStairBlock(ROWAN_PLANKS.get().defaultBlockState(), Properties.copy(ROWAN_PLANKS.get())));
    public static final Supplier<CropsBlockAgeFive> SNOWBELL = register("snowbell", () -> new SnowbellBlock(Properties.copy(Blocks.WHEAT)));
    public static final Supplier<Block> SPANISH_MOSS = register("spanish_moss", () -> new SpanishMossBlock(Properties.copy(Blocks.VINE)));
    public static final Supplier<Block> SPINNING_WHEEL = register("spinning_wheel", () -> new SpinningWheelBlock(Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Supplier<RotatedPillarBlock> WICKER_BUNDLE = register("wicker_bundle", () -> new HayBlock(Properties.copy(Blocks.HAY_BLOCK)));
    public static final Supplier<Block> WITCH_CAULDRON = register("witch_cauldron", () -> new WitchCauldronBlock(Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final Supplier<Block> WITCH_OVEN = register("witch_oven", () -> new WitchOvenBlock(Properties.copy(DISTILLERY.get())));
    public static final Supplier<CropsBlockAgeFive> WOLFSBANE = register("wolfsbane", () -> new WolfsbaneBlock(Properties.copy(Blocks.WHEAT)));

    //public static final Supplier<Block> DEMON_HEART = register("demon_heart", DemonHeart::new);

    public static void registerFlammables() {
        ICommonRegistryHelper registry = CommonServices.COMMON_REGISTRY;
        registry.setFlammable(ROWAN_LOG.get(), 5, 5);
        registry.setFlammable(ROWAN_PLANKS.get(), 5, 20);
        registry.setFlammable(ROWAN_STAIRS.get(), 5, 20);
        registry.setFlammable(ROWAN_SLAB.get(), 5, 20);
        registry.setFlammable(ROWAN_LEAVES.get(), 30, 60);
        registry.setFlammable(ALDER_LOG.get(), 5, 5);
        registry.setFlammable(ALDER_PLANKS.get(), 5, 20);
        registry.setFlammable(ALDER_STAIRS.get(), 5, 20);
        registry.setFlammable(ALDER_SLAB.get(), 5, 20);
        registry.setFlammable(ALDER_LEAVES.get(), 30, 60);
        registry.setFlammable(HAWTHORN_LOG.get(), 5, 5);
        registry.setFlammable(HAWTHORN_PLANKS.get(), 5, 20);
        registry.setFlammable(HAWTHORN_STAIRS.get(), 5, 20);
        registry.setFlammable(HAWTHORN_SLAB.get(), 5, 20);
        registry.setFlammable(HAWTHORN_LEAVES.get(), 30, 60);
    }

    //-------------------------------------------------------- UTILITY FUNCTIONS FOR CREATING BLOCKS --------------------------------------------------------

    private static <T extends Block> Supplier<T> register(String name, Supplier<T> blockSupplier) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.BLOCK, name, blockSupplier);
    }

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static RotatedPillarBlock log(MapColor topColor, MapColor barkColor) {
        return new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG)
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Axis.Y ? topColor : barkColor)
        );
    }

    public static void load() {} // Method which exists purely to load the class.

}

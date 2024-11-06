package net.favouriteless.enchanted.common.blocks;

import net.favouriteless.enchanted.common.blocks.access.EnchantedSaplingBlock;
import net.favouriteless.enchanted.common.blocks.access.EnchantedStairBlock;
import net.favouriteless.enchanted.common.blocks.altar.AltarBlock;
import net.favouriteless.enchanted.common.blocks.altar.CandelabraBlock;
import net.favouriteless.enchanted.common.blocks.altar.ChaliceBlock;
import net.favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import net.favouriteless.enchanted.common.blocks.cauldrons.WitchCauldronBlock;
import net.favouriteless.enchanted.common.blocks.chalk.ChalkCircleBlock;
import net.favouriteless.enchanted.common.blocks.chalk.GoldChalkBlock;
import favouriteless.enchanted.common.blocks.crops.*;
import net.favouriteless.enchanted.common.blocks.crops.*;
import net.favouriteless.enchanted.common.world.features.EnchantedTreeGrower;
import net.favouriteless.enchanted.platform.CommonServices;
import net.favouriteless.enchanted.platform.services.ICommonRegistryHelper;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class EBlocks {

    public static final Supplier<RotatedPillarBlock> STRIPPED_ALDER_LOG = register("stripped_alder_log", () -> log(MapColor.COLOR_ORANGE, MapColor.COLOR_ORANGE));
    public static final Supplier<RotatedPillarBlock> STRIPPED_HAWTHORN_LOG = register("stripped_hawthorn_log", () -> log(MapColor.CLAY, MapColor.CLAY));
    public static final Supplier<RotatedPillarBlock> STRIPPED_ROWAN_LOG = register("stripped_rowan_log", () -> log(MapColor.WOOD, MapColor.WOOD));
    public static final Supplier<ButtonBlock> ALDER_BUTTON = register("alder_button", EBlocks::woodenButton);
    public static final Supplier<FenceBlock> ALDER_FENCE = register("alder_fence", EBlocks::woodenFence);
    public static final Supplier<FenceGateBlock> ALDER_FENCE_GATE = register("alder_fence_gate", EBlocks::woodenGate);
    public static final Supplier<Block> ALDER_LEAVES = register("alder_leaves", EBlocks::leaves);
    public static final Supplier<EnchantedLog> ALDER_LOG = register("alder_log", () -> strippableLog(MapColor.COLOR_ORANGE, MapColor.STONE, STRIPPED_ALDER_LOG));
    public static final Supplier<Block> ALDER_PLANKS = register("alder_planks", () -> block(Blocks.OAK_PLANKS));
    public static final Supplier<PressurePlateBlock> ALDER_PRESSURE_PLATE = register("alder_pressure_plate", () -> woodenPressurePlate(MapColor.COLOR_ORANGE));
    public static final Supplier<SaplingBlock> ALDER_SAPLING = register("alder_sapling", () -> sapling("alder_tree"));
    public static final Supplier<SlabBlock> ALDER_SLAB = register("alder_slab", () -> slab(Blocks.OAK_SLAB));
    public static final Supplier<StairBlock> ALDER_STAIRS = register("alder_stairs", () -> stairs(ALDER_PLANKS.get()));
    public static final Supplier<AltarBlock> ALTAR = register("altar", AltarBlock::new);
    public static final Supplier<CropsBlockAgeFive> BELLADONNA = register("belladonna", BelladonnaBlock::new);
    public static final Supplier<BloodPoppyBlock> BLOOD_POPPY = register("blood_poppy", BloodPoppyBlock::new);
    public static final Supplier<CandelabraBlock> CANDELABRA = register("candelabra", CandelabraBlock::new);
    public static final Supplier<ChaliceBlock> CHALICE = register("chalice", () -> new ChaliceBlock(false));
    public static final Supplier<ChaliceBlock> CHALICE_FILLED = register("chalice_filled", () -> new ChaliceBlock(true));
    public static final Supplier<DistilleryBlock> DISTILLERY = register("distillery", DistilleryBlock::new);
    public static final Supplier<EmberMossBlock> EMBER_MOSS = register("ember_moss", EmberMossBlock::new);
    public static final Supplier<FumeFunnelBlock> FUME_FUNNEL = register("fume_funnel", FumeFunnelBlock::new);
    public static final Supplier<FumeFunnelBlock> FUME_FUNNEL_FILTERED = register("fume_funnel_filtered", FumeFunnelBlock::new);
    public static final Supplier<GarlicBlock> GARLIC = register("garlic", GarlicBlock::new);
    public static final Supplier<GlintWeedBlock> GLINT_WEED = register("glint_weed", GlintWeedBlock::new);
    public static final Supplier<GoldChalkBlock> GOLDEN_CHALK = register("golden_chalk", GoldChalkBlock::new);
    public static final Supplier<ButtonBlock> HAWTHORN_BUTTON = register("hawthorn_button", EBlocks::woodenButton);
    public static final Supplier<FenceBlock> HAWTHORN_FENCE = register("hawthorn_fence", EBlocks::woodenFence);
    public static final Supplier<FenceGateBlock> HAWTHORN_FENCE_GATE = register("hawthorn_fence_gate", EBlocks::woodenGate);
    public static final Supplier<Block> HAWTHORN_LEAVES = register("hawthorn_leaves", EBlocks::leaves);
    public static final Supplier<EnchantedLog> HAWTHORN_LOG = register("hawthorn_log", () -> strippableLog(MapColor.CLAY, MapColor.CLAY, STRIPPED_HAWTHORN_LOG));
    public static final Supplier<Block> HAWTHORN_PLANKS = register("hawthorn_planks", () -> block(Blocks.OAK_PLANKS));
    public static final Supplier<PressurePlateBlock> HAWTHORN_PRESSURE_PLATE = register("hawthorn_pressure_plate", () -> woodenPressurePlate(MapColor.CLAY));
    public static final Supplier<SaplingBlock> HAWTHORN_SAPLING = register("hawthorn_sapling", () -> sapling("hawthorn_tree"));
    public static final Supplier<SlabBlock> HAWTHORN_SLAB = register("hawthorn_slab", () -> slab(Blocks.OAK_SLAB));
    public static final Supplier<StairBlock> HAWTHORN_STAIRS = register("hawthorn_stairs", () -> stairs(HAWTHORN_PLANKS.get()));;
    public static final Supplier<InfinityEggBlock> INFINITY_EGG = register("infinity_egg", InfinityEggBlock::new);
    public static final Supplier<KettleBlock> KETTLE = register("kettle", KettleBlock::new);
    public static final Supplier<MandrakeBlock> MANDRAKE = register("mandrake", MandrakeBlock::new);
    public static final Supplier<ChalkCircleBlock> NETHER_CHALK = register("nether_chalk", () -> new ChalkCircleBlock(ParticleTypes.FLAME));
    public static final Supplier<ChalkCircleBlock> OTHERWHERE_CHALK = register("otherwhere_chalk", () -> new ChalkCircleBlock(ParticleTypes.DRAGON_BREATH));
    public static final Supplier<PoppetShelfBlock> POPPET_SHELF = register("poppet_shelf", PoppetShelfBlock::new);
    public static final Supplier<ProtectionBarrierBlock> PROTECTION_BARRIER = register("protection_barrier", ProtectionBarrierBlock::new);
    public static final Supplier<TemporaryProtectionBarrierBlock> PROTECTION_BARRIER_TEMPORARY = register("protection_barrier_temporary", TemporaryProtectionBarrierBlock::new);
    public static final Supplier<ChalkCircleBlock> RITUAL_CHALK = register("ritual_chalk", () -> new ChalkCircleBlock(null));
    public static final Supplier<ButtonBlock> ROWAN_BUTTON = register("rowan_button", EBlocks::woodenButton);
    public static final Supplier<FenceBlock> ROWAN_FENCE = register("rowan_fence", EBlocks::woodenFence);
    public static final Supplier<FenceGateBlock> ROWAN_FENCE_GATE = register("rowan_fence_gate", EBlocks::woodenGate);
    public static final Supplier<Block> ROWAN_LEAVES = register("rowan_leaves", EBlocks::leaves);
    public static final Supplier<EnchantedLog> ROWAN_LOG = register("rowan_log", () -> strippableLog(MapColor.WOOD, MapColor.PODZOL, STRIPPED_ROWAN_LOG));
    public static final Supplier<Block> ROWAN_PLANKS = register("rowan_planks", () -> block(Blocks.OAK_PLANKS));
    public static final Supplier<PressurePlateBlock> ROWAN_PRESSURE_PLATE = register("rowan_pressure_plate", () -> woodenPressurePlate(MapColor.WOOD));
    public static final Supplier<SaplingBlock> ROWAN_SAPLING = register("rowan_sapling", () -> sapling("rowan_tree"));
    public static final Supplier<SlabBlock> ROWAN_SLAB = register("rowan_slab", () -> slab(Blocks.OAK_SLAB));
    public static final Supplier<StairBlock> ROWAN_STAIRS = register("rowan_stairs", () -> stairs(ROWAN_PLANKS.get()));
    public static final Supplier<SnowbellBlock> SNOWBELL = register("snowbell", SnowbellBlock::new);
    public static final Supplier<SpanishMossBlock> SPANISH_MOSS = register("spanish_moss", SpanishMossBlock::new);
    public static final Supplier<SpinningWheelBlock> SPINNING_WHEEL = register("spinning_wheel", SpinningWheelBlock::new);
    public static final Supplier<ArtichokeBlock> WATER_ARTICHOKE = register("water_artichoke", ArtichokeBlock::new);
    public static final Supplier<HayBlock> WICKER_BUNDLE = register("wicker_bundle", () -> new HayBlock(Properties.ofFullCopy(Blocks.HAY_BLOCK)));
    public static final Supplier<WitchCauldronBlock> WITCH_CAULDRON = register("witch_cauldron", WitchCauldronBlock::new);
    public static final Supplier<WitchOvenBlock> WITCH_OVEN = register("witch_oven", WitchOvenBlock::new);
    public static final Supplier<WolfsbaneBlock> WOLFSBANE = register("wolfsbane", WolfsbaneBlock::new);

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
        registry.setFlammable(STRIPPED_ALDER_LOG.get(), 5, 5);
        registry.setFlammable(STRIPPED_HAWTHORN_LOG.get(), 5, 5);
        registry.setFlammable(STRIPPED_ROWAN_LOG.get(), 5, 5);
        registry.setFlammable(ALDER_FENCE.get(), 5, 20);
        registry.setFlammable(ALDER_FENCE_GATE.get(), 5, 20);
        registry.setFlammable(HAWTHORN_FENCE.get(), 5, 20);
        registry.setFlammable(HAWTHORN_FENCE_GATE.get(), 5, 20);
        registry.setFlammable(ROWAN_FENCE.get(), 5, 20);
        registry.setFlammable(ROWAN_FENCE_GATE.get(), 5, 20);
    }

    //-------------------------------------------------------- UTILITY FUNCTIONS FOR CREATING BLOCKS --------------------------------------------------------

    public static <T extends Block> Supplier<T> register(String name, Supplier<T> blockSupplier) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.BLOCK, name, blockSupplier);
    }

    public static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static RotatedPillarBlock log(MapColor topColor, MapColor barkColor) {
        return new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Axis.Y ? topColor : barkColor));
    }

    private static EnchantedLog strippableLog(MapColor topColor, MapColor barkColor, Supplier<RotatedPillarBlock> stripped) {
        return new EnchantedLog(Properties.ofFullCopy(Blocks.OAK_LOG)
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Axis.Y ? topColor : barkColor), stripped);
    }

    private static ButtonBlock woodenButton() {
        return new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 30, true);
    }

    private static FenceBlock woodenFence() {
        return new FenceBlock(Properties.ofFullCopy(Blocks.OAK_FENCE));
    }

    private static FenceGateBlock woodenGate() {
        return new FenceGateBlock(Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), WoodType.OAK);
    }

    private static PressurePlateBlock woodenPressurePlate(MapColor mapColor) {
        return new PressurePlateBlock( Properties.of().mapColor(mapColor).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava()
                .pushReaction(PushReaction.DESTROY),
                BlockSetType.OAK);
    }

    private static LeavesBlock leaves() {
        return new LeavesBlock(Properties.ofFullCopy(Blocks.OAK_LEAVES));
    }

    private static Block block(Properties properties) {
        return new Block(properties);
    }

    private static Block block(BlockBehaviour block) {
        return new Block(Properties.ofFullCopy(block));
    }

    private static Block block(Supplier<BlockBehaviour> block) {
        return block(block.get());
    }

    public static StairBlock stairs(Block base) {
        return new EnchantedStairBlock(base.defaultBlockState(), Properties.ofFullCopy(base));
    }

    public static SlabBlock slab(Block base) {
        return new SlabBlock(Properties.ofFullCopy(base));
    }

    public static SaplingBlock sapling(String id) {
        return new EnchantedSaplingBlock(new EnchantedTreeGrower(id), Properties.ofFullCopy(Blocks.OAK_SAPLING));
    }

    public static void load() {} // Method which exists purely to load the class.

}

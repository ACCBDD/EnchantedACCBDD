package favouriteless.enchanted.common.init.registry;

import favouriteless.enchanted.common.blocks.*;
import favouriteless.enchanted.common.blocks.access.EnchantedSaplingBlock;
import favouriteless.enchanted.common.blocks.access.EnchantedStairBlock;
import favouriteless.enchanted.common.blocks.altar.AltarBlock;
import favouriteless.enchanted.common.blocks.altar.CandelabraBlock;
import favouriteless.enchanted.common.blocks.altar.ChaliceBlock;
import favouriteless.enchanted.common.blocks.altar.ChaliceBlockMilk;
import favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import favouriteless.enchanted.common.blocks.cauldrons.WitchCauldronBlock;
import favouriteless.enchanted.common.blocks.chalk.ChalkCircleBlock;
import favouriteless.enchanted.common.blocks.chalk.GoldChalkBlock;
import favouriteless.enchanted.common.blocks.crops.*;
import favouriteless.enchanted.common.init.registry.util.BlockRegistryDescriptor;
import favouriteless.enchanted.common.world.features.EnchantedTreeGrower;
import favouriteless.enchanted.platform.CommonServices;
import favouriteless.enchanted.platform.services.ICommonRegistryHelper;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
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

public class EnchantedBlocks {

    public static final Supplier<ButtonBlock> ALDER_BUTTON = descriptor("alder_button", EnchantedBlocks::woodenButton).dropSelf().register();
    public static final Supplier<FenceBlock> ALDER_FENCE = descriptor("alder_fence", EnchantedBlocks::woodenFence).dropSelf().register();
    public static final Supplier<FenceGateBlock> ALDER_GATE = descriptor("alder_gate", EnchantedBlocks::woodenGate).dropSelf().register();
    public static final Supplier<LeavesBlock> ALDER_LEAVES = descriptor("alder_leaves", EnchantedBlocks::leaves).register();
    public static final Supplier<RotatedPillarBlock> ALDER_LOG = descriptor("alder_log", () -> log(MapColor.COLOR_ORANGE, MapColor.STONE)).dropSelf().register();
    public static final Supplier<Block> ALDER_PLANKS = descriptor("alder_planks", () -> block(Properties.copy(Blocks.OAK_PLANKS))).dropSelf().register();
    public static final Supplier<PressurePlateBlock> ALDER_PRESSURE_PLATE = descriptor("alder_pressure_plate", () -> woodenPressurePlate(MapColor.COLOR_ORANGE)).dropSelf().register();
    public static final Supplier<SaplingBlock> ALDER_SAPLING = descriptor("alder_sapling", () -> sapling("alder_tree")).dropSelf().register();;
    public static final Supplier<SlabBlock> ALDER_SLAB = descriptor("alder_slab", () -> slab(Blocks.OAK_SLAB)).register();
    public static final Supplier<StairBlock> ALDER_STAIRS = descriptor("alder_stairs", () -> stairs(ALDER_PLANKS.get())).dropSelf().register();
    public static final Supplier<AltarBlock> ALTAR = descriptor("altar", AltarBlock::new).dropSelf().register();
    public static final Supplier<ArtichokeBlock> ARTICHOKE = descriptor("artichoke", ArtichokeBlock::new).register();
    public static final Supplier<BelladonnaBlock> BELLADONNA = descriptor("belladonna", BelladonnaBlock::new).register();
    public static final Supplier<BloodPoppyBlock> BLOOD_POPPY = descriptor("blood_poppy", BloodPoppyBlock::new).dropSelf().register();
    public static final Supplier<CandelabraBlock> CANDELABRA = descriptor("candelabra", CandelabraBlock::new).dropSelf().register();
    public static final Supplier<ChaliceBlock> CHALICE = descriptor("chalice", () -> new ChaliceBlock(false)).dropSelf().register();
    public static final Supplier<ChaliceBlock> CHALICE_FILLED = descriptor("chalice_filled", () -> new ChaliceBlock(true)).dropSelf().register();
    public static final Supplier<ChaliceBlockMilk> CHALICE_FILLED_MILK = descriptor("chalice_filled_milk", ChaliceBlockMilk::new).dropSelf().register();
    public static final Supplier<GoldChalkBlock> CHALK_GOLD = descriptor("chalk_gold", GoldChalkBlock::new).register();
    public static final Supplier<ChalkCircleBlock> CHALK_PURPLE = descriptor("chalk_purple", () -> new ChalkCircleBlock(ParticleTypes.DRAGON_BREATH)).register();
    public static final Supplier<ChalkCircleBlock> CHALK_RED = descriptor("chalk_red", () -> new ChalkCircleBlock(ParticleTypes.FLAME)).register();
    public static final Supplier<ChalkCircleBlock> CHALK_WHITE = descriptor("chalk_white", () -> new ChalkCircleBlock(null)).register();
    public static final Supplier<DistilleryBlock> DISTILLERY = descriptor("distillery", DistilleryBlock::new).dropSelf().register();
    public static final Supplier<EmberMossBlock> EMBER_MOSS = descriptor("ember_moss", EmberMossBlock::new).dropSelf().tools(() -> Items.SHEARS).register();
    public static final Supplier<FumeFunnelBlock> FUME_FUNNEL = descriptor("fume_funnel", () -> new FumeFunnelBlock(Properties.copy(DISTILLERY.get()))).dropSelf().register();
    public static final Supplier<FumeFunnelBlock> FUME_FUNNEL_FILTERED = descriptor("fume_funnel_filtered", () -> new FumeFunnelBlock(Properties.copy(FUME_FUNNEL.get()))).dropSelf().register();
    public static final Supplier<GarlicBlock> GARLIC = descriptor("garlic", GarlicBlock::new).register();
    public static final Supplier<GlintWeedBlock> GLINT_WEED = descriptor("glint_weed", GlintWeedBlock::new).dropSelf().register();
    public static final Supplier<ButtonBlock> HAWTHORN_BUTTON = descriptor("hawthorn_button", EnchantedBlocks::woodenButton).dropSelf().register();
    public static final Supplier<FenceBlock> HAWTHORN_FENCE = descriptor("hawthorn_fence", EnchantedBlocks::woodenFence).dropSelf().register();
    public static final Supplier<FenceGateBlock> HAWTHORN_GATE = descriptor("hawthorn_gate", EnchantedBlocks::woodenGate).dropSelf().register();
    public static final Supplier<LeavesBlock> HAWTHORN_LEAVES = descriptor("hawthorn_leaves", EnchantedBlocks::leaves).register();
    public static final Supplier<RotatedPillarBlock> HAWTHORN_LOG = descriptor("hawthorn_log", () -> log(MapColor.CLAY, MapColor.CLAY)).dropSelf().register();
    public static final Supplier<Block> HAWTHORN_PLANKS = descriptor("hawthorn_planks", () -> block(Blocks.OAK_PLANKS)).dropSelf().register();
    public static final Supplier<PressurePlateBlock> HAWTHORN_PRESSURE_PLATE = descriptor("hawthorn_pressure_plate", () -> woodenPressurePlate(MapColor.CLAY)).dropSelf().register();
    public static final Supplier<SaplingBlock> HAWTHORN_SAPLING = descriptor("hawthorn_sapling", () -> sapling("hawthorn_tree")).dropSelf().register();
    public static final Supplier<SlabBlock> HAWTHORN_SLAB = descriptor("hawthorn_slab", () -> slab(Blocks.OAK_SLAB)).register();
    public static final Supplier<StairBlock> HAWTHORN_STAIRS = descriptor("hawthorn_stairs", () -> stairs(HAWTHORN_PLANKS.get())).dropSelf().register();
    public static final Supplier<InfinityEggBlock> INFINITY_EGG = descriptor("infinity_egg", InfinityEggBlock::new).register();
    public static final Supplier<KettleBlock> KETTLE = descriptor("kettle", KettleBlock::new).dropSelf().register();
    public static final Supplier<MandrakeBlock> MANDRAKE = descriptor("mandrake", MandrakeBlock::new).register();
    public static final Supplier<PoppetShelfBlock> POPPET_SHELF = descriptor("poppet_shelf", PoppetShelfBlock::new).dropSelf().register();
    public static final Supplier<ProtectionBarrierBlock> PROTECTION_BARRIER = descriptor("protection_barrier", ProtectionBarrierBlock::new).register();
    public static final Supplier<TemporaryProtectionBarrierBlock> PROTECTION_BARRIER_TEMPORARY = descriptor("protection_barrier_temporary", TemporaryProtectionBarrierBlock::new).register();
    public static final Supplier<ButtonBlock> ROWAN_BUTTON = descriptor("rowan_button", EnchantedBlocks::woodenButton).dropSelf().register();
    public static final Supplier<FenceBlock> ROWAN_FENCE = descriptor("rowan_fence", EnchantedBlocks::woodenFence).dropSelf().register();
    public static final Supplier<FenceGateBlock> ROWAN_GATE = descriptor("rowan_gate", EnchantedBlocks::woodenGate).dropSelf().register();
    public static final Supplier<LeavesBlock> ROWAN_LEAVES = descriptor("rowan_leaves", EnchantedBlocks::leaves).register();
    public static final Supplier<RotatedPillarBlock> ROWAN_LOG = descriptor("rowan_log", () -> log(MapColor.WOOD, MapColor.PODZOL)).dropSelf().register();
    public static final Supplier<Block> ROWAN_PLANKS = descriptor("rowan_planks", () -> block(Properties.copy(Blocks.OAK_PLANKS))).dropSelf().register();
    public static final Supplier<PressurePlateBlock> ROWAN_PRESSURE_PLATE = descriptor("rowan_pressure_plate", () -> woodenPressurePlate(MapColor.WOOD)).dropSelf().register();
    public static final Supplier<SaplingBlock> ROWAN_SAPLING = descriptor("rowan_sapling", () -> sapling("rowan_tree")).dropSelf().register();
    public static final Supplier<SlabBlock> ROWAN_SLAB = descriptor("rowan_slab", () -> slab(Blocks.OAK_SLAB)).register();
    public static final Supplier<StairBlock> ROWAN_STAIRS = descriptor("rowan_stairs", () -> stairs(ROWAN_PLANKS.get())).dropSelf().register();
    public static final Supplier<SnowbellBlock> SNOWBELL = descriptor("snowbell", SnowbellBlock::new).register();
    public static final Supplier<SpanishMossBlock> SPANISH_MOSS = descriptor("spanish_moss", SpanishMossBlock::new).dropSelf().tools(() -> Items.SHEARS).register();
    public static final Supplier<SpinningWheelBlock> SPINNING_WHEEL = descriptor("spinning_wheel", SpinningWheelBlock::new).dropSelf().register();
    public static final Supplier<RotatedPillarBlock> STRIPPED_ALDER_LOG = descriptor("stripped_alder_log", () -> log(MapColor.COLOR_ORANGE, MapColor.COLOR_ORANGE)).dropSelf().register();
    public static final Supplier<RotatedPillarBlock> STRIPPED_HAWTHORN_LOG = descriptor("stripped_hawthorn_log", () -> log(MapColor.CLAY, MapColor.CLAY)).dropSelf().register();
    public static final Supplier<RotatedPillarBlock> STRIPPED_ROWAN_LOG = descriptor("stripped_rowan_log", () -> log(MapColor.WOOD, MapColor.WOOD)).dropSelf().register();
    public static final Supplier<HayBlock> WICKER_BUNDLE = descriptor("wicker_bundle", () -> new HayBlock(Properties.copy(Blocks.HAY_BLOCK))).dropSelf().register();
    public static final Supplier<WitchCauldronBlock> WITCH_CAULDRON = descriptor("witch_cauldron", WitchCauldronBlock::new).dropSelf().register();
    public static final Supplier<WitchOvenBlock> WITCH_OVEN = descriptor("witch_oven", WitchOvenBlock::new).dropSelf().register();
    public static final Supplier<WolfsbaneBlock> WOLFSBANE = descriptor("wolfsbane", WolfsbaneBlock::new).register();

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

    public static <T extends Block> Supplier<T> register(String name, Supplier<T> blockSupplier) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.BLOCK, name, blockSupplier);
    }

    public static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static RotatedPillarBlock log(MapColor topColor, MapColor barkColor) {
        return new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG)
                .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Axis.Y ? topColor : barkColor)
        );
    }

    private static ButtonBlock woodenButton() {
        return new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 30, true);
    }

    private static FenceBlock woodenFence() {
        return new FenceBlock(Properties.copy(Blocks.OAK_FENCE));
    }

    private static FenceGateBlock woodenGate() {
        return new FenceGateBlock(Properties.copy(Blocks.OAK_FENCE_GATE), WoodType.OAK);
    }

    private static PressurePlateBlock woodenPressurePlate(MapColor mapColor) {
        return new PressurePlateBlock(Sensitivity.EVERYTHING,
                Properties.of().mapColor(mapColor).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY),
                BlockSetType.OAK);
    }

    private static LeavesBlock leaves() {
        return new LeavesBlock(Properties.copy(Blocks.OAK_LEAVES));
    }

    private static Block block(Properties properties) {
        return new Block(properties);
    }

    private static Block block(BlockBehaviour block) {
        return new Block(Properties.copy(block));
    }

    private static Block block(Supplier<BlockBehaviour> block) {
        return block(block.get());
    }

    public static StairBlock stairs(Block base) {
        return new EnchantedStairBlock(base.defaultBlockState(), Properties.copy(base));
    }

    public static SlabBlock slab(Block base) {
        return new SlabBlock(Properties.copy(base));
    }

    public static SaplingBlock sapling(String id) {
        return new EnchantedSaplingBlock(new EnchantedTreeGrower(id), Properties.copy(Blocks.OAK_SAPLING));
    }

    private static <T extends Block> BlockRegistryDescriptor<T> descriptor(String name, Supplier<T> supplier) {
        return BlockRegistryDescriptor.of(name, supplier);
    }

    public static void load() {} // Method which exists purely to load the class.

}

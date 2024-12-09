package net.favouriteless.enchanted.common.init.registry;

import net.favouriteless.enchanted.common.blocks.entity.*;
import net.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class EnchantedBlockEntityTypes {

    public static final Supplier<BlockEntityType<AltarBlockEntity>> ALTAR = register("altar",
            () -> BlockEntityType.Builder.of(AltarBlockEntity::new, EBlocks.ALTAR.get()).build(null));
    public static final Supplier<BlockEntityType<BloodPoppyBlockEntity>> BLOOD_POPPY = register("blood_poppy",
            () -> BlockEntityType.Builder.of(BloodPoppyBlockEntity::new, EBlocks.BLOOD_POPPY.get()).build(null));
    public static final Supplier<BlockEntityType<GoldChalkBlockEntity>> GOLD_CHALK = register("chalk_gold",
            () -> BlockEntityType.Builder.of(GoldChalkBlockEntity::new, EBlocks.GOLDEN_CHALK.get()).build(null));
    public static final Supplier<BlockEntityType<DistilleryBlockEntity>> DISTILLERY = register("distillery",
            () -> BlockEntityType.Builder.of(DistilleryBlockEntity::new, EBlocks.DISTILLERY.get()).build(null));
    public static final Supplier<BlockEntityType<KettleBlockEntity>> KETTLE = register("kettle",
            () -> BlockEntityType.Builder.of(KettleBlockEntity::new, EBlocks.KETTLE.get()).build(null));
    public static final Supplier<BlockEntityType<PoppetShelfBlockEntity>> POPPET_SHELF = register("poppet_shelf",
            () -> BlockEntityType.Builder.of(PoppetShelfBlockEntity::new, EBlocks.POPPET_SHELF.get()).build(null));
    public static final Supplier<BlockEntityType<SpinningWheelBlockEntity>> SPINNING_WHEEL = register("spinning_wheel",
            () -> BlockEntityType.Builder.of(SpinningWheelBlockEntity::new, EBlocks.SPINNING_WHEEL.get()).build(null));
    public static final Supplier<BlockEntityType<WitchCauldronBlockEntity>> WITCH_CAULDRON = register("witch_cauldron",
            () -> BlockEntityType.Builder.of(WitchCauldronBlockEntity::new, EBlocks.WITCH_CAULDRON.get()).build(null));
    public static final Supplier<BlockEntityType<WitchOvenBlockEntity>> WITCH_OVEN = register("witch_oven",
            () -> BlockEntityType.Builder.of(WitchOvenBlockEntity::new, EBlocks.WITCH_OVEN.get()).build(null));



    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, Supplier<BlockEntityType<T>> type) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, name, type);
    }

    public static void load() {} // Method which exists purely to load the class.

}

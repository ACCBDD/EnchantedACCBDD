package net.favouriteless.enchanted.common;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.DoubleValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {

    public static final CommonConfig INSTANCE;
    public static final ModConfigSpec SPEC;

    public final IntValue altarRange;
    public final DoubleValue altarBaseRecharge;

    public final BooleanValue cauldronItemSpoil;
    public final BooleanValue kettleItemSpoil;

    public final DoubleValue broilingBurnChance;
    public final BooleanValue disableMisfortune;
    public final BooleanValue disableOverheating;
    public final BooleanValue disableSinking;
    public final BooleanValue disableBlight;
    public final DoubleValue blightDecayChance;
    public final DoubleValue blightZombieChance;
    public final DoubleValue fertilityBoneMealChance;
    public final IntValue forestTreeCount;
    public final IntValue forestRadius;

    public final BooleanValue hoeOnlySeeds;
    public final BooleanValue disableTotems;
    public final DoubleValue entAxeMultiplier;

    private CommonConfig(ModConfigSpec.Builder builder) {
        builder.push("Altar Options");
        altarRange = builder.comment("Range of altars (DEFAULT: 16)").defineInRange("altar_range", 16, 1, Integer.MAX_VALUE);
        altarBaseRecharge = builder.comment("Multiplier for altar power recharge rates (DEFAULT: 2.0)").defineInRange("altar_recharge_rate", 2.0D, 0.0D, Double.MAX_VALUE);
        builder.pop();

        builder.push("Cauldron Options");
        cauldronItemSpoil = builder.comment("Allow incorrect items to spoil brew (DEFAULT: true)").define("cauldron_item_spoil", true);
        builder.pop();

        builder.push("Kettle Options");
        kettleItemSpoil = builder.comment("Allow incorrect items to spoil brew (DEFAULT: true)").define("kettle_item_spoil", true);
        builder.pop();

        builder.push("Rite Options");
        broilingBurnChance = builder.comment("Chance for food to be burned by the rite of broiling (RANGE: 0-1, DEFAULT: 0.3)").defineInRange("broiling_burn_chance", 0.3D, 0, 1);
        disableMisfortune = builder.comment("Disable the curse of misfortune (DEFAULT: false)").define("disable_misfortune", false);
        disableOverheating = builder.comment("Disable the curse of overheating (DEFAULT: false)").define("disable_overheating", false);
        disableSinking = builder.comment("Disable the curse of sinking (DEFAULT: false)").define("disable_sinking", false);
        disableBlight = builder.comment("Disable the curse of blight (DEFAULT: false)").define("disable_blight", false);
        blightDecayChance = builder.comment("Chance for blocks to be decayed by the curse of blight (RANGE: 0-1, DEFAULT: 0.3)").defineInRange("blight_decay_chance", 0.3D, 0, 1);
        blightZombieChance = builder.comment("Chance for villagers to be zombified by the curse of blight (RANGE: 0-1, DEFAULT: 0.3)").defineInRange("blight_zombie_chance", 0.3D, 0, 1);
        fertilityBoneMealChance = builder.comment("Chance for blocks to be bone mealed by the rite of fertility (RANGE: 0-1, DEFAULT: 0.8)").defineInRange("fertility_bone_meal_chance", 0.8D, 0, 1);
        forestTreeCount = builder.comment("Average tree count for the rite of the forest to place (DEFAULT: 25)").defineInRange("forest_tree_amount", 25, 0, Integer.MAX_VALUE);
        forestRadius = builder.comment("Maximum radius trees can be placed in by the rite of the forest (DEFAULT: 25)").defineInRange("forest_tree_radius", 25, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("Miscellaneous Options");
        hoeOnlySeeds = builder.comment("Only drop modded seeds when a hoe is used to break grass. (DEFAULT: false)").define("hoe_seeds", false);
        disableTotems = builder.comment("Disable totems of undying (to make poppets more useful) (DEFAULT: false)").define("disable_totems", false);
        entAxeMultiplier = builder.comment("The damage multiplier for axes against an Ent (DEFAULT: 3.0)").defineInRange("ent_axe_multiplier", 3.0D, 0, Double.MAX_VALUE);
        builder.pop();
    }

    static {
        Pair<CommonConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        INSTANCE = pair.getLeft();
        SPEC = pair.getRight();
    }

}
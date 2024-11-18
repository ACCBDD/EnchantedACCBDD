package net.favouriteless.enchanted.common;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec.DoubleValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CommonConfig {

    public static final CommonConfig INSTANCE;
    public static final ModConfigSpec SPEC;

    public final ConfigValue<List<? extends String>> disabledRites;

    public final IntValue altarRange;
    public final DoubleValue altarBaseRecharge;

    public final BooleanValue cauldronItemSpoil;
    public final BooleanValue kettleItemSpoil;

    public final BooleanValue hoeOnlySeeds;
    public final BooleanValue disableTotems;
    public final DoubleValue entAxeMultiplier;


    private CommonConfig(ModConfigSpec.Builder builder) {
        builder.push("Circle Magic Options");
        disabledRites = builder.comment("Disabled rite types").defineListAllowEmpty("disabled_rites", List::of, () -> "", e -> true);
        builder.pop();

        builder.push("Altar Options");
        altarRange = builder.comment("Range of altars").defineInRange("altar_range", 16, 1, Integer.MAX_VALUE);
        altarBaseRecharge = builder.comment("Multiplier for altar power recharge rates").defineInRange("altar_recharge_rate", 2.0D, 0.0D, Double.MAX_VALUE);
        builder.pop();

        builder.push("Cauldron Options");
        cauldronItemSpoil = builder.comment("Allow incorrect items to spoil brew (DEFAULT: true)").define("cauldron_item_spoil", true);
        builder.pop();

        builder.push("Kettle Options");
        kettleItemSpoil = builder.comment("Allow incorrect items to spoil brew (DEFAULT: true)").define("kettle_item_spoil", true);
        builder.pop();

        builder.push("Miscellaneous Options");
        hoeOnlySeeds = builder.comment("Only drop modded seeds when a hoe is used to break grass. (DEFAULT: false)").define("hoe_seeds", false);
        disableTotems = builder.comment("Disable totems of undying (to make poppets more useful) (DEFAULT: false)").define("disable_totems", false);
        entAxeMultiplier = builder.comment("The damage multiplier for axes against an Ent").defineInRange("ent_axe_multiplier", 3.0D, 0, Double.MAX_VALUE);
        builder.pop();
    }

    static {
        Pair<CommonConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        INSTANCE = pair.getLeft();
        SPEC = pair.getRight();
    }

}
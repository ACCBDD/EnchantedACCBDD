package com.favouriteless.enchanted.common.init.registry;

import com.favouriteless.enchanted.common.init.EnchantedTags;
import com.favouriteless.enchanted.common.items.*;
import com.favouriteless.enchanted.common.items.brews.SimpleEffectBrewItem;
import com.favouriteless.enchanted.common.items.brews.throwable.LoveBrewItem;
import com.favouriteless.enchanted.common.items.poppets.*;
import com.favouriteless.enchanted.common.poppet.PoppetColour;
import com.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;

import java.util.function.Supplier;

public class EnchantedItems {

	public static final Supplier<Item> ALDER_LEAVES = registerBlock("alder_leaves", EnchantedBlocks.ALDER_LEAVES);
	public static final Supplier<Item> ALDER_LOG = registerBlock("alder_log", EnchantedBlocks.ALDER_LOG);
	public static final Supplier<Item> ALDER_PLANKS = registerBlock("alder_planks", EnchantedBlocks.ALDER_PLANKS);
	public static final Supplier<Item> ALDER_SAPLING = registerBlock("alder_sapling", EnchantedBlocks.ALDER_SAPLING);
	public static final Supplier<Item> ALDER_SLAB = registerBlock("alder_slab", EnchantedBlocks.ALDER_SLAB);
	public static final Supplier<Item> ALDER_STAIRS = registerBlock("alder_stairs", EnchantedBlocks.ALDER_STAIRS);
	public static final Supplier<Item> ALTAR = registerBlock("altar", EnchantedBlocks.ALTAR);
	public static final Supplier<Item> ANOINTING_PASTE = register("anointing_paste", () -> new AnointingPasteItem(defaultProperties()));
	public static final Supplier<Item> ARMOUR_POPPET = register("armour_poppet", () -> new ItemProtectionPoppetItem(0.3F, 1, 0.9F, PoppetColour.EQUIPMENT));
	public static final Supplier<Item> ARMOUR_POPPET_INFUSED = register("armour_poppet_infused", () -> new ItemProtectionPoppetItem(0.0F, 1, 0.0F, PoppetColour.EQUIPMENT));
	public static final Supplier<Item> ARMOUR_POPPET_STURDY = register("armour_poppet_sturdy", () -> new ItemProtectionPoppetItem(0.0F, 2, 0.9F, PoppetColour.EQUIPMENT));
	public static final Supplier<Item> ARTHANA = registerSword("arthana", Tiers.GOLD, 3, -2.4F);
	public static final Supplier<Item> ARTICHOKE = registerFood("artichoke", 3, MobEffects.HUNGER, 100, 0, 1.0F);
	public static final Supplier<Item> ARTICHOKE_SEEDS = register("artichoke_seeds", () -> new ArtichokeSeedsItem(EnchantedBlocks.ARTICHOKE.get(), defaultProperties()));
	public static final Supplier<Item> ATTUNED_STONE = registerItem("attuned_stone");
	public static final Supplier<Item> ATTUNED_STONE_CHARGED = register("attuned_stone_charged",  () -> new SimpleFoiledItem(defaultProperties()));
	public static final Supplier<Item> BELLADONNA_FLOWER = registerItem("belladonna_flower");
	public static final Supplier<Item> BELLADONNA_SEEDS = registerBlockNamed("belladonna_seeds", EnchantedBlocks.BELLADONNA);
	public static final Supplier<Item> BLOODED_WAYSTONE = register("blooded_waystone", () -> new BloodedWaystoneItem(new Properties()));
	public static final Supplier<Item> BLOOD_POPPY = registerBlock("blood_poppy", EnchantedBlocks.BLOOD_POPPY);
	public static final Supplier<Item> BONE_NEEDLE = registerItem("bone_needle");
	public static final Supplier<Item> BOUND_WAYSTONE = registerNoTab("bound_waystone");
	public static final Supplier<Item> BREATH_OF_THE_GODDESS = registerItem("breath_of_the_goddess");
	public static final Supplier<Item> BREW_OF_LOVE = register("brew_of_love", () -> new LoveBrewItem(defaultProperties()));
	public static final Supplier<Item> BREW_OF_SPROUTING = registerItem("brew_of_sprouting");
	public static final Supplier<Item> BREW_OF_THE_DEPTHS = registerBrew("brew_of_the_depths", MobEffects.WATER_BREATHING, 6000, 0);
	public static final Supplier<Item> BREW_OF_THE_GROTESQUE = registerItem("brew_of_the_grotesque");
	public static final Supplier<Item> BROOM = register("broom", () -> new BroomItem(defaultProperties()));
	public static final Supplier<Item> CANDELABRA = registerBlock("candelabra", EnchantedBlocks.CANDELABRA);
	public static final Supplier<Item> CHALICE = registerBlock("chalice", EnchantedBlocks.CHALICE);
	public static final Supplier<Item> CHALICE_FILLED = registerBlock("chalice_filled", EnchantedBlocks.CHALICE_FILLED);
	public static final Supplier<Item> CHALICE_FILLED_MILK = registerBlock("chalice_filled_milk", EnchantedBlocks.CHALICE_FILLED_MILK);
	public static final Supplier<Item> CHALK_GOLD = registerChalk("chalk_gold", EnchantedBlocks.CHALK_GOLD, 3);
	public static final Supplier<Item> CHALK_PURPLE = registerChalk("chalk_purple", EnchantedBlocks.CHALK_PURPLE, 40);
	public static final Supplier<Item> CHALK_RED = registerChalk("chalk_red", EnchantedBlocks.CHALK_RED, 40);
	public static final Supplier<Item> CHALK_WHITE = registerChalk("chalk_white", EnchantedBlocks.CHALK_WHITE, 40);
	public static final Supplier<Item> CIRCLE_TALISMAN = register("circle_talisman", () -> new CircleTalismanItem(defaultProperties().stacksTo(1)));
	public static final Supplier<Item> CLAY_JAR = registerItem("clay_jar");
	public static final Supplier<Item> CLAY_JAR_SOFT = registerItem("clay_jar_soft");
	public static final Supplier<Item> CONDENSED_FEAR = registerItem("condensed_fear");
	public static final Supplier<Item> CREEPER_HEART = registerItem("creeper_heart");
	public static final Supplier<Item> DEMONIC_BLOOD = registerItem("demonic_blood");
	public static final Supplier<Item> DEMON_HEART = registerItem("demon_heart");
	public static final Supplier<Item> DIAMOND_VAPOUR = registerItem("diamond_vapour");
	public static final Supplier<Item> DISTILLERY = registerBlock("distillery", EnchantedBlocks.DISTILLERY);
	public static final Supplier<Item> DROP_OF_LUCK = registerItem("drop_of_luck");
	public static final Supplier<NonAnimatedArmorItem> EARMUFFS = registerNonAnimatedArmor("earmuffs", ArmorMaterials.LEATHER, Type.HELMET, defaultProperties());
	public static final Supplier<Item> EARTH_POPPET = register("earth_poppet", () -> new DeathPoppetItem(0.3F, 1, PoppetColour.EARTH, source -> source.is(DamageTypeTags.IS_FALL) || source.is(DamageTypes.FLY_INTO_WALL)));
	public static final Supplier<Item> EARTH_POPPET_INFUSED = register("earth_poppet_infused", () -> new DeathPoppetEffectItem(0.0F, 1, PoppetColour.EARTH, () -> new MobEffectInstance(EnchantedEffects.FALL_RESISTANCE.get(), 200), source -> source.is(DamageTypeTags.IS_FALL) || source.is(DamageTypes.FLY_INTO_WALL)));
	public static final Supplier<Item> EARTH_POPPET_STURDY = register("earth_poppet_sturdy", () -> new DeathPoppetItem(0.0F, 2, PoppetColour.EARTH, source -> source.is(DamageTypeTags.IS_FALL) || source.is(DamageTypes.FLY_INTO_WALL)));
	public static final Supplier<Item> EMBER_MOSS = registerBlock("ember_moss", EnchantedBlocks.EMBER_MOSS);
	public static final Supplier<Item> ENCHANTED_BROOMSTICK = register("enchanted_broomstick",  () -> new BroomstickItem(defaultProperties()));
	public static final Supplier<Item> ENDER_DEW = registerItem("ender_dew");
	public static final Supplier<Item> ENT_TWIG = registerItem("ent_twig");
	public static final Supplier<Item> EXHALE_OF_THE_HORNED_ONE = registerItem("exhale_of_the_horned_one");
	public static final Supplier<Item> FIRE_POPPET = register("fire_poppet", () -> new FirePoppetItem(0.3F, 1, PoppetColour.FIRE, source -> source.is(DamageTypeTags.IS_FIRE)));
	public static final Supplier<Item> FIRE_POPPET_INFUSED = register("fire_poppet_infused", () -> new FirePoppetEffectItem(0.0F, 1, PoppetColour.FIRE, () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200), source -> source.is(DamageTypeTags.IS_FIRE)));
	public static final Supplier<Item> FIRE_POPPET_STURDY =  register("fire_poppet_sturdy", () -> new FirePoppetItem(0.0F, 2, PoppetColour.FIRE, source -> source.is(DamageTypeTags.IS_FIRE)));
	public static final Supplier<Item> FLYING_OINTMENT = registerBrew("flying_ointment", MobEffects.LEVITATION, 400, 0);
	public static final Supplier<Item> FOCUSED_WILL = registerItem("focused_will");
	public static final Supplier<Item> FOUL_FUME = registerItem("foul_fume");
	public static final Supplier<Item> FUME_FILTER = registerItem("fume_filter");
	public static final Supplier<Item> FUME_FUNNEL = registerBlock("fume_funnel", EnchantedBlocks.FUME_FUNNEL);
	public static final Supplier<Item> FUME_FUNNEL_FILTERED = registerBlock("fume_funnel_filtered", EnchantedBlocks.FUME_FUNNEL_FILTERED);
	public static final Supplier<Item> GARLIC = registerBlockNamed("garlic", EnchantedBlocks.GARLIC);
	public static final Supplier<Item> GHOST_OF_THE_LIGHT = registerBrew("ghost_of_the_light", MobEffects.POISON, 1200, 1);
	public static final Supplier<Item> GLINT_WEED = registerBlock("glint_weed", EnchantedBlocks.GLINT_WEED);
	public static final Supplier<Item> GYPSUM = registerItem("gypsum");
	public static final Supplier<Item> HAPPENSTANCE_OIL = registerBrew("happenstance_oil", MobEffects.NIGHT_VISION, 1200, 0);
	public static final Supplier<Item> HAWTHORN_LEAVES = registerBlock("hawthorn_leaves", EnchantedBlocks.HAWTHORN_LEAVES);
	public static final Supplier<Item> HAWTHORN_LOG = registerBlock("hawthorn_log", EnchantedBlocks.HAWTHORN_LOG);
	public static final Supplier<Item> HAWTHORN_PLANKS = registerBlock("hawthorn_planks", EnchantedBlocks.HAWTHORN_PLANKS);
	public static final Supplier<Item> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling", EnchantedBlocks.HAWTHORN_SAPLING);
	public static final Supplier<Item> HAWTHORN_SLAB = registerBlock("hawthorn_slab", EnchantedBlocks.HAWTHORN_SLAB);
	public static final Supplier<Item> HAWTHORN_STAIRS = registerBlock("hawthorn_stairs", EnchantedBlocks.HAWTHORN_STAIRS);
	public static final Supplier<Item> HINT_OF_REBIRTH = registerItem("hint_of_rebirth");
	public static final Supplier<Item> HUNGER_POPPET = register("hunger_poppet", () -> new DeathPoppetItem(0.3F, 1, PoppetColour.HUNGER, source -> source.is(DamageTypes.STARVE)));
	public static final Supplier<Item> HUNGER_POPPET_INFUSED = register("hunger_poppet_infused", () -> new DeathPoppetEffectItem( 0.0F, 1, PoppetColour.HUNGER, () -> new MobEffectInstance(MobEffects.SATURATION, 100), source -> source.is(DamageTypes.STARVE)));
	public static final Supplier<Item> HUNGER_POPPET_STURDY = register("hunger_poppet_sturdy", () -> new DeathPoppetItem(0.0F, 2, PoppetColour.HUNGER, source -> source.is(DamageTypes.STARVE)));
	public static final Supplier<Item> ICY_NEEDLE = registerItem("icy_needle");
	public static final Supplier<Item> INFERNAL_ANIMUS = registerBrew("infernal_animus", MobEffects.WITHER, 1200, 2);
	public static final Supplier<Item> INFINITY_EGG = registerBlock("infinity_egg", EnchantedBlocks.INFINITY_EGG);
	public static final Supplier<Item> KETTLE = registerBlock("kettle", EnchantedBlocks.KETTLE);
	public static final Supplier<Item> MANDRAKE_ROOT = registerItem("mandrake_root");
	public static final Supplier<Item> MANDRAKE_SEEDS = registerBlockNamed("mandrake_seeds", EnchantedBlocks.MANDRAKE);
	public static final Supplier<Item> MELLIFLUOUS_HUNGER = registerItem("mellifluous_hunger");
	public static final Supplier<Item> MUTANDIS = register("mutandis", () -> new MutandisItem(EnchantedTags.Blocks.MUTANDIS_PLANTS, defaultProperties()));
	public static final Supplier<Item> MUTANDIS_EXTREMIS = register("mutandis_extremis", () -> new MutandisItem(EnchantedTags.Blocks.MUTANDIS_EXTREMIS_PLANTS, defaultProperties()));
	public static final Supplier<Item> MYSTIC_UNGUENT = registerBrew("mystic_unguent", MobEffects.WEAKNESS, 1200, 1);
	public static final Supplier<Item> ODOUR_OF_PURITY = registerItem("odour_of_purity");
	public static final Supplier<Item> OIL_OF_VITRIOL = registerItem("oil_of_vitriol");
	public static final Supplier<Item> POPPET = registerItem("poppet");
	public static final Supplier<Item> POPPET_INFUSED = registerItem("poppet_infused");
	public static final Supplier<Item> POPPET_SHELF = registerBlock("poppet_shelf", EnchantedBlocks.POPPET_SHELF);
	public static final Supplier<Item> POPPET_STURDY = registerItem("poppet_sturdy");
	public static final Supplier<Item> PURIFIED_MILK = registerItem("purified_milk");
	public static final Supplier<Item> QUICKLIME = registerItem("quicklime");
	public static final Supplier<Item> REDSTONE_SOUP = registerBrew("redstone_soup", MobEffects.ABSORPTION, 2400, 1);
	public static final Supplier<Item> REEK_OF_MISFORTUNE = registerItem("reek_of_misfortune");
	public static final Supplier<Item> REFINED_EVIL = registerItem("refined_evil");
	public static final Supplier<Item> ROWAN_BERRIES = registerFood("rowan_berries", 3, true, false);
	public static final Supplier<Item> ROWAN_LEAVES = registerBlock("rowan_leaves", EnchantedBlocks.ROWAN_LEAVES);
	public static final Supplier<Item> ROWAN_LOG = registerBlock("rowan_log", EnchantedBlocks.ROWAN_LOG);
	public static final Supplier<Item> ROWAN_PLANKS = registerBlock("rowan_planks", EnchantedBlocks.ROWAN_PLANKS);
	public static final Supplier<Item> ROWAN_SAPLING = registerBlock("rowan_sapling", EnchantedBlocks.ROWAN_SAPLING);
	public static final Supplier<Item> ROWAN_SLAB = registerBlock("rowan_slab", EnchantedBlocks.ROWAN_SLAB);
	public static final Supplier<Item> ROWAN_STAIRS = registerBlock("rowan_stairs", EnchantedBlocks.ROWAN_STAIRS);
	public static final Supplier<Item> SNOWBELL_SEEDS = registerBlockNamed("snowbell_seeds", EnchantedBlocks.SNOWBELL);
	public static final Supplier<Item> SOUL_OF_THE_WORLD = registerBrew("soul_of_the_world", MobEffects.POISON, 1200, 1);
	public static final Supplier<Item> SPANISH_MOSS = registerBlock("spanish_moss", EnchantedBlocks.SPANISH_MOSS);
	public static final Supplier<Item> SPINNING_WHEEL = register("spinning_wheel", () -> new SpinningWheelBlockItem(defaultProperties()));
	public static final Supplier<Item> SPIRIT_OF_OTHERWHERE = registerBrew("spirit_of_otherwhere", MobEffects.POISON, 1200, 1);
	public static final Supplier<Item> TAGLOCK = register("taglock",  () -> new TaglockItem(defaultProperties()));
	public static final Supplier<Item> TAGLOCK_FILLED =  register("taglock_filled",  () -> new TaglockFilledItem(new Properties()));
	public static final Supplier<Item> TEAR_OF_THE_GODDESS = registerItem("tear_of_the_goddess");
	public static final Supplier<Item> TONGUE_OF_DOG = registerItem("tongue_of_dog");

	public static final Supplier<Item> TOOL_POPPET = register("tool_poppet", () -> new ItemProtectionPoppetItem(0.3F, 1, 0.9F, PoppetColour.EQUIPMENT));
	public static final Supplier<Item> TOOL_POPPET_INFUSED = register("tool_poppet_infused", () -> new ItemProtectionPoppetItem(0.0F, 1, 0.0F, PoppetColour.EQUIPMENT));
	public static final Supplier<Item> TOOL_POPPET_STURDY = register("tool_poppet_sturdy", () -> new ItemProtectionPoppetItem(0.0F, 2, 0.9F, PoppetColour.EQUIPMENT));
	public static final Supplier<Item> VOID_POPPET = register("void_poppet", () -> new VoidPoppetItem(0.3F, 1, PoppetColour.VOID, source -> source.is(DamageTypes.FELL_OUT_OF_WORLD)));
	public static final Supplier<Item> VOID_POPPET_INFUSED = register("void_poppet_infused", () -> new VoidPoppetEffectItem( 0.0F, 1, PoppetColour.VOID, () -> new MobEffectInstance(EnchantedEffects.FALL_RESISTANCE.get(), 150), source -> source.is(DamageTypes.FELL_OUT_OF_WORLD)));
	public static final Supplier<Item> VOID_POPPET_STURDY = register("void_poppet_sturdy", () -> new VoidPoppetItem( 0.0F, 2, PoppetColour.VOID, source -> source.is(DamageTypes.FELL_OUT_OF_WORLD)));
	public static final Supplier<Item> WATER_POPPET = register("water_poppet", () -> new DeathPoppetEffectItem(0.3F, 1, PoppetColour.WATER, () -> new MobEffectInstance(EnchantedEffects.DROWN_RESISTANCE.get(), 100), source -> source.is(DamageTypeTags.IS_DROWNING)));
	public static final Supplier<Item> WATER_POPPET_INFUSED = register("water_poppet_infused", () -> new DeathPoppetEffectItem( 0.0F, 1, PoppetColour.WATER, () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 200), source -> source.is(DamageTypeTags.IS_DROWNING)));
	public static final Supplier<Item> WATER_POPPET_STURDY = register("water_poppet_sturdy", () -> new DeathPoppetEffectItem( 0.0F, 2, PoppetColour.WATER, () -> new MobEffectInstance(EnchantedEffects.DROWN_RESISTANCE.get(), 100), source -> source.is(DamageTypeTags.IS_DROWNING)));
	public static final Supplier<Item> WAYSTONE = registerItem("waystone");
	public static final Supplier<Item> WHIFF_OF_MAGIC = registerItem("whiff_of_magic");
	public static final Supplier<Item> WICKER_BUNDLE = registerBlock("wicker_bundle", EnchantedBlocks.WICKER_BUNDLE);
	public static final Supplier<Item> WITCH_CAULDRON = registerBlock("witch_cauldron", EnchantedBlocks.WITCH_CAULDRON);
	public static final Supplier<Item> WITCH_OVEN = registerBlock("witch_oven", EnchantedBlocks.WITCH_OVEN);
	public static final Supplier<Item> WOLFSBANE_FLOWER = registerItem("wolfsbane_flower");
	public static final Supplier<Item> WOLFSBANE_SEEDS = registerBlockNamed("wolfsbane_seeds", EnchantedBlocks.WOLFSBANE);
	public static final Supplier<Item> WOOD_ASH = registerItem("wood_ash");
	public static final Supplier<Item> WOOL_OF_BAT = registerItem("wool_of_bat");


//	public static final Supplier<Item> MANDRAKE_SPAWN_EGG = registerSpawnEgg("mandrake_spawn_egg", EnchantedEntityTypes.MANDRAKE::get, 0x634C39, 0x291A0E);
//	public static final Supplier<Item> ENT_SPAWN_EGG = registerSpawnEgg("ent_spawn_egg", EnchantedEntityTypes.ENT::get, 0x2A5422, 0x183812);



	private static <T extends Item> Supplier<T> register(String name, Supplier<T> itemSupplier) {
		return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.ITEM, name, itemSupplier);
	}

	private static Properties defaultProperties() {
		return new Properties();
	}

	private static Supplier<Item> registerItem(String name) {
		return register(name, () -> new Item(defaultProperties()));
	}

	private static Supplier<Item> registerNoTab(String name) {
		return register(name, () -> new Item(new Properties()));
	}

	private static Supplier<Item> registerItem(String name, Properties properties) {
		return register(name, () -> new Item(properties));
	}

	private static Supplier<Item> registerBlock(String name, Supplier<? extends Block> block) {
		return register(name, () -> new BlockItem(block.get(), defaultProperties()));
	}

	private static Supplier<Item> registerBlockProperties(String name, Supplier<? extends Block> block, Properties properties) {
		return register(name, () -> new BlockItem(block.get(), properties));
	}

	private static Supplier<Item> registerBlockNamed(String name, Supplier<? extends Block> block) {
		return register(name, () -> new ItemNameBlockItem(block.get(), defaultProperties()));
	}

	private static Supplier<Item> registerFood(String name, int nutrition, boolean fast, boolean meat) {
		FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition);
		if(fast)
			builder.fast();
		if(meat)
			builder.meat();
		return register(name, () -> new Item(defaultProperties().food(builder.build())));
	}

	private static Supplier<Item> registerFood(String name, int nutrition, MobEffect effect, int duration, int amplification, float chance) {
		return register(name, () -> new Item(defaultProperties().food(new FoodProperties.Builder().nutrition(nutrition).effect(new MobEffectInstance(effect, duration, amplification), chance).build())));
	}

	private static Supplier<Item> registerChalk(String name, Supplier<? extends Block> block, int durability) {
		return register(name, () -> new ChalkItem(block.get(), defaultProperties().stacksTo(1).durability(durability)));
	}

	private static Supplier<Item> registerSword(String name, Tier tier, int attackDamageModifier, float attackSpeedModifier) {
		return register(name, () -> new SwordItem(tier, attackDamageModifier, attackSpeedModifier, defaultProperties()));
	}

	private static Supplier<NonAnimatedArmorItem> registerNonAnimatedArmor(String name, ArmorMaterials material, ArmorItem.Type type, Properties properties) {
		return CommonServices.COMMON_REGISTRY.registerNonAnimatedArmorItem(name, material, type, name, properties);
	}

	private static Supplier<Item> registerBrew(String name, MobEffect effect, int duration, int amplifier) {
		return register(name, () -> new SimpleEffectBrewItem(effect, duration, amplifier, defaultProperties()));
	}

	public static boolean isToolPoppet(Item item) {
		return item == TOOL_POPPET.get() || item == TOOL_POPPET_INFUSED.get() || item == TOOL_POPPET_STURDY.get();
	}

	public static  boolean isArmourPoppet(Item item) {
		return item == ARMOUR_POPPET.get() || item == ARMOUR_POPPET_INFUSED.get() || item == ARMOUR_POPPET_STURDY.get();
	}

	public static void registerCompostables() {
		ComposterBlock.COMPOSTABLES.put(ARTICHOKE_SEEDS.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(ARTICHOKE.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(SNOWBELL_SEEDS.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(BELLADONNA_SEEDS.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(BELLADONNA_FLOWER.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(MANDRAKE_SEEDS.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(MANDRAKE_ROOT.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(WOLFSBANE_SEEDS.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(WOLFSBANE_FLOWER.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(GARLIC.get(), 0.45F);
	}

	public static void load() {} // Method which exists purely to load the class.

}

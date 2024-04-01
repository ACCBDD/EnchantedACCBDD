package com.favouriteless.enchanted.datagen.providers;

import com.favouriteless.enchanted.common.init.EnchantedTags;
import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import com.favouriteless.enchanted.datagen.builders.recipe.ByproductRecipeBuilder;
import com.favouriteless.enchanted.datagen.builders.recipe.DistillingRecipeBuilder;
import com.favouriteless.enchanted.datagen.builders.recipe.SpinningRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

	public RecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
		buildShapedRecipes(consumer);
		buildShapelessRecipes(consumer);
		buildSmeltingRecipes(consumer);
		buildByproductRecipes(consumer);
		buildSpinningRecipes(consumer);
		buildDistillingRecipes(consumer);
	}

	protected void buildShapedRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(EnchantedItems.ALTAR.get())
				.pattern("bwe")
				.pattern("srs")
				.pattern("srs")
				.define('s', Items.STONE_BRICKS).define('r', EnchantedItems.ROWAN_LOG.get())
				.define('b', EnchantedItems.BREATH_OF_THE_GODDESS.get())
				.define('e', EnchantedItems.EXHALE_OF_THE_HORNED_ONE.get())
				.define('w', StrictNBTIngredient.of(Items.POTION.getDefaultInstance()))
				.unlockedBy("has_rowan_log", has(EnchantedItems.ROWAN_LOG.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.ARTHANA.get())
				.pattern(" i ")
				.pattern("nen")
				.pattern(" s ")
				.define('i', Items.GOLD_INGOT).define('n', Items.GOLD_NUGGET)
				.define('e', Items.EMERALD).define('s', Items.STICK)
				.unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.ATTUNED_STONE.get())
				.pattern("w")
				.pattern("d")
				.pattern("l")
				.define('w', EnchantedItems.WHIFF_OF_MAGIC.get()).define('d', Items.DIAMOND)
				.define('l', Items.LAVA_BUCKET)
				.unlockedBy("has_whiff_of_magic", has(EnchantedItems.WHIFF_OF_MAGIC.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.BROOM.get())
				.pattern(" s ")
				.pattern(" s ")
				.pattern("hhh")
				.define('s', Items.STICK).define('h', EnchantedItems.HAWTHORN_SAPLING.get())
				.unlockedBy("has_hawthorn_sapling", has(EnchantedItems.HAWTHORN_SAPLING.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.CANDELABRA.get())
				.pattern("ccc")
				.pattern("iai")
				.pattern(" i ")
				.define('c', Items.CANDLE).define('i', Items.IRON_INGOT)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.CHALICE.get())
				.pattern("nan")
				.pattern("ngn")
				.pattern(" g ")
				.define('n', Items.GOLD_NUGGET).define('g', Items.GOLD_INGOT)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.CHALK_WHITE.get(), 2)
				.pattern("ata")
				.pattern("aga")
				.pattern("aga")
				.define('g', EnchantedItems.GYPSUM.get())
				.define('t', EnchantedItems.TEAR_OF_THE_GODDESS.get())
				.define('a', EnchantedItems.WOOD_ASH.get())
				.unlockedBy("has_tear_of_the_Goddess", has(EnchantedItems.TEAR_OF_THE_GODDESS.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.CIRCLE_TALISMAN.get())
				.pattern("ngn").pattern("gag")
				.pattern("ngn").define('n', Items.GOLD_NUGGET)
				.define('g', Items.GOLD_INGOT).define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.CLAY_JAR_SOFT.get(), 4)
				.pattern(" c ")
				.pattern("ccc")
				.define('c', Items.CLAY)
				.unlockedBy("has_clay", has(Items.CLAY)).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.DISTILLERY.get())
				.pattern("cic")
				.pattern("iii")
				.pattern("gag")
				.define('i', Items.IRON_INGOT).define('g', Items.GOLD_NUGGET)
				.define('c', EnchantedItems.CLAY_JAR.get())
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.EARMUFFS.get())
				.pattern(" i ")
				.pattern("whw")
				.define('i', Items.IRON_INGOT).define('w', ItemTags.WOOL)
				.define('h', Items.LEATHER_HELMET)
				.unlockedBy("has_wool", has(ItemTags.WOOL))
				.unlockedBy("has_leather_helmet", has(Items.LEATHER_HELMET)).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.FUME_FILTER.get())
				.pattern("ggg")
				.pattern("iai")
				.pattern("ggg")
				.define('g', Items.GLASS).define('i', Items.IRON_INGOT)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.FUME_FUNNEL.get())
				.pattern("blb")
				.pattern("bgb")
				.pattern("ifi")
				.define('g', Items.GLOWSTONE).define('i', Items.IRON_BLOCK)
				.define('b', Items.BUCKET).define('l', Items.LAVA_BUCKET)
				.define('f', Items.IRON_BARS)
				.unlockedBy("has_glowstone", has(Items.GLOWSTONE)).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.KETTLE.get())
				.pattern("wsw")
				.pattern("sas")
				.pattern(" c ").define('w', Items.STICK)
				.define('s', Items.STRING).define('c', Items.CAULDRON
				).define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.POPPET.get())
				.pattern("wmw")
				.pattern("bms")
				.pattern("waw")
				.define('w', ItemTags.WOOL).define('s', Items.STRING)
				.define('m', EnchantedItems.SPANISH_MOSS.get())
				.define('b', EnchantedItems.BONE_NEEDLE.get())
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.POPPET_SHELF.get())
				.pattern("apa")
				.pattern("pwp")
				.pattern("apa")
				.define('w', Items.GREEN_WOOL).define('p', Items.DARK_OAK_PLANKS)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.SPINNING_WHEEL.get())
				.pattern("wrr")
				.pattern("srr")
				.pattern("pap")
				.define('w', ItemTags.WOOL).define('s', Items.STICK)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.define('p', EnchantedItems.ALDER_PLANKS.get())
				.define('r', EnchantedItems.ROWAN_STAIRS.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.WICKER_BUNDLE.get())
				.pattern("sss")
				.pattern("sss")
				.pattern("sss")
				.define('s', ItemTags.SAPLINGS)
				.unlockedBy("has_sapling", has(ItemTags.SAPLINGS)).save(consumer);
		ShapedRecipeBuilder.shaped(EnchantedItems.WITCH_OVEN.get())
				.pattern(" b ").pattern("iii")
				.pattern("ibi")
				.define('i', Items.IRON_INGOT).define('b', Items.IRON_BARS)
				.unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);

		slab(consumer, EnchantedItems.ROWAN_SLAB.get(), EnchantedItems.ROWAN_PLANKS.get());
		slab(consumer, EnchantedItems.ALDER_SLAB.get(), EnchantedItems.ALDER_PLANKS.get());
		slab(consumer, EnchantedItems.HAWTHORN_SLAB.get(), EnchantedItems.HAWTHORN_PLANKS.get());

		stairs(consumer, EnchantedItems.ROWAN_STAIRS.get(), EnchantedItems.ROWAN_PLANKS.get());
		stairs(consumer, EnchantedItems.ALDER_STAIRS.get(), EnchantedItems.ALDER_PLANKS.get());
		stairs(consumer, EnchantedItems.HAWTHORN_STAIRS.get(), EnchantedItems.HAWTHORN_PLANKS.get());
	}

	protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(EnchantedItems.ANOINTING_PASTE.get())
				.requires(EnchantedItems.ARTICHOKE_SEEDS.get()).requires(EnchantedItems.MANDRAKE_SEEDS.get())
				.requires(EnchantedItems.BELLADONNA_SEEDS.get()).requires(EnchantedItems.SNOWBELL_SEEDS.get())
				.unlockedBy("has_seeds", has(EnchantedItems.BELLADONNA_SEEDS.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(EnchantedItems.BONE_NEEDLE.get(), 8)
				.requires(Items.FLINT).requires(Items.BONE)
				.unlockedBy("has_bone", has(Items.BONE)).save(consumer);
		ShapelessRecipeBuilder.shapeless(EnchantedItems.FUME_FUNNEL_FILTERED.get())
				.requires(EnchantedItems.FUME_FUNNEL.get()).requires(EnchantedItems.FUME_FILTER.get())
				.unlockedBy("has_fume_funnel", has(EnchantedItems.FUME_FUNNEL.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(EnchantedItems.PURIFIED_MILK.get()).requires(Items.MILK_BUCKET)
				.requires(EnchantedItems.ODOUR_OF_PURITY.get()).requires(EnchantedItems.CLAY_JAR.get(), 3)
				.unlockedBy("has_odour_of_purity", has(EnchantedItems.ODOUR_OF_PURITY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(EnchantedItems.QUICKLIME.get())
				.requires(EnchantedItems.WOOD_ASH.get())
				.unlockedBy("has_wood_ash", has(EnchantedItems.WOOD_ASH.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(EnchantedItems.TAGLOCK.get())
				.requires(Items.GLASS_BOTTLE).requires(EnchantedItems.BONE_NEEDLE.get())
				.unlockedBy("has_bone_needle", has(EnchantedItems.BONE_NEEDLE.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(EnchantedItems.WAYSTONE.get())
				.requires(Items.FLINT).requires(EnchantedItems.BONE_NEEDLE.get())
				.unlockedBy("has_bone_needle", has(EnchantedItems.BONE_NEEDLE.get())).save(consumer);

		planksFromLog(consumer, EnchantedItems.ROWAN_PLANKS.get(), EnchantedItems.ROWAN_LOG.get());
		planksFromLog(consumer, EnchantedItems.ALDER_PLANKS.get(), EnchantedItems.ALDER_LOG.get());
		planksFromLog(consumer, EnchantedItems.HAWTHORN_PLANKS.get(), EnchantedItems.HAWTHORN_LOG.get());
	}

	protected void buildSmeltingRecipes(Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(
						Ingredient.of(EnchantedItems.CLAY_JAR_SOFT.get()), EnchantedItems.CLAY_JAR.get(),
						0.1F, 200)
				.unlockedBy("has_clay_jar_soft", has(EnchantedItems.CLAY_JAR_SOFT.get())).save(consumer);
		SimpleCookingRecipeBuilder.smelting(
						Ingredient.of(ItemTags.SAPLINGS), EnchantedItems.WOOD_ASH.get(),
						0.1F, 150)
				.unlockedBy("has_sapling", has(ItemTags.SAPLINGS)).save(consumer);
	}

	protected void buildByproductRecipes(Consumer<FinishedRecipe> consumer) {
		byproduct(consumer, EnchantedItems.BREATH_OF_THE_GODDESS.get(), Items.BIRCH_SAPLING);
		byproduct(consumer, EnchantedItems.EXHALE_OF_THE_HORNED_ONE.get(), Items.OAK_SAPLING);
		byproduct(consumer, EnchantedItems.FOUL_FUME.get(), Items.JUNGLE_SAPLING);
		byproduct(consumer, EnchantedItems.FOUL_FUME.get(), ItemTags.LOGS_THAT_BURN);
		byproduct(consumer, EnchantedItems.FOUL_FUME.get(), EnchantedTags.Items.RAW_FOODS);
		byproduct(consumer, EnchantedItems.HINT_OF_REBIRTH.get(), Items.SPRUCE_SAPLING);
		byproduct(consumer, EnchantedItems.ODOUR_OF_PURITY.get(), EnchantedItems.HAWTHORN_SAPLING.get());
		byproduct(consumer, EnchantedItems.REEK_OF_MISFORTUNE.get(), EnchantedItems.ALDER_SAPLING.get());
		byproduct(consumer, EnchantedItems.WHIFF_OF_MAGIC.get(), EnchantedItems.ROWAN_SAPLING.get());
	}

	protected void buildSpinningRecipes(Consumer<FinishedRecipe> consumer) {
		// Basic poppets
		spinning(consumer, EnchantedItems.POPPET_INFUSED.get(),
				EnchantedItems.POPPET.get(), EnchantedItems.ATTUNED_STONE.get(), Items.ENDER_PEARL);
		spinning(consumer, EnchantedItems.POPPET_STURDY.get(),
				EnchantedItems.POPPET.get(), EnchantedItems.HINT_OF_REBIRTH.get(), Items.SHIELD);
		// Effect poppets
		spinningSet(consumer, EnchantedItems.ARMOUR_POPPET.get(), EnchantedItems.ARMOUR_POPPET_INFUSED.get(), EnchantedItems.ARMOUR_POPPET_STURDY.get(),
				EnchantedItems.HINT_OF_REBIRTH.get(), Items.DIAMOND_BOOTS);
		spinningSet(consumer, EnchantedItems.EARTH_POPPET.get(), EnchantedItems.EARTH_POPPET_INFUSED.get(), EnchantedItems.EARTH_POPPET_STURDY.get(),
				stack(EnchantedItems.MANDRAKE_ROOT.get(), 1), PotionUtils.setPotion(stack(Items.POTION, 1), Potions.LONG_SLOW_FALLING));
		spinningSet(consumer, EnchantedItems.FIRE_POPPET.get(), EnchantedItems.FIRE_POPPET_INFUSED.get(), EnchantedItems.FIRE_POPPET_STURDY.get(),
				stack(EnchantedItems.EMBER_MOSS.get(), 1), PotionUtils.setPotion(stack(Items.POTION, 1), Potions.LONG_FIRE_RESISTANCE));
		spinningSet(consumer, EnchantedItems.HUNGER_POPPET.get(), EnchantedItems.HUNGER_POPPET_INFUSED.get(), EnchantedItems.HUNGER_POPPET_STURDY.get(),
				EnchantedItems.ROWAN_BERRIES.get(), Items.ROTTEN_FLESH);
		spinningSet(consumer, EnchantedItems.TOOL_POPPET.get(), EnchantedItems.TOOL_POPPET_INFUSED.get(), EnchantedItems.TOOL_POPPET_STURDY.get(),
				EnchantedItems.HINT_OF_REBIRTH.get(), Items.DIAMOND_SWORD);
		spinningSet(consumer, EnchantedItems.VOID_POPPET.get(), EnchantedItems.VOID_POPPET_INFUSED.get(), EnchantedItems.VOID_POPPET_STURDY.get(),
				EnchantedItems.CHALK_PURPLE.get(), EnchantedItems.ENDER_DEW.get());
		spinningSet(consumer, EnchantedItems.WATER_POPPET.get(), EnchantedItems.WATER_POPPET_INFUSED.get(), EnchantedItems.WATER_POPPET_STURDY.get(),
				stack(EnchantedItems.ARTICHOKE.get(), 1), PotionUtils.setPotion(stack(Items.POTION, 1), Potions.LONG_WATER_BREATHING));
	}

	protected void buildDistillingRecipes(Consumer<FinishedRecipe> consumer) {
		DistillingRecipeBuilder.create(Items.BLAZE_POWDER, Items.GUNPOWDER)
				.results(stack(Items.GLOWSTONE_DUST, 2))
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 3), stack(EnchantedItems.BREATH_OF_THE_GODDESS.get()), stack(Items.LAPIS_LAZULI))
				.results(EnchantedItems.TEAR_OF_THE_GODDESS.get(), EnchantedItems.WHIFF_OF_MAGIC.get(), Items.SLIME_BALL, EnchantedItems.FOUL_FUME.get())
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 2), stack(EnchantedItems.DEMON_HEART.get()), stack(EnchantedItems.DIAMOND_VAPOUR.get()))
				.results(stack(EnchantedItems.DEMONIC_BLOOD.get(), 2))
				.results(EnchantedItems.REFINED_EVIL.get())
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 3), stack(Items.DIAMOND), stack(EnchantedItems.OIL_OF_VITRIOL.get()))
				.results(stack(EnchantedItems.DIAMOND_VAPOUR.get(), 2))
				.results(EnchantedItems.ODOUR_OF_PURITY.get())
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(EnchantedItems.CLAY_JAR.get(), EnchantedItems.DIAMOND_VAPOUR.get(), Items.BLAZE_ROD)
				.results(EnchantedItems.DEMONIC_BLOOD.get())
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 3), stack(EnchantedItems.DIAMOND_VAPOUR.get()), stack(Items.GHAST_TEAR))
				.results(EnchantedItems.ODOUR_OF_PURITY.get(), EnchantedItems.REEK_OF_MISFORTUNE.get(), EnchantedItems.FOUL_FUME.get(), EnchantedItems.REFINED_EVIL.get())
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 6), stack(Items.ENDER_PEARL))
				.results(stack(EnchantedItems.ENDER_DEW.get(), 5))
				.results(EnchantedItems.WHIFF_OF_MAGIC.get())
				.cookTime(300).save(consumer);
		DistillingRecipeBuilder.create(EnchantedItems.CLAY_JAR.get(), EnchantedItems.FOUL_FUME.get(), EnchantedItems.QUICKLIME.get())
				.results(EnchantedItems.GYPSUM.get(), EnchantedItems.OIL_OF_VITRIOL.get(), Items.SLIME_BALL)
				.cookTime(300).save(consumer);
	}

	protected static void spinning(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike first, Item second, Item third) {
		SpinningRecipeBuilder.create(result, 500, first, second, third).save(consumer);
	}

	protected static void spinningSet(Consumer<FinishedRecipe> consumer, ItemLike normal, ItemLike infused, ItemLike sturdy, Item second, Item third) {
		SpinningRecipeBuilder.create(normal, 500, EnchantedItems.POPPET.get(), second, third).save(consumer);
		SpinningRecipeBuilder.create(infused, 1000, EnchantedItems.POPPET_INFUSED.get(), second, third).save(consumer);
		SpinningRecipeBuilder.create(sturdy, 1000, EnchantedItems.POPPET_STURDY.get(), second, third).save(consumer);
	}

	protected static void spinningSet(Consumer<FinishedRecipe> consumer, ItemLike normal, ItemLike infused, ItemLike sturdy, ItemStack second, ItemStack third) {
		SpinningRecipeBuilder.create(normal, 500, EnchantedItems.POPPET.get().getDefaultInstance(), second, third).save(consumer);
		SpinningRecipeBuilder.create(infused, 1000, EnchantedItems.POPPET_INFUSED.get().getDefaultInstance(), second, third).save(consumer);
		SpinningRecipeBuilder.create(sturdy, 1000, EnchantedItems.POPPET_STURDY.get().getDefaultInstance(), second, third).save(consumer);
	}

	protected static void byproduct(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike... items) {
		ByproductRecipeBuilder.create(result, items).save(consumer);
	}

	protected static void byproduct(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> tag) {
		ByproductRecipeBuilder.create(result, tag).save(consumer);
	}

	protected static void planksFromLog(Consumer<FinishedRecipe> consumer, ItemLike planks, ItemLike logs) {
		ShapelessRecipeBuilder.shapeless(planks, 4).requires(logs).group("planks").unlockedBy("has_log", has(logs)).save(consumer);
	}

	protected static void stairs(Consumer<FinishedRecipe> consumer, ItemLike stairs, ItemLike material) {
		stairBuilder(stairs, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(consumer);
	}

	private static ItemStack stack(ItemLike item, int count) {
		return new ItemStack(item, count);
	}

	private static ItemStack stack(ItemLike item) {
		return stack(item, 1);
	}

}

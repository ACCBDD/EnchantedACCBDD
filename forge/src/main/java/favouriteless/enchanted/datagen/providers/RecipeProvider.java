package favouriteless.enchanted.datagen.providers;

import favouriteless.enchanted.common.init.EnchantedTags;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.datagen.builders.recipe.ByproductRecipeBuilder;
import favouriteless.enchanted.datagen.builders.recipe.CauldronTypeRecipeBuilder;
import favouriteless.enchanted.datagen.builders.recipe.DistillingRecipeBuilder;
import favouriteless.enchanted.datagen.builders.recipe.SpinningRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

	public RecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		buildShapedRecipes(consumer);
		buildShapelessRecipes(consumer);
		buildSmeltingRecipes(consumer);
		buildByproductRecipes(consumer);
		buildSpinningRecipes(consumer);
		buildDistillingRecipes(consumer);
		buildCauldronRecipes(consumer);
		buildKettleRecipes(consumer);
	}

	protected void buildShapedRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnchantedItems.ALTAR.get())
				.pattern("bwe")
				.pattern("srs")
				.pattern("srs")
				.define('s', Items.STONE_BRICKS).define('r', EnchantedItems.ROWAN_LOG.get())
				.define('b', EnchantedItems.BREATH_OF_THE_GODDESS.get())
				.define('e', EnchantedItems.EXHALE_OF_THE_HORNED_ONE.get())
				.define('w', StrictNBTIngredient.of(Items.POTION.getDefaultInstance()))
				.unlockedBy(getHasName(EnchantedItems.ROWAN_LOG.get()), has(EnchantedItems.ROWAN_LOG.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EnchantedItems.ARTHANA.get())
				.pattern(" i ")
				.pattern("nen")
				.pattern(" s ")
				.define('i', Items.GOLD_INGOT).define('n', Items.GOLD_NUGGET)
				.define('e', Items.EMERALD).define('s', Items.STICK)
				.unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.ATTUNED_STONE.get())
				.pattern("w")
				.pattern("d")
				.pattern("l")
				.define('w', EnchantedItems.WHIFF_OF_MAGIC.get()).define('d', Items.DIAMOND)
				.define('l', Items.LAVA_BUCKET)
				.unlockedBy(getHasName(EnchantedItems.WHIFF_OF_MAGIC.get()), has(EnchantedItems.WHIFF_OF_MAGIC.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.BROOM.get())
				.pattern(" s ")
				.pattern(" s ")
				.pattern("hhh")
				.define('s', Items.STICK).define('h', EnchantedItems.HAWTHORN_SAPLING.get())
				.unlockedBy(getHasName(EnchantedItems.HAWTHORN_SAPLING.get()), has(EnchantedItems.HAWTHORN_SAPLING.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EnchantedItems.CANDELABRA.get())
				.pattern("ccc")
				.pattern("iai")
				.pattern(" i ")
				.define('c', Items.CANDLE).define('i', Items.IRON_INGOT)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy("has_attuned_stone", has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EnchantedItems.CHALICE.get())
				.pattern("nan")
				.pattern("ngn")
				.pattern(" g ")
				.define('n', Items.GOLD_NUGGET).define('g', Items.GOLD_INGOT)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EnchantedItems.RITUAL_CHALK.get(), 2)
				.pattern("ata")
				.pattern("aga")
				.pattern("aga")
				.define('g', EnchantedItems.GYPSUM.get())
				.define('t', EnchantedItems.TEAR_OF_THE_GODDESS.get())
				.define('a', EnchantedItems.WOOD_ASH.get())
				.unlockedBy(getHasName(EnchantedItems.TEAR_OF_THE_GODDESS.get()), has(EnchantedItems.TEAR_OF_THE_GODDESS.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EnchantedItems.CIRCLE_TALISMAN.get())
				.pattern("ngn").pattern("gag")
				.pattern("ngn").define('n', Items.GOLD_NUGGET)
				.define('g', Items.GOLD_INGOT).define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.SOFT_CLAY_JAR.get(), 4)
				.pattern(" c ")
				.pattern("ccc")
				.define('c', Items.CLAY_BALL)
				.unlockedBy(getHasName(Items.CLAY_BALL), has(Items.CLAY_BALL)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.DISTILLERY.get())
				.pattern("cic")
				.pattern("iii")
				.pattern("gag")
				.define('i', Items.IRON_INGOT).define('g', Items.GOLD_NUGGET)
				.define('c', EnchantedItems.CLAY_JAR.get())
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EnchantedItems.EARMUFFS.get())
				.pattern(" i ")
				.pattern("whw")
				.define('i', Items.IRON_INGOT).define('w', ItemTags.WOOL)
				.define('h', Items.LEATHER_HELMET)
				.unlockedBy("has_wool", has(ItemTags.WOOL))
				.unlockedBy(getHasName(Items.LEATHER_HELMET), has(Items.LEATHER_HELMET)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.FUME_FILTER.get())
				.pattern("ggg")
				.pattern("iai")
				.pattern("ggg")
				.define('g', Items.GLASS).define('i', Items.IRON_INGOT)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.FUME_FUNNEL.get())
				.pattern("blb")
				.pattern("bgb")
				.pattern("ifi")
				.define('g', Items.GLOWSTONE).define('i', Items.IRON_BLOCK)
				.define('b', Items.BUCKET).define('l', Items.LAVA_BUCKET)
				.define('f', Items.IRON_BARS)
				.unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.KETTLE.get())
				.pattern("wsw")
				.pattern("sas")
				.pattern(" c ").define('w', Items.STICK)
				.define('s', Items.STRING).define('c', Items.CAULDRON
				).define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.POPPET.get())
				.pattern("wmw")
				.pattern("bms")
				.pattern("waw")
				.define('w', ItemTags.WOOL).define('s', Items.STRING)
				.define('m', EnchantedItems.SPANISH_MOSS.get())
				.define('b', EnchantedItems.BONE_NEEDLE.get())
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.POPPET_SHELF.get())
				.pattern("apa")
				.pattern("pwp")
				.pattern("apa")
				.define('w', Items.GREEN_WOOL).define('p', Items.DARK_OAK_PLANKS)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.SPINNING_WHEEL.get())
				.pattern("wrr")
				.pattern("srr")
				.pattern("pap")
				.define('w', ItemTags.WOOL).define('s', Items.STICK)
				.define('a', EnchantedItems.ATTUNED_STONE.get())
				.define('p', EnchantedItems.ALDER_PLANKS.get())
				.define('r', EnchantedItems.ROWAN_STAIRS.get())
				.unlockedBy(getHasName(EnchantedItems.ATTUNED_STONE.get()), has(EnchantedItems.ATTUNED_STONE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnchantedItems.WICKER_BUNDLE.get())
				.pattern("sss")
				.pattern("sss")
				.pattern("sss")
				.define('s', ItemTags.SAPLINGS)
				.unlockedBy("has_sapling", has(ItemTags.SAPLINGS)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EnchantedItems.WITCH_OVEN.get())
				.pattern(" b ").pattern("iii")
				.pattern("ibi")
				.define('i', Items.IRON_INGOT).define('b', Items.IRON_BARS)
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(consumer);

		slab(consumer, RecipeCategory.BUILDING_BLOCKS, EnchantedItems.ROWAN_SLAB.get(), EnchantedItems.ROWAN_PLANKS.get());
		slab(consumer, RecipeCategory.BUILDING_BLOCKS, EnchantedItems.ALDER_SLAB.get(), EnchantedItems.ALDER_PLANKS.get());
		slab(consumer, RecipeCategory.BUILDING_BLOCKS, EnchantedItems.HAWTHORN_SLAB.get(), EnchantedItems.HAWTHORN_PLANKS.get());

		stairs(consumer, EnchantedItems.ROWAN_STAIRS.get(), EnchantedItems.ROWAN_PLANKS.get());
		stairs(consumer, EnchantedItems.ALDER_STAIRS.get(), EnchantedItems.ALDER_PLANKS.get());
		stairs(consumer, EnchantedItems.HAWTHORN_STAIRS.get(), EnchantedItems.HAWTHORN_PLANKS.get());

		pressurePlate(consumer, EnchantedItems.ALDER_PRESSURE_PLATE.get(), EnchantedItems.ALDER_PLANKS.get());
		pressurePlate(consumer, EnchantedItems.HAWTHORN_PRESSURE_PLATE.get(), EnchantedItems.HAWTHORN_PLANKS.get());
		pressurePlate(consumer, EnchantedItems.ROWAN_PRESSURE_PLATE.get(), EnchantedItems.ROWAN_PLANKS.get());

		fenceBuilder(EnchantedItems.ALDER_FENCE.get(), Ingredient.of(EnchantedItems.ALDER_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.ALDER_PLANKS.get()), has(EnchantedItems.ALDER_PLANKS.get())).save(consumer);
		fenceBuilder(EnchantedItems.HAWTHORN_FENCE.get(), Ingredient.of(EnchantedItems.HAWTHORN_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.HAWTHORN_PLANKS.get()), has(EnchantedItems.HAWTHORN_PLANKS.get())).save(consumer);
		fenceBuilder(EnchantedItems.ROWAN_FENCE.get(), Ingredient.of(EnchantedItems.ROWAN_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.ROWAN_PLANKS.get()), has(EnchantedItems.ROWAN_PLANKS.get())).save(consumer);

		fenceGateBuilder(EnchantedItems.ALDER_FENCE_GATE.get(), Ingredient.of(EnchantedItems.ALDER_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.ALDER_PLANKS.get()), has(EnchantedItems.ALDER_PLANKS.get())).save(consumer);
		fenceGateBuilder(EnchantedItems.HAWTHORN_FENCE_GATE.get(), Ingredient.of(EnchantedItems.HAWTHORN_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.HAWTHORN_PLANKS.get()), has(EnchantedItems.HAWTHORN_PLANKS.get())).save(consumer);
		fenceGateBuilder(EnchantedItems.ROWAN_FENCE_GATE.get(), Ingredient.of(EnchantedItems.ROWAN_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.ROWAN_PLANKS.get()), has(EnchantedItems.ROWAN_PLANKS.get())).save(consumer);
	}

	protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.ANOINTING_PASTE.get())
				.requires(EnchantedItems.WATER_ARTICHOKE_SEEDS.get()).requires(EnchantedItems.MANDRAKE_SEEDS.get())
				.requires(EnchantedItems.BELLADONNA_SEEDS.get()).requires(EnchantedItems.SNOWBELL_SEEDS.get())
				.unlockedBy(getHasName(EnchantedItems.BELLADONNA_SEEDS.get()), has(EnchantedItems.BELLADONNA_SEEDS.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.BONE_NEEDLE.get(), 8)
				.requires(Items.FLINT).requires(Items.BONE)
				.unlockedBy(getHasName(Items.BONE), has(Items.BONE)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.FUME_FUNNEL_FILTERED.get())
				.requires(EnchantedItems.FUME_FUNNEL.get()).requires(EnchantedItems.FUME_FILTER.get())
				.unlockedBy(getHasName(EnchantedItems.FUME_FUNNEL.get()), has(EnchantedItems.FUME_FUNNEL.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.PURIFIED_MILK.get()).requires(Items.MILK_BUCKET)
				.requires(EnchantedItems.ODOUR_OF_PURITY.get()).requires(EnchantedItems.CLAY_JAR.get(), 3)
				.unlockedBy(getHasName(EnchantedItems.ODOUR_OF_PURITY.get()), has(EnchantedItems.ODOUR_OF_PURITY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.QUICKLIME.get())
				.requires(EnchantedItems.WOOD_ASH.get())
				.unlockedBy(getHasName(EnchantedItems.WOOD_ASH.get()), has(EnchantedItems.WOOD_ASH.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.TAGLOCK.get())
				.requires(Items.GLASS_BOTTLE).requires(EnchantedItems.BONE_NEEDLE.get())
				.unlockedBy(getHasName(EnchantedItems.BONE_NEEDLE.get()), has(EnchantedItems.BONE_NEEDLE.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EnchantedItems.WAYSTONE.get())
				.requires(Items.FLINT).requires(EnchantedItems.BONE_NEEDLE.get())
				.unlockedBy(getHasName(EnchantedItems.BONE_NEEDLE.get()), has(EnchantedItems.BONE_NEEDLE.get())).save(consumer);


		buttonBuilder(EnchantedItems.ALDER_BUTTON.get(), Ingredient.of(EnchantedItems.ALDER_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.ALDER_PLANKS.get()), has(EnchantedItems.ALDER_PLANKS.get())).save(consumer);
		buttonBuilder(EnchantedItems.HAWTHORN_BUTTON.get(), Ingredient.of(EnchantedItems.HAWTHORN_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.HAWTHORN_PLANKS.get()), has(EnchantedItems.HAWTHORN_PLANKS.get())).save(consumer);
		buttonBuilder(EnchantedItems.ROWAN_BUTTON.get(), Ingredient.of(EnchantedItems.ROWAN_PLANKS.get()))
				.unlockedBy(getHasName(EnchantedItems.ROWAN_PLANKS.get()), has(EnchantedItems.ROWAN_PLANKS.get())).save(consumer);

		planksFromLog(consumer, EnchantedItems.ROWAN_PLANKS.get(), EnchantedItems.ROWAN_LOG.get());
		planksFromLog(consumer, EnchantedItems.ALDER_PLANKS.get(), EnchantedItems.ALDER_LOG.get());
		planksFromLog(consumer, EnchantedItems.HAWTHORN_PLANKS.get(), EnchantedItems.HAWTHORN_LOG.get());
	}

	protected void buildSmeltingRecipes(Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(
						Ingredient.of(EnchantedItems.SOFT_CLAY_JAR.get()), RecipeCategory.MISC, EnchantedItems.CLAY_JAR.get(),
						0.1F, 200)
				.unlockedBy(getHasName(EnchantedItems.SOFT_CLAY_JAR.get()), has(EnchantedItems.SOFT_CLAY_JAR.get())).save(consumer);
		SimpleCookingRecipeBuilder.smelting(
						Ingredient.of(ItemTags.SAPLINGS), RecipeCategory.MISC, EnchantedItems.WOOD_ASH.get(),
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
				EnchantedItems.OTHERWHERE_CHALK.get(), EnchantedItems.ENDER_DEW.get());
		spinningSet(consumer, EnchantedItems.WATER_POPPET.get(), EnchantedItems.WATER_POPPET_INFUSED.get(), EnchantedItems.WATER_POPPET_STURDY.get(),
				stack(EnchantedItems.WATER_ARTICHOKE.get(), 1), PotionUtils.setPotion(stack(Items.POTION, 1), Potions.LONG_WATER_BREATHING));
		spinningSet(consumer, EnchantedItems.MAGIC_POPPET.get(), EnchantedItems.MAGIC_POPPET_INFUSED.get(), EnchantedItems.MAGIC_POPPET_STURDY.get(),
				stack(EnchantedItems.ANOINTING_PASTE.get(), 1), PotionUtils.setPotion(stack(Items.POTION, 1), Potions.STRONG_HARMING));
		spinningSet(consumer, EnchantedItems.VOODOO_PROTECTION_POPPET.get(), EnchantedItems.VOODOO_PROTECTION_POPPET_INFUSED.get(), EnchantedItems.VOODOO_PROTECTION_POPPET_STURDY.get(),
				stack(EnchantedItems.BONE_NEEDLE.get(), 1), PotionUtils.setPotion(stack(Items.POTION, 1), Potions.STRONG_HEALING));

		// Special poppets
		spinning(consumer, EnchantedItems.VOODOO_POPPET.get(), EnchantedItems.POPPET_INFUSED.get(), EnchantedItems.BONE_NEEDLE.get(), EnchantedItems.BREW_OF_THE_GROTESQUE.get());
	}

	protected void buildDistillingRecipes(Consumer<FinishedRecipe> consumer) {
		DistillingRecipeBuilder.create(Items.BLAZE_POWDER, Items.GUNPOWDER)
				.results(stack(Items.GLOWSTONE_DUST, 2))
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 3), stack(EnchantedItems.BREATH_OF_THE_GODDESS.get()), stack(Items.LAPIS_LAZULI))
				.results(EnchantedItems.TEAR_OF_THE_GODDESS.get(), EnchantedItems.WHIFF_OF_MAGIC.get(), Items.SLIME_BALL, EnchantedItems.FOUL_FUME.get())
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 2), stack(EnchantedItems.DEMON_HEART.get()), stack(EnchantedItems.DIAMOND_VAPOUR.get()))
				.results(stack(EnchantedItems.DEMONIC_BLOOD.get(), 2))
				.results(EnchantedItems.REFINED_EVIL.get())
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 3), stack(Items.DIAMOND), stack(EnchantedItems.OIL_OF_VITRIOL.get()))
				.results(stack(EnchantedItems.DIAMOND_VAPOUR.get(), 2))
				.results(EnchantedItems.ODOUR_OF_PURITY.get())
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(EnchantedItems.CLAY_JAR.get(), EnchantedItems.DIAMOND_VAPOUR.get(), Items.BLAZE_ROD)
				.results(EnchantedItems.DEMONIC_BLOOD.get())
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 3), stack(EnchantedItems.DIAMOND_VAPOUR.get()), stack(Items.GHAST_TEAR))
				.results(EnchantedItems.ODOUR_OF_PURITY.get(), EnchantedItems.REEK_OF_MISFORTUNE.get(), EnchantedItems.FOUL_FUME.get(), EnchantedItems.REFINED_EVIL.get())
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(stack(EnchantedItems.CLAY_JAR.get(), 6), stack(Items.ENDER_PEARL))
				.results(stack(EnchantedItems.ENDER_DEW.get(), 5))
				.results(EnchantedItems.WHIFF_OF_MAGIC.get())
				.cookTime(300).power(750).save(consumer);
		DistillingRecipeBuilder.create(EnchantedItems.CLAY_JAR.get(), EnchantedItems.FOUL_FUME.get(), EnchantedItems.QUICKLIME.get())
				.results(EnchantedItems.GYPSUM.get(), EnchantedItems.OIL_OF_VITRIOL.get(), Items.SLIME_BALL)
				.cookTime(300).power(750).save(consumer);
	}

	protected void buildCauldronRecipes(Consumer<FinishedRecipe> consumer) {
		CauldronTypeRecipeBuilder.cauldron(stack(Items.COOKED_BEEF), 0)
				.inputs(Items.BEEF)
				.cookColor(24, 48, 22)
				.finalColor(28, 94, 22).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(EnchantedItems.GOLDEN_CHALK.get()), 3000)
				.inputs(EnchantedItems.MANDRAKE_ROOT.get(), Items.GOLD_NUGGET, EnchantedItems.RITUAL_CHALK.get())
				.cookColor(89, 64, 0)
				.finalColor(194, 155, 0).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(EnchantedItems.OTHERWHERE_CHALK.get()), 2000)
				.inputs(Items.NETHER_WART, EnchantedItems.TEAR_OF_THE_GODDESS.get(), Items.ENDER_PEARL,
						EnchantedItems.RITUAL_CHALK.get())
				.cookColor(49, 21, 74)
				.finalColor(73, 13, 130).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(EnchantedItems.NETHER_CHALK.get()), 2000)
				.inputs(Items.NETHER_WART, Items.BLAZE_POWDER, EnchantedItems.RITUAL_CHALK.get())
				.cookColor(84, 1, 26)
				.finalColor(156, 1, 47).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(Items.COOKED_CHICKEN), 0)
				.inputs(Items.CHICKEN)
				.cookColor(82, 40, 84)
				.finalColor(110, 22, 115).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(EnchantedItems.DROP_OF_LUCK.get()), 7000)
				.inputs(EnchantedItems.MANDRAKE_ROOT.get(), Items.NETHER_WART, EnchantedItems.TEAR_OF_THE_GODDESS.get(),
						EnchantedItems.REFINED_EVIL.get(), EnchantedItems.MUTANDIS_EXTREMIS.get())
				.cookColor(0, 69, 23)
				.finalColor(0, 117, 39).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(EnchantedItems.MUTANDIS.get(), 6), 0)
				.inputs(EnchantedItems.MANDRAKE_ROOT.get(), EnchantedItems.EXHALE_OF_THE_HORNED_ONE.get(), Items.EGG)
				.cookColor(26, 71, 35)
				.finalColor(62, 128, 78).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(EnchantedItems.MUTANDIS_EXTREMIS.get()), 7000)
				.inputs(EnchantedItems.MUTANDIS.get(), Items.NETHER_WART)
				.cookColor(84, 24, 24)
				.finalColor(128, 29, 29).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(Items.NETHER_WART), 0)
				.inputs(EnchantedItems.MANDRAKE_ROOT.get(), EnchantedItems.TEAR_OF_THE_GODDESS.get(),
						EnchantedItems.DIAMOND_VAPOUR.get(), Items.ENDER_PEARL, Items.WHEAT,
						EnchantedItems.MUTANDIS.get())
				.cookColor(92, 35, 0)
				.finalColor(153, 52, 0).save(consumer);
		CauldronTypeRecipeBuilder.cauldron(stack(Items.COOKED_PORKCHOP), 0)
				.inputs(Items.PORKCHOP)
				.cookColor(61, 69, 24)
				.finalColor(98, 110, 19).save(consumer);
	}

	protected void buildKettleRecipes(Consumer<FinishedRecipe> consumer) {
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.BREW_OF_LOVE.get(), 3), 0)
				.inputs(Items.POPPY, Items.GOLDEN_CARROT, Items.LILY_PAD, Items.COCOA_BEANS,
						EnchantedItems.WHIFF_OF_MAGIC.get(), EnchantedItems.WATER_ARTICHOKE.get())
				.cookColor(176, 70, 165)
				.finalColor(247, 143, 235).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.BREW_OF_SPROUTING.get(), 3), 0)
				.inputs(EnchantedItems.ROWAN_SAPLING.get(), EnchantedItems.TONGUE_OF_DOG.get(),
						EnchantedItems.ALDER_SAPLING.get(), EnchantedItems.MANDRAKE_ROOT.get(),
						EnchantedItems.HAWTHORN_SAPLING.get(), Items.POPPY)
				.cookColor(92, 60, 22)
				.finalColor(128, 90, 43).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.BREW_OF_THE_DEPTHS.get(), 3), 0)
				.inputs(Items.LILY_PAD, Items.INK_SAC, EnchantedItems.MANDRAKE_ROOT.get(),
						EnchantedItems.TEAR_OF_THE_GODDESS.get(), EnchantedItems.WATER_ARTICHOKE.get(),
						EnchantedItems.ODOUR_OF_PURITY.get())
				.cookColor(24, 110, 168)
				.finalColor(84, 186, 214).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.BREW_OF_THE_GROTESQUE.get(), 3), 750)
				.inputs(EnchantedItems.MUTANDIS_EXTREMIS.get(), EnchantedItems.MANDRAKE_ROOT.get(),
						EnchantedItems.WATER_ARTICHOKE.get(), Items.GOLDEN_APPLE, EnchantedItems.TONGUE_OF_DOG.get(),
						Items.POISONOUS_POTATO)
				.cookColor(54, 42, 33)
				.finalColor(128, 95, 70).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.FLYING_OINTMENT.get()), 3000)
				.inputs(EnchantedItems.REDSTONE_SOUP.get())
				.inputs(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_SWIFTNESS))
				.inputs(Items.DIAMOND, Items.FEATHER, EnchantedItems.WOOL_OF_BAT.get(),
						EnchantedItems.BELLADONNA_FLOWER.get())
				.cookColor(112, 102, 21)
				.finalColor(219, 199, 42).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.HAPPENSTANCE_OIL.get()), 2000)
				.inputs(EnchantedItems.REDSTONE_SOUP.get())
				.inputs(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_NIGHT_VISION))
				.inputs(Items.ENDER_EYE, Items.GOLDEN_CARROT, Items.SPIDER_EYE, EnchantedItems.MANDRAKE_ROOT.get())
				.cookColor(50, 15, 110)
				.finalColor(84, 26, 184).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.MYSTIC_UNGUENT.get()), 3000)
				.inputs(EnchantedItems.REDSTONE_SOUP.get())
				.inputs(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_STRENGTH))
				.inputs(Items.DIAMOND, EnchantedItems.ROWAN_SAPLING.get(), EnchantedItems.CREEPER_HEART.get(),
						EnchantedItems.DEMONIC_BLOOD.get())
				.cookColor(24, 48, 22)
				.finalColor(28, 94, 22).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.REDSTONE_SOUP.get()), 1000)
				.inputs(Items.REDSTONE, EnchantedItems.DROP_OF_LUCK.get(), EnchantedItems.WOOL_OF_BAT.get(),
						EnchantedItems.TONGUE_OF_DOG.get(), EnchantedItems.BELLADONNA_FLOWER.get(),
						EnchantedItems.MANDRAKE_ROOT.get())
				.cookColor(128, 30, 23)
				.finalColor(222, 49, 29).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.SOUL_OF_THE_WORLD.get(), 2), 4000)
				.inputs(EnchantedItems.REDSTONE_SOUP.get())
				.inputs(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_REGENERATION))
				.inputs(EnchantedItems.ROWAN_SAPLING.get(), EnchantedItems.ATTUNED_STONE.get(),
						EnchantedItems.MANDRAKE_ROOT.get(), Items.GOLDEN_APPLE)
				.cookColor(13, 92, 25)
				.finalColor(9, 153, 31).save(consumer);
		CauldronTypeRecipeBuilder.kettle(stack(EnchantedItems.SPIRIT_OF_OTHERWHERE.get(), 2), 4000)
				.inputs(EnchantedItems.REDSTONE_SOUP.get())
				.inputs(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_SWIFTNESS))
				.inputs(EnchantedItems.WOOL_OF_BAT.get(), Items.ENDER_EYE, Items.ENDER_EYE,
						EnchantedItems.DROP_OF_LUCK.get())
				.cookColor(47, 22, 69)
				.finalColor(78, 22, 128).save(consumer);
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
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4).requires(logs).group("planks").unlockedBy(getHasName(logs), has(logs)).save(consumer);
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

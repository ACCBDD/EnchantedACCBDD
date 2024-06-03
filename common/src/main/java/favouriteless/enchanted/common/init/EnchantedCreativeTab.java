package favouriteless.enchanted.common.init;

import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class EnchantedCreativeTab {

    public static final Supplier<CreativeModeTab> TAB = register("main",
            () -> EnchantedItems.ENCHANTED_BROOMSTICK.get().getDefaultInstance(),
            (params, out) -> {
                out.accept(EnchantedItems.ALDER_LOG.get());
                out.accept(EnchantedItems.STRIPPED_ALDER_LOG.get());
                out.accept(EnchantedItems.ALDER_PLANKS.get());
                out.accept(EnchantedItems.ALDER_STAIRS.get());
                out.accept(EnchantedItems.ALDER_SLAB.get());
                out.accept(EnchantedItems.ALDER_FENCE.get());
                out.accept(EnchantedItems.ALDER_FENCE_GATE.get());
                out.accept(EnchantedItems.ALDER_PRESSURE_PLATE.get());
                out.accept(EnchantedItems.ALDER_BUTTON.get());
                out.accept(EnchantedItems.HAWTHORN_LOG.get());
                out.accept(EnchantedItems.STRIPPED_HAWTHORN_LOG.get());
                out.accept(EnchantedItems.HAWTHORN_PLANKS.get());
                out.accept(EnchantedItems.HAWTHORN_STAIRS.get());
                out.accept(EnchantedItems.HAWTHORN_SLAB.get());
                out.accept(EnchantedItems.HAWTHORN_FENCE.get());
                out.accept(EnchantedItems.HAWTHORN_FENCE_GATE.get());
                out.accept(EnchantedItems.HAWTHORN_PRESSURE_PLATE.get());
                out.accept(EnchantedItems.HAWTHORN_BUTTON.get());
                out.accept(EnchantedItems.ROWAN_LOG.get());
                out.accept(EnchantedItems.STRIPPED_ROWAN_LOG.get());
                out.accept(EnchantedItems.ROWAN_PLANKS.get());
                out.accept(EnchantedItems.ROWAN_STAIRS.get());
                out.accept(EnchantedItems.ROWAN_SLAB.get());
                out.accept(EnchantedItems.ROWAN_FENCE.get());
                out.accept(EnchantedItems.ROWAN_FENCE_GATE.get());
                out.accept(EnchantedItems.ROWAN_PRESSURE_PLATE.get());
                out.accept(EnchantedItems.ROWAN_BUTTON.get());
                out.accept(EnchantedItems.ALDER_LEAVES.get());
                out.accept(EnchantedItems.HAWTHORN_LEAVES.get());
                out.accept(EnchantedItems.ROWAN_LEAVES.get());

                out.accept(EnchantedItems.ALTAR.get());
                out.accept(EnchantedItems.WITCH_OVEN.get());
                out.accept(EnchantedItems.FUME_FUNNEL.get());
                out.accept(EnchantedItems.FUME_FUNNEL_FILTERED.get());
                out.accept(EnchantedItems.DISTILLERY.get());
                out.accept(EnchantedItems.WITCH_CAULDRON.get());
                out.accept(EnchantedItems.KETTLE.get());
                out.accept(EnchantedItems.SPINNING_WHEEL.get());
                out.accept(EnchantedItems.POPPET_SHELF.get());

                out.accept(EnchantedItems.CHALICE.get());
                out.accept(EnchantedItems.CHALICE_FILLED.get());
                out.accept(EnchantedItems.CANDELABRA.get());
                out.accept(EnchantedItems.INFINITY_EGG.get());

                out.accept(EnchantedItems.WICKER_BUNDLE.get());

                out.accept(EnchantedItems.ALDER_SAPLING.get());
                out.accept(EnchantedItems.HAWTHORN_SAPLING.get());
                out.accept(EnchantedItems.ROWAN_SAPLING.get());
                out.accept(EnchantedItems.WATER_ARTICHOKE_SEEDS.get());
                out.accept(EnchantedItems.BELLADONNA_SEEDS.get());
                out.accept(EnchantedItems.MANDRAKE_SEEDS.get());
                out.accept(EnchantedItems.SNOWBELL_SEEDS.get());
                out.accept(EnchantedItems.WOLFSBANE_SEEDS.get());
                out.accept(EnchantedItems.GARLIC.get());
                out.accept(EnchantedItems.ROWAN_BERRIES.get());
                out.accept(EnchantedItems.WATER_ARTICHOKE.get());
                out.accept(EnchantedItems.BELLADONNA_FLOWER.get());
                out.accept(EnchantedItems.MANDRAKE_ROOT.get());
                out.accept(EnchantedItems.ICY_NEEDLE.get());
                out.accept(EnchantedItems.WOLFSBANE_FLOWER.get());

                out.accept(EnchantedItems.GLINT_WEED.get());
                out.accept(EnchantedItems.EMBER_MOSS.get());
                out.accept(EnchantedItems.SPANISH_MOSS.get());
                out.accept(EnchantedItems.BLOOD_POPPY.get());

                out.accept(EnchantedItems.ANOINTING_PASTE.get());
                out.accept(EnchantedItems.MUTANDIS.get());
                out.accept(EnchantedItems.MUTANDIS_EXTREMIS.get());
                out.accept(EnchantedItems.TAGLOCK.get());
                out.accept(EnchantedItems.GOLDEN_CHALK.get());
                out.accept(EnchantedItems.RITUAL_CHALK.get());
                out.accept(EnchantedItems.NETHER_CHALK.get());
                out.accept(EnchantedItems.OTHERWHERE_CHALK.get());

                out.accept(EnchantedItems.ARTHANA.get());
                out.accept(EnchantedItems.EARMUFFS.get());
                out.accept(EnchantedItems.BROOM.get());
                out.accept(EnchantedItems.ENCHANTED_BROOMSTICK.get());

                out.accept(EnchantedItems.SOFT_CLAY_JAR.get());
                out.accept(EnchantedItems.CLAY_JAR.get());
                out.accept(EnchantedItems.BREATH_OF_THE_GODDESS.get());
                out.accept(EnchantedItems.EXHALE_OF_THE_HORNED_ONE.get());
                out.accept(EnchantedItems.FOUL_FUME.get());
                out.accept(EnchantedItems.HINT_OF_REBIRTH.get());
                out.accept(EnchantedItems.TEAR_OF_THE_GODDESS.get());
                out.accept(EnchantedItems.WHIFF_OF_MAGIC.get());
                out.accept(EnchantedItems.CONDENSED_FEAR.get());
                out.accept(EnchantedItems.DIAMOND_VAPOUR.get());
                out.accept(EnchantedItems.DROP_OF_LUCK.get());
                out.accept(EnchantedItems.ENDER_DEW.get());
                out.accept(EnchantedItems.FOCUSED_WILL.get());
                out.accept(EnchantedItems.DEMONIC_BLOOD.get());
                out.accept(EnchantedItems.MELLIFLUOUS_HUNGER.get());
                out.accept(EnchantedItems.ODOUR_OF_PURITY.get());
                out.accept(EnchantedItems.OIL_OF_VITRIOL.get());
                out.accept(EnchantedItems.PURIFIED_MILK.get());
                out.accept(EnchantedItems.REEK_OF_MISFORTUNE.get());

                out.accept(EnchantedItems.ATTUNED_STONE.get());
                out.accept(EnchantedItems.ATTUNED_STONE_CHARGED.get());
                out.accept(EnchantedItems.WAYSTONE.get());
                out.accept(EnchantedItems.GYPSUM.get());
                out.accept(EnchantedItems.QUICKLIME.get());
                out.accept(EnchantedItems.REFINED_EVIL.get());
                out.accept(EnchantedItems.WOOD_ASH.get());
                out.accept(EnchantedItems.BONE_NEEDLE.get());
                out.accept(EnchantedItems.WOOL_OF_BAT.get());
                out.accept(EnchantedItems.TONGUE_OF_DOG.get());
                out.accept(EnchantedItems.CREEPER_HEART.get());
                out.accept(EnchantedItems.ENT_TWIG.get());

                out.accept(EnchantedItems.REDSTONE_SOUP.get());
                out.accept(EnchantedItems.FLYING_OINTMENT.get());
                out.accept(EnchantedItems.MYSTIC_UNGUENT.get());
                out.accept(EnchantedItems.GHOST_OF_THE_LIGHT.get());
                out.accept(EnchantedItems.SOUL_OF_THE_WORLD.get());
                out.accept(EnchantedItems.SPIRIT_OF_OTHERWHERE.get());
                out.accept(EnchantedItems.INFERNAL_ANIMUS.get());
                out.accept(EnchantedItems.HAPPENSTANCE_OIL.get());
                out.accept(EnchantedItems.BREW_OF_THE_GROTESQUE.get());
                out.accept(EnchantedItems.BREW_OF_SPROUTING.get());
                out.accept(EnchantedItems.BREW_OF_LOVE.get());
                out.accept(EnchantedItems.BREW_OF_THE_DEPTHS.get());

                out.accept(EnchantedItems.POPPET.get());
                out.accept(EnchantedItems.POPPET_INFUSED.get());
                out.accept(EnchantedItems.POPPET_STURDY.get());
                out.accept(EnchantedItems.ARMOUR_POPPET.get());
                out.accept(EnchantedItems.ARMOUR_POPPET_INFUSED.get());
                out.accept(EnchantedItems.ARMOUR_POPPET_STURDY.get());
                out.accept(EnchantedItems.EARTH_POPPET.get());
                out.accept(EnchantedItems.EARTH_POPPET_INFUSED.get());
                out.accept(EnchantedItems.EARTH_POPPET_STURDY.get());
                out.accept(EnchantedItems.FIRE_POPPET.get());
                out.accept(EnchantedItems.FIRE_POPPET_INFUSED.get());
                out.accept(EnchantedItems.FIRE_POPPET_STURDY.get());
                out.accept(EnchantedItems.HUNGER_POPPET.get());
                out.accept(EnchantedItems.HUNGER_POPPET_INFUSED.get());
                out.accept(EnchantedItems.HUNGER_POPPET_STURDY.get());
                out.accept(EnchantedItems.MAGIC_POPPET.get());
                out.accept(EnchantedItems.MAGIC_POPPET_INFUSED.get());
                out.accept(EnchantedItems.MAGIC_POPPET_STURDY.get());
                out.accept(EnchantedItems.WATER_POPPET.get());
                out.accept(EnchantedItems.WATER_POPPET_INFUSED.get());
                out.accept(EnchantedItems.WATER_POPPET_STURDY.get());
                out.accept(EnchantedItems.VOID_POPPET.get());
                out.accept(EnchantedItems.VOID_POPPET_INFUSED.get());
                out.accept(EnchantedItems.VOID_POPPET_STURDY.get());
                out.accept(EnchantedItems.VOODOO_POPPET.get());
                out.accept(EnchantedItems.VOODOO_PROTECTION_POPPET.get());
                out.accept(EnchantedItems.VOODOO_PROTECTION_POPPET_INFUSED.get());
                out.accept(EnchantedItems.VOODOO_PROTECTION_POPPET_STURDY.get());
                out.accept(EnchantedItems.TOOL_POPPET.get());
                out.accept(EnchantedItems.TOOL_POPPET_INFUSED.get());
                out.accept(EnchantedItems.TOOL_POPPET_STURDY.get());


                out.accept(EnchantedItems.CIRCLE_TALISMAN.get());
                for(int i = 1; i < 4; i++) {
                    for(String key : new String[] { "small", "medium", "large" }) {
                        ItemStack stack = new ItemStack(EnchantedItems.CIRCLE_TALISMAN.get());
                        CompoundTag nbt = stack.getOrCreateTag();
                        nbt.putInt(key, i);
                        out.accept(stack);
                    }
                }
            });



    public static Supplier<CreativeModeTab> register(String name, Supplier<ItemStack> iconSupplier, DisplayItemsGenerator itemsGenerator) {
        return CommonServices.COMMON_REGISTRY.registerCreativeTab(name, iconSupplier, itemsGenerator);
    }

    public static void load() {} // Method which exists purely to load the class.

}

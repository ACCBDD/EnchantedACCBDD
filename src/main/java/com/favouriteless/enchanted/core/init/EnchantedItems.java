package com.favouriteless.enchanted.core.init;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.items.ChalkItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantedItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Enchanted.MOD_ID);

    // Block Items
    public static final RegistryObject<Item> ALTAR = ITEMS.register("altar", () -> new BlockItem(EnchantedBlocks.ALTAR.get(), new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> LEATHER_BLOCK = ITEMS.register("leather_block", () -> new BlockItem(EnchantedBlocks.LEATHER_BLOCK.get(), new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> WITCH_OVEN = ITEMS.register("witch_oven", () -> new BlockItem(EnchantedBlocks.WITCH_OVEN.get(), new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> FUME_FUNNEL = ITEMS.register("fume_funnel", () -> new BlockItem(EnchantedBlocks.FUME_FUNNEL.get(), new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> FUME_FUNNEL_FILTERED = ITEMS.register("fume_funnel_filtered", () -> new BlockItem(EnchantedBlocks.FUME_FUNNEL_FILTERED.get(), new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> DISTILLERY = ITEMS.register("distillery", () -> new BlockItem(EnchantedBlocks.DISTILLERY.get(), new Item.Properties().tab(EnchantedTabs.MAIN)));

    public static final RegistryObject<Item> ROWAN_LOG = ITEMS.register("rowan_log", () -> new BlockItem(EnchantedBlocks.ROWAN_LOG.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> ROWAN_PLANKS = ITEMS.register("rowan_planks", () -> new BlockItem(EnchantedBlocks.ROWAN_PLANKS.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> ROWAN_LEAVES = ITEMS.register("rowan_leaves", () -> new BlockItem(EnchantedBlocks.ROWAN_LEAVES.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    //public static final RegistryObject<Item> ROWAN_SAPLING = ITEMS.register("rowan_sapling", () -> new BlockItem(EnchantedBlocks.ROWAN_SAPLING.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> HAWTHORN_LOG = ITEMS.register("hawthorn_log", () -> new BlockItem(EnchantedBlocks.HAWTHORN_LOG.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> HAWTHORN_PLANKS = ITEMS.register("hawthorn_planks", () -> new BlockItem(EnchantedBlocks.HAWTHORN_PLANKS.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> HAWTHORN_LEAVES = ITEMS.register("hawthorn_leaves", () -> new BlockItem(EnchantedBlocks.HAWTHORN_LEAVES.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    //public static final RegistryObject<Item> HAWTHORN_SAPLING = ITEMS.register("hawthorn_sapling", () -> new BlockItem(EnchantedBlocks.HAWTHORN_SAPLING.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));

    public static final RegistryObject<Item> ALDER_LOG = ITEMS.register("alder_log", () -> new BlockItem(EnchantedBlocks.ALDER_LOG.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> ALDER_PLANKS = ITEMS.register("alder_planks", () -> new BlockItem(EnchantedBlocks.ALDER_PLANKS.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    public static final RegistryObject<Item> ALDER_LEAVES = ITEMS.register("alder_leaves", () -> new BlockItem(EnchantedBlocks.ALDER_LEAVES.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));
    //public static final RegistryObject<Item> ALDER_SAPLING = ITEMS.register("alder_sapling", () -> new BlockItem(EnchantedBlocks.ALDER_SAPLING.get(), new Item.Properties().tab(EnchantedTabs.PLANTS)));

    public static final RegistryObject<Item> CHALK_GOLD = ITEMS.register("chalk_gold", () -> new ChalkItem(new Item.Properties().tab(EnchantedTabs.MAIN).stacksTo(1).durability(3), EnchantedBlocks.CHALK_GOLD.get()));
    public static final RegistryObject<Item> CHALK_WHITE = ITEMS.register("chalk_white", () -> new ChalkItem(new Item.Properties().tab(EnchantedTabs.MAIN).stacksTo(1).durability(40), EnchantedBlocks.CHALK_WHITE.get()));
    public static final RegistryObject<Item> CHALK_RED = ITEMS.register("chalk_red", () -> new ChalkItem(new Item.Properties().tab(EnchantedTabs.MAIN).stacksTo(1).durability(40), EnchantedBlocks.CHALK_RED.get()));
    public static final RegistryObject<Item> CHALK_PURPLE = ITEMS.register("chalk_purple", () -> new ChalkItem(new Item.Properties().tab(EnchantedTabs.MAIN).stacksTo(1).durability(40), EnchantedBlocks.CHALK_PURPLE.get()));

    //public static final RegistryObject<Item> DEMON_HEART = ITEMS.register("demon_heart", () -> new BlockItem(EnchantedBlocks.DEMON_HEART.get(), new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));

    // Items
    public static final RegistryObject<Item> CLAY_JAR_SOFT = ITEMS.register("clay_jar_soft", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> CLAY_JAR = ITEMS.register("clay_jar", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> BREATH_OF_THE_GODDESS = ITEMS.register("breath_of_the_goddess", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> EXHALE_OF_THE_HORNED_ONE = ITEMS.register("exhale_of_the_horned_one", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> FOUL_FUME = ITEMS.register("foul_fume", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> HINT_OF_REBIRTH = ITEMS.register("hint_of_rebirth", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> TEAR_OF_THE_GODDESS = ITEMS.register("tear_of_the_goddess", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> WHIFF_OF_MAGIC = ITEMS.register("whiff_of_magic", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> CONDENSED_FEAR = ITEMS.register("condensed_fear", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> DIAMOND_VAPOUR = ITEMS.register("diamond_vapour", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> DROP_OF_LUCK = ITEMS.register("drop_of_luck", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> ENDER_DEW = ITEMS.register("ender_dew", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> FOCUSED_WILL = ITEMS.register("focused_will", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> DEMONIC_BLOOD = ITEMS.register("demonic_blood", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> MELLIFLUOUS_HUNGER = ITEMS.register("mellifluous_hunger", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> ODOUR_OF_PURITY = ITEMS.register("odour_of_purity", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> OIL_OF_VITRIOL = ITEMS.register("oil_of_vitriol", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> PURIFIED_MILK = ITEMS.register("purified_milk", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> REEK_OF_MISFORTUNE = ITEMS.register("reek_of_misfortune", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> ATTUNED_STONE = ITEMS.register("attuned_stone", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> ATTUNED_STONE_CHARGED = ITEMS.register("attuned_stone_charged", () -> new SimpleFoiledItem(new Item.Properties().stacksTo(1).tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> GYPSUM = ITEMS.register("gypsum", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> QUICKLIME = ITEMS.register("quicklime", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> REFINED_EVIL = ITEMS.register("refined_evil", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> ROWAN_BERRIES = ITEMS.register("rowan_berries", () -> new Item((new Item.Properties()).tab(EnchantedTabs.INGREDIENTS).food(new Food.Builder().nutrition(3).build())));
    public static final RegistryObject<Item> WOOD_ASH = ITEMS.register("wood_ash", () -> new Item(new Item.Properties().tab(EnchantedTabs.INGREDIENTS)));
    public static final RegistryObject<Item> FUME_FILTER = ITEMS.register("fume_filter", () -> new Item(new Item.Properties().tab(EnchantedTabs.MAIN)));

    // Armor
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet", () -> new ArmorItem(EnchantedArmorMaterials.EMERALD, EquipmentSlotType.HEAD, new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", () -> new ArmorItem(EnchantedArmorMaterials.EMERALD, EquipmentSlotType.CHEST, new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", () -> new ArmorItem(EnchantedArmorMaterials.EMERALD, EquipmentSlotType.LEGS, new Item.Properties().tab(EnchantedTabs.MAIN)));
    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots", () -> new ArmorItem(EnchantedArmorMaterials.EMERALD, EquipmentSlotType.FEET, new Item.Properties().tab(EnchantedTabs.MAIN)));
}

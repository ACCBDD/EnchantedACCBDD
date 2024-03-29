package com.favouriteless.enchanted.datagen.providers.tag;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.init.EnchantedTags;
import com.favouriteless.enchanted.common.init.EnchantedTags.Blocks;
import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTagProvider extends ItemTagsProvider {

    public ItemTagProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
        super(generator, blockTagsProvider, Enchanted.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        addEnchantedTags();
        addVanillaTags();
    }

    public void addEnchantedTags() {
        // Copied block tags
        copy(Blocks.CHALICES, EnchantedTags.Items.CHALICES);
        copy(Blocks.CHALKS, EnchantedTags.Items.CHALKS);
        copy(Blocks.LEAVES, EnchantedTags.Items.LEAVES);
        copy(Blocks.LOGS, EnchantedTags.Items.LOGS);
        copy(Blocks.PLANKS, EnchantedTags.Items.PLANKS);
        copy(Blocks.SAPLINGS, EnchantedTags.Items.SAPLINGS);
        copy(Blocks.SLABS, EnchantedTags.Items.SLABS);
        copy(Blocks.STAIRS, EnchantedTags.Items.STAIRS);
        copy(Blocks.WOODEN_SLABS, EnchantedTags.Items.WOODEN_SLABS);
        copy(Blocks.WOODEN_STAIRS, EnchantedTags.Items.WOODEN_STAIRS);

        // Other tags
        tag(EnchantedTags.Items.RAW_FOODS)
                .add(Items.BEEF, Items.KELP, Items.POTATO, Items.CHORUS_FRUIT, Items.CHICKEN, Items.COD, Items.MUTTON,
                        Items.PORKCHOP, Items.RABBIT, Items.SALMON);
        tag(EnchantedTags.Items.TOOL_POPPET_BLACKLIST)
                .addTag(EnchantedTags.Items.CHALKS);
    }

    public void addVanillaTags() {
        tag(ItemTags.LEAVES)
                .addTag(EnchantedTags.Items.LEAVES);
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(EnchantedTags.Items.LOGS);
        tag(ItemTags.PLANKS)
                .addTag(EnchantedTags.Items.PLANKS);
        tag(ItemTags.SAPLINGS)
                .addTag(EnchantedTags.Items.SAPLINGS);
        tag(ItemTags.SLABS)
                .addTag(EnchantedTags.Items.SLABS);
        tag(ItemTags.STAIRS)
                .addTag(EnchantedTags.Items.STAIRS);
        tag(ItemTags.SMALL_FLOWERS)
                .add(EnchantedItems.BLOOD_POPPY.get());
        tag(ItemTags.WOODEN_SLABS)
                .addTag(EnchantedTags.Items.WOODEN_SLABS);
        tag(ItemTags.WOODEN_STAIRS)
                .addTag(EnchantedTags.Items.WOODEN_STAIRS);
    }

}

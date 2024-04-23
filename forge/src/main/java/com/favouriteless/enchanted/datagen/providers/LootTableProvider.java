package com.favouriteless.enchanted.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Set;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider {

    public LootTableProvider(PackOutput output, Set<ResourceLocation> requiredTables, List<SubProviderEntry> subProviders) {
        super(output, requiredTables, subProviders);
    }

}

package favouriteless.enchanted.datagen.providers;

import favouriteless.enchanted.datagen.providers.loot_tables.BlockLootSubProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider {

    public LootTableProvider(PackOutput output) {
        super(output, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(
                        BlockLootSubProvider::new,
                        LootContextParamSets.BLOCK
                )
        ));
    }

}

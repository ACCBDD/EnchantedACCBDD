package favouriteless.enchanted.neoforge.datagen.providers.tag;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.init.EnchantedTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BiomeTagProvider extends BiomeTagsProvider {

    public BiomeTagProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider, Enchanted.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        addEnchantedTags(provider);
    }

    public void addEnchantedTags(Provider provider) {
        tag(EnchantedTags.Biomes.OVERHEATING_BIOMES)
                .add(Biomes.BADLANDS, Biomes.BAMBOO_JUNGLE, Biomes.DESERT, Biomes.ERODED_BADLANDS, Biomes.JUNGLE,
                        Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SPARSE_JUNGLE, Biomes.WINDSWEPT_SAVANNA,
                        Biomes.WOODED_BADLANDS);
    }

}

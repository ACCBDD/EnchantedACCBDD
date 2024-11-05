package net.favouriteless.enchanted.neoforge.datagen;

import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.neoforge.datagen.providers.*;
import net.favouriteless.enchanted.neoforge.datagen.providers.tag.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Enchanted.MOD_ID, bus = Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		DataGenerator gen = event.getGenerator();
		PackOutput output = gen.getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

		BlockTagProvider blockTagProvider = gen.addProvider(true, new BlockTagProvider(output, provider, fileHelper));
		gen.addProvider(true, new ItemTagProvider(output, provider, fileHelper, blockTagProvider.contentsGetter()));
		gen.addProvider(true, new EntityTypeTagProvider(output, provider, fileHelper));
		gen.addProvider(true, new MobEffectTagProvider(output, provider, fileHelper));
		gen.addProvider(true, new BiomeTagProvider(output, provider, fileHelper));
		gen.addProvider(true, new DamageTypeTagProvider(output, provider, fileHelper));
		gen.addProvider(true, new ERecipeProvider(output, provider));

		gen.addProvider(true, new BlockstateProvider(output, fileHelper));
		gen.addProvider(true, new ItemModelProvider(output, fileHelper));
		gen.addProvider(true, new LanguageProvider(output));
		gen.addProvider(true, ELootTableProvider.create(output, provider));
	}

}

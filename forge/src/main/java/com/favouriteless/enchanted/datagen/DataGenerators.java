package com.favouriteless.enchanted.datagen;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.datagen.providers.BlockstateProvider;
import com.favouriteless.enchanted.datagen.providers.ItemModelProvider;
import com.favouriteless.enchanted.datagen.providers.LootTableProvider;
import com.favouriteless.enchanted.datagen.providers.RecipeProvider;
import com.favouriteless.enchanted.datagen.providers.tag.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid=Enchanted.MOD_ID, bus=Bus.MOD)
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

		gen.addProvider(true, new BlockstateProvider(output, fileHelper));
		gen.addProvider(true, new ItemModelProvider(output, fileHelper));
		gen.addProvider(true, new RecipeProvider(output));
		gen.addProvider(true, new LootTableProvider(output));
	}

}

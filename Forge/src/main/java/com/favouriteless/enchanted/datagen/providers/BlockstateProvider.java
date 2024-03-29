package com.favouriteless.enchanted.datagen.providers;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.blocks.FumeFunnelBlock;
import com.favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import com.favouriteless.enchanted.common.blocks.chalk.ChalkCircleBlock;
import com.favouriteless.enchanted.common.blocks.chalk.GoldChalkBlock;
import com.favouriteless.enchanted.common.blocks.crops.BloodPoppyBlock;
import com.favouriteless.enchanted.common.blocks.crops.CropsBlockAgeFive;
import com.favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockstateProvider extends BlockStateProvider {

	public BlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Enchanted.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		rotatedPillarBlockWithItem(EnchantedBlocks.ROWAN_LOG.get());
		rotatedPillarBlockWithItem(EnchantedBlocks.HAWTHORN_LOG.get());
		rotatedPillarBlockWithItem(EnchantedBlocks.ALDER_LOG.get());
		rotatedPillarBlockWithItem(EnchantedBlocks.WICKER_BUNDLE.get());
		simpleBlockWithItem(EnchantedBlocks.ROWAN_PLANKS.get());
		simpleBlockWithItem(EnchantedBlocks.HAWTHORN_PLANKS.get());
		simpleBlockWithItem(EnchantedBlocks.ALDER_PLANKS.get());
		slabBlockWithItem(EnchantedBlocks.ROWAN_SLAB.get(), EnchantedBlocks.ROWAN_PLANKS.get());
		slabBlockWithItem(EnchantedBlocks.HAWTHORN_SLAB.get(), EnchantedBlocks.HAWTHORN_PLANKS.get());
		slabBlockWithItem(EnchantedBlocks.ALDER_SLAB.get(), EnchantedBlocks.ALDER_PLANKS.get());
		stairsBlockWithItem(EnchantedBlocks.ROWAN_STAIRS.get(), EnchantedBlocks.ROWAN_PLANKS.get());
		stairsBlockWithItem(EnchantedBlocks.HAWTHORN_STAIRS.get(), EnchantedBlocks.HAWTHORN_PLANKS.get());
		stairsBlockWithItem(EnchantedBlocks.ALDER_STAIRS.get(), EnchantedBlocks.ALDER_PLANKS.get());
		leafRandomBlockWithItem(EnchantedBlocks.ROWAN_LEAVES.get(), 4);
		leafRandomBlockWithItem(EnchantedBlocks.HAWTHORN_LEAVES.get(), 4);
		leafRandomBlockWithItem(EnchantedBlocks.ALDER_LEAVES.get(), 4);
		crossBlockWithItem(EnchantedBlocks.ROWAN_SAPLING.get());
		crossBlockWithItem(EnchantedBlocks.HAWTHORN_SAPLING.get());
		crossBlockWithItem(EnchantedBlocks.ALDER_SAPLING.get());

		chalkBlockWithItem(EnchantedBlocks.CHALK_WHITE.get());
		chalkBlockWithItem(EnchantedBlocks.CHALK_RED.get());
		chalkBlockWithItem(EnchantedBlocks.CHALK_PURPLE.get());
		goldChalkBlockWithItem(EnchantedBlocks.CHALK_GOLD.get());

		simpleBlockItem(EnchantedBlocks.ALTAR.get(), models().getExistingFile(modLoc("block/altar")));
		horizontalLitBlockWithItem(EnchantedBlocks.WITCH_OVEN.get(), "_on", 180);
		fumeFunnelWithItem(EnchantedBlocks.FUME_FUNNEL.get());
		fumeFunnelWithItem(EnchantedBlocks.FUME_FUNNEL_FILTERED.get());
		horizontalLitBlockWithItem(EnchantedBlocks.DISTILLERY.get(), "", 180);
		complexBlockWithItem(EnchantedBlocks.WITCH_CAULDRON.get());
		complexBlockWithItem(EnchantedBlocks.CHALICE.get());
		complexBlockWithItem(EnchantedBlocks.CHALICE_FILLED.get());
		complexBlockWithItem(EnchantedBlocks.CHALICE_FILLED_MILK.get());
		complexBlockWithItem(EnchantedBlocks.INFINITY_EGG.get(), "block/dragon_egg");
		complexBlockWithItem(EnchantedBlocks.CANDELABRA.get());
		kettleBlockWithItem(EnchantedBlocks.KETTLE.get());
		complexBlockWithItem(EnchantedBlocks.POPPET_SHELF.get());

		cropsBlockWithItem(EnchantedBlocks.BELLADONNA.get(), "crop");
		cropsBlockWithItem(EnchantedBlocks.SNOWBELL.get(), "cross");
		cropsBlockWithItem(EnchantedBlocks.ARTICHOKE.get(), "crop");
		cropsBlockWithItem(EnchantedBlocks.MANDRAKE.get(), "crop");
		cropsBlockWithItem(EnchantedBlocks.GARLIC.get(), "crop");
		cropsBlockWithItem(EnchantedBlocks.WOLFSBANE.get(), "cross");
		crossBlockWithItem(EnchantedBlocks.GLINT_WEED.get());
		crossBlockWithItem(EnchantedBlocks.EMBER_MOSS.get());
		getVariantBuilder(EnchantedBlocks.BLOOD_POPPY.get()).forAllStates(state -> {
			String name = "block/" + ForgeRegistries.BLOCKS.getKey(EnchantedBlocks.BLOOD_POPPY.get()).getPath();
			if(state.getValue(BloodPoppyBlock.FILLED)) name = name + "_filled";
			return ConfiguredModel.builder()
					.modelFile(models().withExistingParent(name, mcLoc("block/cross")).texture("cross", name))
					.build();
		});
		simpleItem(EnchantedBlocks.BLOOD_POPPY.get());
		simpleItem(EnchantedBlocks.SPANISH_MOSS.get());

	}

	private void kettleBlockWithItem(Block block) {
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(models().getExistingFile(state.getValue(KettleBlock.TYPE) == 0 ? modLoc("block/kettle") : state.getValue(KettleBlock.TYPE) == 1 ? modLoc("block/kettle_hanging") : modLoc("block/kettle_hanging_beam")))
				.rotationY((int)state.getValue(KettleBlock.FACING).toYRot() % 360)
				.build()
		);
		simpleBlockItem(block, models().getExistingFile(modLoc("block/kettle")));
	}

	private void simpleItem(Block block) {
		String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
		itemModels()
				.withExistingParent(ModelProvider.ITEM_FOLDER + "/" + name, mcLoc(ModelProvider.ITEM_FOLDER + "/generated"))
				.texture("layer0", ModelProvider.BLOCK_FOLDER + "/" + name);
	}

	private void chalkBlockWithItem(Block block) {
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
						.modelFile(models().withExistingParent("block/glyph_" + (state.getValue(ChalkCircleBlock.GLYPH)%12), modLoc("block/glyph")).texture("top", "block/glyph_"+(state.getValue(ChalkCircleBlock.GLYPH)%12)))
						.rotationY((state.getValue(ChalkCircleBlock.GLYPH) % 4 * 90))
						.build()
				);
		itemModels()
				.withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), mcLoc(ModelProvider.ITEM_FOLDER + "/generated"))
				.texture("layer0", modLoc(ModelProvider.ITEM_FOLDER + "/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
	}

	private void goldChalkBlockWithItem(Block block) {
		VariantBlockStateBuilder builder = getVariantBuilder(block);
		String name = ForgeRegistries.BLOCKS.getKey(block).getPath();

		ModelFile model = models().withExistingParent(name, modLoc("block/glyph")).texture("top", modLoc("block/glyph_gold"));
		for(int j = 0; j < 4; j++) {
			builder.partialState().with(GoldChalkBlock.GLYPH, j).setModels(new ConfiguredModel(model, 0, j*90, false));
		}

		itemModels()
				.withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), mcLoc(ModelProvider.ITEM_FOLDER + "/generated"))
				.texture("layer0", modLoc(ModelProvider.ITEM_FOLDER + "/" + name));
	}

	private void simpleBlockWithItem(Block block) {
		simpleBlock(block);
		simpleBlockItem(block, models().getExistingFile(modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block).getPath())));
	}

	private void complexBlockWithItem(Block block) {
		ModelFile model = models().getExistingFile(modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
		simpleBlock(block, model);
		simpleBlockItem(block, model);
	}

	private void complexBlockWithItem(Block block, String location) {
		ModelFile model = models().getExistingFile(mcLoc(location));
		simpleBlock(block, model);
		simpleBlockItem(block, model);
	}

	private void slabBlockWithItem(SlabBlock block, Block parent) {
		ResourceLocation path = blockTexture(parent);
		slabBlock(block, path, blockTexture(parent));
		simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
	}

	private void stairsBlockWithItem(StairBlock block, Block parent) {
		stairsBlock(block, blockTexture(parent));
		simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
	}

	private void horizontalLitBlockWithItem(Block block, String onSuffix, int angleOffset) {
		String name = ForgeRegistries.BLOCKS.getKey(block).getPath();

		ModelFile modelUnlit = models().getExistingFile(modLoc("block/" + name));
		ModelFile modelLit = models().getExistingFile(modLoc("block/" + name + onSuffix));

		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(state.getValue(BlockStateProperties.LIT) ? modelLit : modelUnlit)
				.rotationY((int)(state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) % 360)
				.build());
		itemModels().getBuilder(name)
				.parent(modelUnlit);
	}

	private void fumeFunnelWithItem(Block block) {
		String name = ForgeRegistries.BLOCKS.getKey(block).getPath();

		VariantBlockStateBuilder builder = getVariantBuilder(block);
		String[] types = { "_left", "_top", "_right", "" };

		for(int type = 0; type < types.length; type++) {
			ModelFile modelUnlit = models().getExistingFile(modLoc("block/" + name + types[type]));
			ModelFile modelLit = (type == 3) ? modelUnlit : models().getExistingFile(modLoc("block/" + name + types[type] + "_on"));
			for(Direction dir : Plane.HORIZONTAL) {
				builder.partialState().with(FumeFunnelBlock.TYPE, type).with(FumeFunnelBlock.FACING, dir).with(FumeFunnelBlock.LIT, false)
						.setModels(new ConfiguredModel(modelUnlit, 0, (int)(dir.toYRot() + 180), false));
				builder.partialState().with(FumeFunnelBlock.TYPE, type).with(FumeFunnelBlock.FACING, dir).with(FumeFunnelBlock.LIT, true)
							.setModels(new ConfiguredModel(modelLit, 0, (int)(dir.toYRot() + 180) % 360, false));
			}
		}

		itemModels().getBuilder(name)
				.parent(models().getExistingFile(modLoc("block/" + name + types[0])));
	}

	private void leafRandomBlockWithItem(Block block, int numVariants) {
		String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
		ConfiguredModel[] models = new ConfiguredModel[numVariants];
		for(int i = 0; i < numVariants; i++) {
			models[i] = new ConfiguredModel(models().getExistingFile(modLoc("block/" + name + "_" + i)));
		}
		getVariantBuilder(block).partialState().setModels(models);
		itemModels().getBuilder(name)
				.parent(models().getExistingFile(modLoc("block/" + name + "_0")));
	}

	private void rotatedPillarBlockWithItem(RotatedPillarBlock block) {
		logBlock(block);
		simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
	}

	private void crossBlock(Block block) {
		ResourceLocation path = blockTexture(block);
		simpleBlock(block, models().cross(path.toString(), path).renderType(new ResourceLocation("cutout")));
	}

	private void crossBlockWithItem(Block block) {
		crossBlock(block);
		simpleItem(block);
	}

	private void cropsBlockWithItem(CropsBlockAgeFive block, String parent) {
		String path = BlockModelProvider.BLOCK_FOLDER + "/" + ForgeRegistries.BLOCKS.getKey(block).getPath() + "_stage_";
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
						.modelFile(models()
								.withExistingParent(path + state.getValue(CropsBlockAgeFive.AGE_FIVE), mcLoc("block/" + parent))
								.texture(parent, path + state.getValue(CropsBlockAgeFive.AGE_FIVE))
								.renderType(new ResourceLocation("cutout")))
						.build());
	}

}

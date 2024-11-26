package favouriteless.enchanted.datagen.providers;

import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.blocks.FumeFunnelBlock;
import favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import favouriteless.enchanted.common.blocks.chalk.ChalkCircleBlock;
import favouriteless.enchanted.common.blocks.chalk.GoldChalkBlock;
import favouriteless.enchanted.common.blocks.crops.BloodPoppyBlock;
import favouriteless.enchanted.common.blocks.crops.CropsBlockAgeFive;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockstateProvider extends BlockStateProvider {

	public BlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, Enchanted.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		logWithItem(EnchantedBlocks.ROWAN_LOG.get());
		logWithItem(EnchantedBlocks.HAWTHORN_LOG.get());
		logWithItem(EnchantedBlocks.ALDER_LOG.get());
		logWithItem(EnchantedBlocks.WICKER_BUNDLE.get());
		logWithItem(EnchantedBlocks.STRIPPED_ALDER_LOG.get());
		logWithItem(EnchantedBlocks.STRIPPED_ROWAN_LOG.get());
		logWithItem(EnchantedBlocks.STRIPPED_HAWTHORN_LOG.get());
		fenceWithItem(EnchantedBlocks.ALDER_FENCE.get(), blockTexture(EnchantedBlocks.ALDER_PLANKS.get()));
		fenceWithItem(EnchantedBlocks.ROWAN_FENCE.get(), blockTexture(EnchantedBlocks.ROWAN_PLANKS.get()));
		fenceWithItem(EnchantedBlocks.HAWTHORN_FENCE.get(), blockTexture(EnchantedBlocks.HAWTHORN_PLANKS.get()));
		fenceGateWithItem(EnchantedBlocks.ALDER_FENCE_GATE.get(), blockTexture(EnchantedBlocks.ALDER_PLANKS.get()));
		fenceGateWithItem(EnchantedBlocks.ROWAN_FENCE_GATE.get(), blockTexture(EnchantedBlocks.ROWAN_PLANKS.get()));
		fenceGateWithItem(EnchantedBlocks.HAWTHORN_FENCE_GATE.get(), blockTexture(EnchantedBlocks.HAWTHORN_PLANKS.get()));
		buttonWithItem(EnchantedBlocks.ALDER_BUTTON.get(), blockTexture(EnchantedBlocks.ALDER_PLANKS.get()));
		buttonWithItem(EnchantedBlocks.HAWTHORN_BUTTON.get(), blockTexture(EnchantedBlocks.HAWTHORN_PLANKS.get()));
		buttonWithItem(EnchantedBlocks.ROWAN_BUTTON.get(), blockTexture(EnchantedBlocks.ROWAN_PLANKS.get()));
		pressurePlateWithItem(EnchantedBlocks.ROWAN_PRESSURE_PLATE.get(), blockTexture(EnchantedBlocks.ROWAN_PLANKS.get()));
		pressurePlateWithItem(EnchantedBlocks.ALDER_PRESSURE_PLATE.get(), blockTexture(EnchantedBlocks.ALDER_PLANKS.get()));
		pressurePlateWithItem(EnchantedBlocks.HAWTHORN_PRESSURE_PLATE.get(), blockTexture(EnchantedBlocks.HAWTHORN_PLANKS.get()));
		simpleWithItem(EnchantedBlocks.ROWAN_PLANKS.get());
		simpleWithItem(EnchantedBlocks.HAWTHORN_PLANKS.get());
		simpleWithItem(EnchantedBlocks.ALDER_PLANKS.get());
		slabWithItem(EnchantedBlocks.ROWAN_SLAB.get(), EnchantedBlocks.ROWAN_PLANKS.get());
		slabWithItem(EnchantedBlocks.HAWTHORN_SLAB.get(), EnchantedBlocks.HAWTHORN_PLANKS.get());
		slabWithItem(EnchantedBlocks.ALDER_SLAB.get(), EnchantedBlocks.ALDER_PLANKS.get());
		stairsWithItem(EnchantedBlocks.ROWAN_STAIRS.get(), EnchantedBlocks.ROWAN_PLANKS.get());
		stairsWithItem(EnchantedBlocks.HAWTHORN_STAIRS.get(), EnchantedBlocks.HAWTHORN_PLANKS.get());
		stairsWithItem(EnchantedBlocks.ALDER_STAIRS.get(), EnchantedBlocks.ALDER_PLANKS.get());
		leafRandomWithItem(EnchantedBlocks.ROWAN_LEAVES.get(), 4);
		leafRandomWithItem(EnchantedBlocks.HAWTHORN_LEAVES.get(), 4);
		leafRandomWithItem(EnchantedBlocks.ALDER_LEAVES.get(), 4);
		crossWithItem(EnchantedBlocks.ROWAN_SAPLING.get());
		crossWithItem(EnchantedBlocks.HAWTHORN_SAPLING.get());
		crossWithItem(EnchantedBlocks.ALDER_SAPLING.get());

		chalkWithItem(EnchantedBlocks.RITUAL_CHALK.get());
		chalkWithItem(EnchantedBlocks.NETHER_CHALK.get());
		chalkWithItem(EnchantedBlocks.OTHERWHERE_CHALK.get());
		goldChalkWithItem(EnchantedBlocks.GOLDEN_CHALK.get());

		simpleBlockItem(EnchantedBlocks.ALTAR.get(), models().getExistingFile(modLoc("block/altar")));
		horizontalLitWithItem(EnchantedBlocks.WITCH_OVEN.get(), "_on", 180);
		fumeFunnelWithItem(EnchantedBlocks.FUME_FUNNEL.get());
		fumeFunnelWithItem(EnchantedBlocks.FUME_FUNNEL_FILTERED.get());
		horizontalLitWithItem(EnchantedBlocks.DISTILLERY.get(), "", 180);
		complexWithItem(EnchantedBlocks.WITCH_CAULDRON.get());
		complexWithItem(EnchantedBlocks.CHALICE.get());
		complexWithItem(EnchantedBlocks.CHALICE_FILLED.get());
		complexWithItem(EnchantedBlocks.INFINITY_EGG.get(), "block/dragon_egg");
		complexWithItem(EnchantedBlocks.CANDELABRA.get());
		kettleWithItem(EnchantedBlocks.KETTLE.get());
		complexWithItem(EnchantedBlocks.POPPET_SHELF.get());

		cropsWithItem(EnchantedBlocks.BELLADONNA.get(), "crop");
		cropsWithItem(EnchantedBlocks.SNOWBELL.get(), "cross");
		cropsWithItem(EnchantedBlocks.WATER_ARTICHOKE.get(), "crop");
		cropsWithItem(EnchantedBlocks.MANDRAKE.get(), "crop");
		cropsWithItem(EnchantedBlocks.GARLIC.get(), "crop");
		cropsWithItem(EnchantedBlocks.WOLFSBANE.get(), "cross");
		crossWithItem(EnchantedBlocks.GLINT_WEED.get());
		crossWithItem(EnchantedBlocks.EMBER_MOSS.get());
		getVariantBuilder(EnchantedBlocks.BLOOD_POPPY.get()).forAllStates(state -> {
			String name = "block/" + ForgeRegistries.BLOCKS.getKey(EnchantedBlocks.BLOOD_POPPY.get()).getPath();
			if(state.getValue(BloodPoppyBlock.FILLED)) name = name + "_filled";
			return ConfiguredModel.builder()
					.modelFile(models().withExistingParent(name, mcLoc("block/cross"))
							.texture("cross", name)
							.renderType("minecraft:cutout"))
					.build();
		});
		simpleItem(EnchantedBlocks.BLOOD_POPPY.get());
		simpleItem(EnchantedBlocks.SPANISH_MOSS.get());

	}

	private void kettleWithItem(Block block) {
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

	private void chalkWithItem(Block block) {
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
						.modelFile(models().withExistingParent("block/glyph_" + (state.getValue(ChalkCircleBlock.GLYPH)%12), modLoc("block/glyph")).texture("top", "block/glyph_"+(state.getValue(ChalkCircleBlock.GLYPH)%12)))
						.rotationY((state.getValue(ChalkCircleBlock.GLYPH) % 4 * 90))
						.build()
				);
		itemModels()
				.withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), mcLoc(ModelProvider.ITEM_FOLDER + "/generated"))
				.texture("layer0", modLoc(ModelProvider.ITEM_FOLDER + "/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
	}

	private void goldChalkWithItem(Block block) {
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

	private void simpleWithItem(Block block) {
		simpleBlock(block);
		simpleBlockItem(block, models().getExistingFile(modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block).getPath())));
	}

	private void complexWithItem(Block block) {
		ModelFile model = models().getExistingFile(modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
		simpleBlock(block, model);
		simpleBlockItem(block, model);
	}

	private void complexWithItem(Block block, String location) {
		ModelFile model = models().getExistingFile(mcLoc(location));
		simpleBlock(block, model);
		simpleBlockItem(block, model);
	}

	private void slabWithItem(SlabBlock block, Block parent) {
		ResourceLocation path = blockTexture(parent);
		slabBlock(block, path, blockTexture(parent));
		simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
	}

	private void stairsWithItem(StairBlock block, Block parent) {
		stairsBlock(block, blockTexture(parent));
		simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
	}

	private void horizontalLitWithItem(Block block, String onSuffix, int angleOffset) {
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

	private void leafRandomWithItem(Block block, int numVariants) {
		String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
		ConfiguredModel[] models = new ConfiguredModel[numVariants];
		for(int i = 0; i < numVariants; i++) {
			models[i] = new ConfiguredModel(models().getExistingFile(modLoc("block/" + name + "_" + i)));
		}
		getVariantBuilder(block).partialState().setModels(models);
		itemModels().getBuilder(name)
				.parent(models().getExistingFile(modLoc("block/" + name + "_0")));
	}

	private void logWithItem(RotatedPillarBlock block) {
		logBlock(block);
		simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
	}

	private void fenceWithItem(FenceBlock block, ResourceLocation texture) {
		fenceBlock(block, texture);
		ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
		itemModels().getBuilder(key.getPath())
				.parent(models().getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", texture);
	}

	private void fenceGateWithItem(FenceGateBlock block, ResourceLocation texture) {
		fenceGateBlock(block, texture);
		ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
		itemModels().getBuilder(key.getPath()).parent(models().getExistingFile(modLoc("block/" + key.getPath())));
	}

	private void buttonWithItem(ButtonBlock block, ResourceLocation texture) {
		buttonBlock(block, texture);
		ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
		itemModels().getBuilder(key.getPath())
				.parent(models().getExistingFile(mcLoc("block/button_inventory")))
				.texture("texture", texture);;
	}

	private void pressurePlateWithItem(PressurePlateBlock block, ResourceLocation texture) {
		pressurePlateBlock(block, texture);
		ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
		itemModels().getBuilder(key.getPath()).parent(models().getExistingFile(modLoc("block/" + key.getPath())));
	}

	private void cross(Block block) {
		ResourceLocation path = blockTexture(block);
		simpleBlock(block, models().cross(path.toString(), path).renderType(new ResourceLocation("cutout")));
	}

	private void crossWithItem(Block block) {
		cross(block);
		simpleItem(block);
	}

	private void cropsWithItem(CropsBlockAgeFive block, String parent) {
		String path = BlockModelProvider.BLOCK_FOLDER + "/" + ForgeRegistries.BLOCKS.getKey(block).getPath() + "_stage_";
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
						.modelFile(models()
								.withExistingParent(path + state.getValue(CropsBlockAgeFive.AGE_FIVE), mcLoc("block/" + parent))
								.texture(parent, path + state.getValue(CropsBlockAgeFive.AGE_FIVE))
								.renderType(new ResourceLocation("cutout")))
						.build());
	}

}

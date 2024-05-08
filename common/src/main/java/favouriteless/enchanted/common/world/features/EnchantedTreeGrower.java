package favouriteless.enchanted.common.world.features;

import favouriteless.enchanted.Enchanted;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class EnchantedTreeGrower extends AbstractTreeGrower {

	private final ResourceLocation location;

	public EnchantedTreeGrower(String id) {
		location = Enchanted.location(id);
	}

	@Override
	public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
		ConfiguredFeature<?, ?> feature = level.registryAccess().registry(Registries.CONFIGURED_FEATURE).orElseThrow().get(location);
		if(feature == null)
			return false;

		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
		if (feature.place(level, chunkGenerator, random, pos))
			return true;
		else {
			level.setBlock(pos, state, 4);
			return false;
		}
	}

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
		return null;
	}

}

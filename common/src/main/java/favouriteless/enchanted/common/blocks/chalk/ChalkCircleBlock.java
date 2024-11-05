package favouriteless.enchanted.common.blocks.chalk;

import favouriteless.enchanted.common.Enchanted;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Random;

public class ChalkCircleBlock extends AbstractChalkBlock {

    // Regular chalk types - white red purple
    public static final IntegerProperty GLYPH = IntegerProperty.create("glyph", 0, 47);
    private final SimpleParticleType particleType;

    public ChalkCircleBlock(SimpleParticleType particleType) {
        super();
        this.particleType = particleType;
        this.registerDefaultState(getStateDefinition().any().setValue(GLYPH, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLYPH);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getRandomState();
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(this.particleType != null) {
            if(random.nextInt(6) == 1) {
                double dx = random.nextDouble();
                double dz = random.nextDouble();

                level.addParticle(particleType, pos.getX() + dx, pos.getY() + 0.1D, pos.getZ() + dz, 0, 0, 0);
            }
        }
    }

    @Override
    public BlockState getRandomState() {
        return defaultBlockState().setValue(GLYPH, Enchanted.RANDOM.nextInt(48));
    }

}
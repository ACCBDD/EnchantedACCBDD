package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.api.Vec2i;
import net.favouriteless.enchanted.client.particles.types.DoubleOptions;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;

public class TransposeBlocksRite extends Rite {

    private final TagKey<Block> tag;

    public TransposeBlocksRite(BaseRiteParams baseParams, RiteParams params, TagKey<Block> tag) {
        super(baseParams, params);
        this.tag = tag;
    }

    @Override
    protected boolean onTick(RiteParams params) {
        Vec2i offset = getType().getInteriorPoints().get(params.ticks()-1);

        for(int i = 0; i < pos.getY() - level.getMinBuildHeight(); i++) {
            BlockPos pos = this.pos.offset(offset.x(), -i, offset.y()); // Steps down 1 block per iteration.

            BlockState state = level.getBlockState(pos);
            if(state.is(tag)) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, Context.of(null, state));
                Block.dropResources(state, level, this.pos, null, null, ItemStack.EMPTY);
                level.playSound(null, this.pos, SoundEvents.COPPER_BREAK, SoundSource.MASTER, 1.0F, 1.0F);
                randomParticles(ParticleTypes.WITCH);
            }
        }

        if(params.ticks() % 20 == 0) {
            level.sendParticles(new DoubleOptions(EParticleTypes.TRANSPOSITION_IRON_SEED.get(), getType().getRadius()),
                    pos.getX()+0.5D, pos.getY()-0.1D, pos.getZ()+0.5D,
                    1, 0, 0, 0, 0);
        }

        return params.ticks() < getType().getInteriorPoints().size(); // Stop executing when run out of points.
    }

}

package net.favouriteless.enchanted.common.blocks.entity;

import net.favouriteless.enchanted.client.particles.types.ColourOptions;
import net.favouriteless.enchanted.common.blocks.cauldrons.KettleBlock;
import net.favouriteless.enchanted.common.init.registry.EnchantedBlockEntityTypes;
import net.favouriteless.enchanted.common.init.registry.EParticleTypes;
import net.favouriteless.enchanted.common.init.registry.ERecipeTypes;
import net.favouriteless.enchanted.common.recipes.KettleRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.block.state.BlockState;

public class KettleBlockEntity extends CauldronBlockEntity<KettleRecipe> {

    public KettleBlockEntity(BlockPos pos, BlockState state) {
        super(EnchantedBlockEntityTypes.KETTLE.get(), pos, state, 1, 160);
    }

    @Override
    public double getWaterStartY(BlockState state) {
        return state.getValue(KettleBlock.TYPE) == 1 ? 0.1875D : 0.0625D;
    }

    @Override
    public double getWaterMaxHeight() {
        return 0.25D;
    }

    @Override
    public double getWaterWidth() {
        return 0.375D;
    }

    @Override
    public void handleCookParticles(long time) {
        if(Math.random() > 0.5D) {
            double dx = worldPosition.getX() + Math.random();
            double dy = worldPosition.getY() + Math.random();
            double dz = worldPosition.getZ() + Math.random();

            level.addParticle(new ColourOptions(EParticleTypes.KETTLE_COOK.get(), FastColor.ARGB32.color(1, getRed(time), getGreen(time), getBlue(time))), dx, dy, dz, 0D, 0D, 0D);
        }
    }

    @Override
    protected void matchRecipes() {
        if (level != null)
            setPotentialRecipes(level.getRecipeManager().getRecipesFor(ERecipeTypes.KETTLE.get(), this, level));
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.enchanted.kettle");
    }

}

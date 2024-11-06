package net.favouriteless.enchanted.common.blocks.entity;

import net.favouriteless.enchanted.common.blocks.EBlocks;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.recipes.ERecipeTypes;
import net.favouriteless.enchanted.common.recipes.WitchCauldronRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

public class WitchCauldronBlockEntity extends CauldronBlockEntity<WitchCauldronRecipe> {

    public WitchCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(EBlockEntityTypes.WITCH_CAULDRON.get(), pos, state, 3, 200);
    }

    @Override
    public double getWaterStartY(BlockState state) {
        return 0.0625D;
    }

    @Override
    public double getWaterMaxHeight() {
        return 0.4375D;
    }

    @Override
    public double getWaterWidth() {
        return 0.875D;
    }

    @Override
    public void handleCookParticles(long time) {
        double dx = worldPosition.getX() + 0.5D;
        double dy = worldPosition.getY() + getWaterY(EBlocks.WITCH_CAULDRON.get().defaultBlockState());
        double dz = worldPosition.getZ() + 0.5D;

        level.addParticle(new SimpleColouredData(EParticleTypes.CAULDRON_COOK.get(), getRed(time), getGreen(time), getBlue(time)), dx, dy, dz, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void matchRecipes() {
        if (level != null)
            setPotentialRecipes(level.getRecipeManager().getRecipesFor(ERecipeTypes.WITCH_CAULDRON.get(), this, level));
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.witch_cauldron");
    }

}

package favouriteless.enchanted.common.blocks.entity;

import favouriteless.enchanted.client.particles.types.ColourOptions;
import favouriteless.enchanted.common.init.registry.EnchantedBlockEntityTypes;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.common.init.registry.EParticleTypes;
import favouriteless.enchanted.common.init.registry.ERecipeTypes;
import favouriteless.enchanted.common.recipes.WitchCauldronRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.block.state.BlockState;

public class WitchCauldronBlockEntity extends CauldronBlockEntity<WitchCauldronRecipe> {

    public WitchCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(EnchantedBlockEntityTypes.WITCH_CAULDRON.get(), pos, state, 3, 200);
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
        double dy = worldPosition.getY() + getWaterY(EnchantedBlocks.WITCH_CAULDRON.get().defaultBlockState());
        double dz = worldPosition.getZ() + 0.5D;

        level.addParticle(new ColourOptions(EParticleTypes.CAULDRON_COOK.get(), FastColor.ARGB32.color(1, getRed(time), getGreen(time), getBlue(time))), dx, dy, dz, 0.0D, 0.0D, 0.0D);
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

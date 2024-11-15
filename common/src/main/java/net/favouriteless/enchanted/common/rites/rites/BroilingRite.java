package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.CommonConfig;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.init.ETags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class BroilingRite extends Rite {

    public static final float CIRCLE_RADIUS = 4.0f;
    private static final float RADIUS_SQR = CIRCLE_RADIUS * CIRCLE_RADIUS;

    private final CachedCheck<SingleRecipeInput, SmeltingRecipe> smeltCheck;

    public BroilingRite(BaseRiteParams params) {
        super(params);
        smeltCheck = RecipeManager.createCheck(RecipeType.SMELTING);
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                             @Nullable UUID targetUUID, List<ItemStack> consumedItems) {

        if(ticks % 5 != 0)
            return true;

        AABB bounds = new AABB(
                pos.getX() - CIRCLE_RADIUS, pos.getY(), pos.getZ() - CIRCLE_RADIUS,
                pos.getX() + CIRCLE_RADIUS + 1, pos.getY() + 1, pos.getZ() + CIRCLE_RADIUS + 1
        );

        List<ItemEntity> toCook = level.getEntitiesOfClass(ItemEntity.class, bounds, e ->
                e.position().subtract(pos.getCenter()).lengthSqr() < RADIUS_SQR && e.getItem().is(ETags.Items.RAW_FOODS));

        if(toCook.isEmpty())
            return false;

        ItemEntity item = toCook.getFirst();
        SingleRecipeInput input = new SingleRecipeInput(item.getItem());
        RecipeHolder<SmeltingRecipe> recipe = smeltCheck.getRecipeFor(input, level).orElse(null);

        if(recipe == null)
            return false;

        int total = item.getItem().getCount();
        int burned = 0;
        for(int i = 0; i < total; i++) {
            if(Math.random() < CommonConfig.INSTANCE.broilingBurnChance.get())
                burned++;
        }


        if(burned < total) {
            ItemStack out = recipe.value().assemble(input, level.registryAccess());
            out.setCount(total - burned);
            level.addFreshEntity(new ItemEntity(level, item.getX(), item.getY(), item.getZ(), out));
        }
        if(burned > 0) {
            int count = burned / 16; // int division, no remainder
            if(Math.random() < (burned % 16) / 16f)
                count += 1;
            level.addFreshEntity(new ItemEntity(level, item.getX(), item.getY(), item.getZ(), new ItemStack(Items.CHARCOAL, count)));
        }

        level.playSound(null, item.getX(), item.getY(), item.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.MASTER, 1.0F, 1.0F);
        level.sendParticles(ParticleTypes.SMALL_FLAME, item.getX(), item.getY(), item.getZ(), 25, 0.2d, 0.2d, 0.2d, 0);
        level.sendParticles(EParticleTypes.BROILING_SEED.get(), pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, 1, 0, 0, 0, 0);
        item.discard();

        return true;
    }

}

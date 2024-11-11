package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DuplicateItemRite extends Rite {

    private final Item targetItem;
    private final int count;

    public DuplicateItemRite(BaseRiteParams params, Item targetItem, int count) {
        super(params);
        this.targetItem = targetItem;
        this.count = count;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        for(ItemStack stack : consumedItems) {
            if(stack.getItem() == targetItem) {
                ItemStack copy = stack.copy();
                copy.setCount(count);
                ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, copy);
                level.addFreshEntity(itemEntity);
                break;
            }
        }
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        randomParticles(ParticleTypes.WITCH);
        return false;
    }
}

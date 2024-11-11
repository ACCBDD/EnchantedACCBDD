package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.common.items.component.EDataComponents;
import net.favouriteless.enchanted.common.items.component.EntityRefData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EntityBoundCreateItemRite extends Rite {

    private final List<ItemStack> items;

    public EntityBoundCreateItemRite(BaseRiteParams params, List<ItemStack> items) {
        super(params);
        this.items = items;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        EntityRefData ref = null;

        for(ItemStack stack : consumedItems) {
            if(stack.has(EDataComponents.ENTITY_REF.get())) {
                ref = stack.get(EDataComponents.ENTITY_REF.get());
                break;
            }
        }

        for(ItemStack stack : items) {
            stack.set(EDataComponents.ENTITY_REF.get(), ref);
            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack.copy());
            level.addFreshEntity(itemEntity);
        }
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        randomParticles(ParticleTypes.WITCH);
        return false;
    }
}

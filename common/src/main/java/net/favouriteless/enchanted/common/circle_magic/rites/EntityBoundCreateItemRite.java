package net.favouriteless.enchanted.common.circle_magic.rites;

import net.favouriteless.enchanted.common.items.TaglockFilledItem;
import net.favouriteless.enchanted.common.util.WaystoneHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.UUID;

public class EntityBoundCreateItemRite extends Rite {
    //todo: fix
    private final List<ItemStack> items;

    public EntityBoundCreateItemRite(BaseRiteParams baseParams, RiteParams params, List<ItemStack> items) {
        super(baseParams, params);
        this.items = items;
    }

    @Override
    protected boolean onStart(RiteParams params) {
        UUID ref = null;
        String name = null;

        for (ItemStack stack : params.consumedItems) {
            if (stack.getItem() instanceof TaglockFilledItem) {
                if (stack.getOrCreateTag().contains(TaglockFilledItem.TARGET_TAG)) {
                    ref = NbtUtils.loadUUID(stack.getTag().get(TaglockFilledItem.TARGET_TAG));
                    name = level.getEntity(ref).getName().getString();
                    break;
                }
            }
        }

        for (ItemStack stack : items) {
            WaystoneHelper.bind(stack, ref, name);
            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack.copy());
            level.addFreshEntity(itemEntity);
        }
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        randomParticles(ParticleTypes.WITCH);
        return false;
    }

}

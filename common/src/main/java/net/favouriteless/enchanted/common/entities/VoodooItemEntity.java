package net.favouriteless.enchanted.common.entities;

import net.favouriteless.enchanted.common.poppet.PoppetUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class VoodooItemEntity extends ItemEntity {

    private int underWaterTicks;
    private int health;

    public VoodooItemEntity(EntityType<? extends ItemEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void setItem(ItemStack stack) {
        if(stack.isDamageableItem())
            this.health = stack.getMaxDamage() - stack.getDamageValue();
        super.setItem(stack);
    }

    @Override
    public void tick() {
        super.tick();
        if(level() instanceof ServerLevel level && PoppetUtils.isBound(getItem())) {
            ItemStack item = getItem();
            ServerPlayer target = PoppetUtils.getBoundPlayer(item, level);

            if(target != null) {
                if(isInWaterOrBubble())
                    ++underWaterTicks;
                else
                    underWaterTicks = 0;

                if(underWaterTicks > 20 && PoppetUtils.tryVoodooPlayer(target, (ServerPlayer)getOwner(), item) && target.hurt(level.damageSources().drown(), 1.0F))
                    hurt(1);

                if(isInLava() && PoppetUtils.tryVoodooPlayer(target, (ServerPlayer)getOwner(), item) && target.hurt(level.damageSources().lava(), 4.0F)) {
                    target.setRemainingFireTicks(15*20);
                    hurt(4);
                }
            }
        }
    }

    public void hurt(int amount) {
        ItemStack item = getItem();

        if(level().isClientSide)
            return;;

        health = health - amount;
        item.setDamageValue(item.getMaxDamage() - health);
        if (health <= 0) {
            getItem().onDestroyed(this);
            discard();
        }
    }

}

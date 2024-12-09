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
                    hurt(level.damageSources().generic(), 1);

                if(isInLava() && PoppetUtils.tryVoodooPlayer(target, (ServerPlayer)getOwner(), item) && target.hurt(level.damageSources().lava(), 4.0F)) {
                    target.setSecondsOnFire(15);
                    hurt(level.damageSources().generic(), 4);
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        ItemStack item = getItem();

        if(isInvulnerableTo(source))
            return false;
        else if(!item.isEmpty() && item.is(Items.NETHER_STAR) && source.is(DamageTypeTags.IS_EXPLOSION))
            return false;
        else if(!item.getItem().canBeHurtBy(source))
            return false;
        else if(level().isClientSide)
            return true;
        else {
            markHurt();
            health = (int)(health - amount);
            gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
            if (health <= 0) {
                getItem().onDestroyed(this);
                discard();
            }
            item.setDamageValue(item.getMaxDamage() - health);
            return true;
        }
    }

}

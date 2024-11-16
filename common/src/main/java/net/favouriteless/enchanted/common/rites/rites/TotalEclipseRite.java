package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class TotalEclipseRite extends Rite {

    public TotalEclipseRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected boolean onStart(RiteParams params) {
        level.setDayTime(18000);
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        return true;
    }

}

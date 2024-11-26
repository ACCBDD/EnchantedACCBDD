package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.api.familiars.FamiliarSavedData;
import favouriteless.enchanted.api.familiars.IFamiliarEntry;
import favouriteless.enchanted.common.curses.CurseManager;
import favouriteless.enchanted.common.curses.CurseType;
import favouriteless.enchanted.common.init.registry.EParticleTypes;
import favouriteless.enchanted.common.init.registry.EnchantedSoundEvents;
import favouriteless.enchanted.common.init.registry.FamiliarTypes;
import net.minecraft.sounds.SoundSource;

public class ApplyCurseRite extends Rite {

    private final CurseType<?> curse;

    public ApplyCurseRite(BaseRiteParams baseParams, RiteParams params, CurseType<?> curse) {
        super(baseParams, params);
        this.curse = curse;
    }

    @Override
    protected boolean onStart(RiteParams params) {
        if (params.target == null || params.caster == null)
            return cancel();

        int casterLevel = 0;

        IFamiliarEntry familiarEntry = FamiliarSavedData.get(level).getEntry(params.caster);

        if (familiarEntry != null) {
            if (!familiarEntry.isDismissed() && familiarEntry.getType() == FamiliarTypes.CAT)
                casterLevel++;
        }

        level.sendParticles(EParticleTypes.CURSE_SEED.get(), pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        level.playSound(null, pos, EnchantedSoundEvents.CURSE_CAST.get(), SoundSource.MASTER, 1.5F, 1.0F);
        CurseManager.createCurse(level, curse, params.target, params.caster, casterLevel);
        return false;
    }

}

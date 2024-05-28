package favouriteless.enchanted.api.rites;

import favouriteless.enchanted.api.curses.Curse;
import favouriteless.enchanted.api.familiars.FamiliarSavedData;
import favouriteless.enchanted.api.familiars.IFamiliarEntry;
import favouriteless.enchanted.common.curses.CurseManager;
import favouriteless.enchanted.common.curses.CurseType;
import favouriteless.enchanted.common.init.registry.EnchantedParticleTypes;
import favouriteless.enchanted.common.init.registry.EnchantedSoundEvents;
import favouriteless.enchanted.common.init.registry.FamiliarTypes;
import favouriteless.enchanted.common.rites.RiteType;
import favouriteless.enchanted.common.rites.curse.RiteCurseMisfortune;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

/**
 * Simple {@link AbstractRite} implementation for creating a {@link Curse} and applying it to a {@link Player}.
 *
 * <p><strong>IMPORTANT:</strong> Rites implementing this should add a filled taglock to their requirements so the target's UUID can
 * be grabbed.</p>
 *
 * <p>See {@link RiteCurseMisfortune} for an example implementation of {@link AbstractCurseRite}.</p>
 */
public abstract class AbstractCurseRite extends AbstractRite {

    private final CurseType<?> curseType;

    public AbstractCurseRite(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster, int power, CurseType<?> curseType) {
        super(type, level, pos, caster, power, 0);
        this.curseType = curseType;
    }

    @Override
    protected void execute() {
        if(getTargetUUID() == null)
            cancel();
        else {
            int casterLevel = 0;

            IFamiliarEntry familiarEntry = FamiliarSavedData.get(getLevel()).getEntry(getCasterUUID());

            if(familiarEntry != null) {
                if(!familiarEntry.isDismissed() && familiarEntry.getType() == FamiliarTypes.CAT)
                    casterLevel++;
            }

            BlockPos pos = getPos();
            getLevel().sendParticles(EnchantedParticleTypes.CURSE_SEED.get(), pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            getLevel().playSound(null, pos, EnchantedSoundEvents.CURSE_CAST.get(), SoundSource.MASTER, 1.5F, 1.0F);
            CurseManager.createCurse(getLevel(), curseType, getTargetUUID(), getCasterUUID(), casterLevel);
            stopExecuting();
        }
    }

}

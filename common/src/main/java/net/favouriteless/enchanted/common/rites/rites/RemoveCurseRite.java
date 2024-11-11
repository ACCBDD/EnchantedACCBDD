package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.api.curses.Curse;
import net.favouriteless.enchanted.api.curses.CurseSavedData;
import net.favouriteless.enchanted.api.familiars.FamiliarSavedData;
import net.favouriteless.enchanted.api.familiars.IFamiliarEntry;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.curses.CurseManager;
import net.favouriteless.enchanted.common.curses.CurseType;
import net.favouriteless.enchanted.common.familiars.FamiliarTypes;
import net.favouriteless.enchanted.common.init.EParticleTypes;
import net.favouriteless.enchanted.common.sounds.ESoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemoveCurseRite extends Rite {

    public static final int RAISE = 300;
    public static final int START_SOUND = 190;

    private final CurseType<?> curse;

    public RemoveCurseRite(BaseRiteParams params, CurseType<?> curse) {
        super(params);
        this.curse = curse;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        if(this.targetUUID == null)
            return cancel();

        level.sendParticles(EParticleTypes.REMOVE_CURSE_SEED.get(), pos.getX() + 0.5d, pos.getY() + 2.5d, pos.getZ() + 0.5d, 1, 0, 0, 0, 0);

        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        if(ticks == START_SOUND) {
            level.playSound(null, pos, ESoundEvents.REMOVE_CURSE.get(), SoundSource.MASTER, 1.0F, 1.0F);
            return true;
        }

        if(ticks == RAISE) {
            List<Curse> curses = CurseSavedData.get(level).entries.get(targetUUID);

            if(curses == null)
                return false;

            int casterLevel = 0;

            FamiliarSavedData data = FamiliarSavedData.get(level);
            IFamiliarEntry familiarEntry = data.getEntry(casterUUID);
            if(familiarEntry != null && !familiarEntry.isDismissed() && familiarEntry.getType() == FamiliarTypes.CAT)
                casterLevel++;

            for(Curse curse : curses) {
                if(curse.getType() == this.curse) {
                    int diff = casterLevel - curse.getLevel();

                    double cureChance = 1.0D + (diff * 0.2D); // If the caster is equal level, there is an 100% chance the curse will be cured.

                    if(Math.random() < cureChance)
                        CurseManager.removeCurse(level, curse);
                    else if(curse.getLevel() < CurseManager.MAX_LEVEL)
                        curse.setLevel(curse.getLevel() + 1);
                    break;
                }
            }
            return false;
        }
        return true;
    }

}

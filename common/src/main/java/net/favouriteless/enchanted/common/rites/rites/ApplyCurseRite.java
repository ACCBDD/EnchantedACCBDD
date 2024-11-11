package net.favouriteless.enchanted.common.rites.rites;

import net.favouriteless.enchanted.api.familiars.FamiliarSavedData;
import net.favouriteless.enchanted.api.familiars.IFamiliarEntry;
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

public class ApplyCurseRite extends Rite {

    private final CurseType<?> curse;

    public ApplyCurseRite(BaseRiteParams params, CurseType<?> curse) {
        super(params);
        this.curse = curse;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        if(this.targetUUID == null || this.casterUUID == null)
            return cancel();

        int casterLevel = 0;

        IFamiliarEntry familiarEntry = FamiliarSavedData.get(level).getEntry(this.casterUUID);

        if(familiarEntry != null) {
            if(!familiarEntry.isDismissed() && familiarEntry.getType() == FamiliarTypes.CAT)
                casterLevel++;
        }

        level.sendParticles(EParticleTypes.CURSE_SEED.get(), pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        level.playSound(null, pos, ESoundEvents.CURSE_CAST.get(), SoundSource.MASTER, 1.5F, 1.0F);
        CurseManager.createCurse(level, curse, targetUUID, casterUUID, casterLevel);
        return false;
    }
}

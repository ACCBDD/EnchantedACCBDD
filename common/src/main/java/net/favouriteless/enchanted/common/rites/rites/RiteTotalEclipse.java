package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class RiteTotalEclipse extends Rite {

    public RiteTotalEclipse(ResourceLocation id, int tickPower, ServerLevel level, BlockPos pos, UUID caster, UUID target) {
        super(id, tickPower, level, pos, caster, target);
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target) {
        level.setDayTime(18000);
        level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        return true;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster, @Nullable ServerPlayer target) {
        return true;
    }

}

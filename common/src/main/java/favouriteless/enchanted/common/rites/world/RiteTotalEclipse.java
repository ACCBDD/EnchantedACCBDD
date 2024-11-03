package favouriteless.enchanted.common.rites.world;

import favouriteless.enchanted.api.rites.AbstractRite;
import favouriteless.enchanted.common.CommonConfig;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class RiteTotalEclipse extends AbstractRite {

    private static long LAST_USE_TIME = System.currentTimeMillis();

    public RiteTotalEclipse(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster);
    }

    @Override
    public void execute() {
        ServerLevel level = getLevel();
        level.setDayTime(18000);
        level.playSound(null, getPos(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.MASTER, 0.5F, 1.0F);
        LAST_USE_TIME = System.currentTimeMillis();
        stopExecuting();
    }

    @Override
    protected boolean checkAdditional() {
        if(System.currentTimeMillis() > LAST_USE_TIME + CommonConfig.TOTAL_ECLIPSE_COOLDOWN.get() * 1000L)
            return true;

        Player caster = getLevel().getServer().getPlayerList().getPlayer(getCasterUUID());
        caster.displayClientMessage(Component.literal("The moon is not ready to be called forth.").withStyle(ChatFormatting.RED), false);
        return false;
    }

}

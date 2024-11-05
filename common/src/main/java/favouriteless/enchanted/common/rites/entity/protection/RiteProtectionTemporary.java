package favouriteless.enchanted.common.rites.entity.protection;

import favouriteless.enchanted.common.init.registry.EBlocks;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class RiteProtectionTemporary extends RiteProtection {

    public static final int DURATION = 60 * 20; // 60 seconds duration

    public RiteProtectionTemporary(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, 4, EBlocks.PROTECTION_BARRIER_TEMPORARY.get()); // Power, power per tick, radius
    }

    @Override
    protected void onTick() {
        super.onTick();
        if(ticks > DURATION)
            stopExecuting();
    }

}

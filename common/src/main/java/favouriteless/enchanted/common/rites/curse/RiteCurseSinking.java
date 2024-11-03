package favouriteless.enchanted.common.rites.curse;

import favouriteless.enchanted.api.rites.AbstractCurseRite;
import favouriteless.enchanted.common.init.registry.CurseTypes;
import favouriteless.enchanted.common.rites.RiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class RiteCurseSinking extends AbstractCurseRite {

    public RiteCurseSinking(RiteType<?> type, ServerLevel level, BlockPos pos, UUID caster) {
        super(type, level, pos, caster, CurseTypes.SINKING); // Power, curse type
    }

    @Override
    protected boolean checkAdditional() {
        return getLevel().isRaining() && getLevel().isThundering();
    }

}

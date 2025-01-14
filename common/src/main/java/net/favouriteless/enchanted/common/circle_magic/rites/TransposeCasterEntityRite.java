package net.favouriteless.enchanted.common.circle_magic.rites;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class TransposeCasterEntityRite extends TransposeEntityRite {

    public TransposeCasterEntityRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected Entity getTransposee(RiteParams params) {
        return findEntity(params.caster);
    }

    @Override
    protected void findTargetLocation(RiteParams params) {
        Entity target = findEntity(params.target);
        if(target != null) {
            targetLevel = (ServerLevel)target.level();
            targetPos = target.blockPosition();
        }
    }

}

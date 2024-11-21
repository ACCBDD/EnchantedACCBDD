package favouriteless.enchanted.common.circle_magic.rites;

import net.minecraft.world.entity.Entity;

public class SummonEntityRite extends TransposeEntityRite {

    public SummonEntityRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected Entity getTransposee(RiteParams params) {
        return findEntity(params.target);
    }

}

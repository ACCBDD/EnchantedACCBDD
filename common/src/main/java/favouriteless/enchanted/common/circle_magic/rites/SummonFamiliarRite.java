package favouriteless.enchanted.common.circle_magic.rites;

import favouriteless.enchanted.api.familiars.FamiliarSavedData;
import favouriteless.enchanted.api.familiars.IFamiliarEntry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.Vec3;

public class SummonFamiliarRite extends Rite {

    public SummonFamiliarRite(BaseRiteParams baseParams, RiteParams params) {
        super(baseParams, params);
    }

    @Override
    protected boolean onStart(RiteParams params) {
        IFamiliarEntry entry = FamiliarSavedData.get(getLevel()).getEntry(params.caster);
        if (entry == null)
            return cancel();

        Vec3 center = pos.getCenter().subtract(0, 0.49D, 0);

        Entity target = findEntity(entry.getUUID());

        if (target == null) { // If the target isn't loaded, create a new one. Old one should discard itself on load.
            target = entry.getType().getTypeOut().create(level);
            target.load(entry.getNbt());
            target.setPos(center);

            level.addFreshEntity(target);
            ((TamableAnimal) target).setPersistenceRequired();
            entry.setUUID(target.getUUID());
        }

        if (target.level() != level)
            target.changeDimension(level);
        else
            target.teleportTo(center.x, center.y, center.z);

        entry.setDismissed(false);
        level.playSound(null, center.x, center.y, center.z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL, 1.0F, 1.0F);
        randomParticles(ParticleTypes.PORTAL);

        return false;
    }

}

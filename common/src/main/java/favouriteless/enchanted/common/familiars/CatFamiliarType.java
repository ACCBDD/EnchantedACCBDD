package favouriteless.enchanted.common.familiars;

import favouriteless.enchanted.common.entities.FamiliarCat;
import favouriteless.enchanted.api.familiars.FamiliarType;
import favouriteless.enchanted.common.init.registry.EEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;

public class CatFamiliarType extends FamiliarType<Cat, FamiliarCat> {

	public CatFamiliarType(ResourceLocation id) {
		super(id, () -> EntityType.CAT, EEntityTypes.FAMILIAR_CAT);
	}

	@Override
	public FamiliarCat create(Cat from) {
		FamiliarCat familiar = EEntityTypes.FAMILIAR_CAT.get().create(from.level());
		familiar.setVariant(from.getVariant());
		familiar.setCollarColor(from.getCollarColor());
		return familiar;
	}

}

package favouriteless.enchanted.common.items;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * We have to create this class and implement it on forge/fabric individually because this version of GeckoLib is
 * incompatible with Multiloader projects, you get compile errors no matter which source you use for common.
 */
public abstract class NonAnimatedArmorItem extends ArmorItem implements GeoItem {

	private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
	protected final String assetPath;

	public NonAnimatedArmorItem(ArmorMaterials material, ArmorItem.Type type, String assetPath, Properties properties) {
		super(material, type, properties);
		this.assetPath = assetPath;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return animationCache;
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) { }

}

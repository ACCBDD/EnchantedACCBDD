package com.favouriteless.enchanted.common.items;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EarmuffsItem extends ArmorItem implements GeoItem {

	private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

	public EarmuffsItem(Properties properties) {
		super(ArmorMaterials.LEATHER, Type.HELMET, properties);
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return animationCache;
	}

}

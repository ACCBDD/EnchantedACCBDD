package favouriteless.enchanted.common.effects;

import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.function.Supplier;

public class EEffects {

	public static final Supplier<MobEffect> DROWN_RESISTANCE = register("drown_resistance", () -> new SimpleEffect(MobEffectCategory.BENEFICIAL, 0x2E5299));
	public static final Supplier<MobEffect> FALL_RESISTANCE = register("fall_resistance", () -> new SimpleEffect(MobEffectCategory.BENEFICIAL, 0x70503A));
	public static final Supplier<MobEffect> MAGIC_RESISTANCE = register("magic_resistance", () -> new SimpleEffect(MobEffectCategory.BENEFICIAL, 0xAC4AED));

	private static <T extends MobEffect> Supplier<T> register(String name, Supplier<T> effectSupplier) {
		return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.MOB_EFFECT, name, effectSupplier);
	}

	public static boolean isMagic(DamageSource source) {
		return source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC);
	}

	public static void load() {} // Method which exists purely to load the class.

}

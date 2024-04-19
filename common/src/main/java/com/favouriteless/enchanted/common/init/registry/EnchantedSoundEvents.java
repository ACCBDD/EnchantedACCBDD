package com.favouriteless.enchanted.common.init.registry;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class EnchantedSoundEvents {

	public static Supplier<SoundEvent> BIND_FAMILIAR = register("bind_familiar", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("bind_familiar")));
	public static Supplier<SoundEvent> BROOM_SWEEP = register("broom_sweep", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("broom_sweep")));
	public static Supplier<SoundEvent> CAULDRON_BUBBLING = register("cauldron_bubbling", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("cauldron_bubbling")));
	public static Supplier<SoundEvent> CHALK_WRITE = register("chalk_write", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("chalk_write")));
	public static Supplier<SoundEvent> CURSE_CAST = register("curse_cast", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("curse_cast")));
	public static Supplier<SoundEvent> CURSE_WHISPER = register("curse_whisper", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("curse_whisper")));
	public static Supplier<SoundEvent> REMOVE_CURSE = register("remove_curse", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("remove_curse")));
	public static Supplier<SoundEvent> SILENT = register("silent", () -> SoundEvent.createVariableRangeEvent(Enchanted.location("silent"))); // Only exists as a dummy sound to avoid nulls

	private static <T extends SoundEvent> Supplier<T> register(String name, Supplier<T> soundSupplier) {
		return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.SOUND_EVENT, name, soundSupplier);
	}

	public static void load() {} // Method which exists purely to load the class.


}

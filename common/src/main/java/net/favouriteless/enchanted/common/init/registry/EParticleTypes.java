package net.favouriteless.enchanted.common.init.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.client.particles.types.*;
import net.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class EParticleTypes {

    public static final Supplier<ParticleType<DelayedPosOptions>> BIND_FAMILIAR = register("bind_familiar", false, DelayedPosOptions.DESERIALIZER, DelayedPosOptions::codec);
    public static final Supplier<ESimpleParticleType> BIND_FAMILIAR_SEED = register("bind_familiar_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<ColouredCircleOptions>> BLIGHT = register("blight", false, ColouredCircleOptions.DESERIALIZER, ColouredCircleOptions::codec);
    public static final Supplier<ESimpleParticleType> BLIGHT_SEED = register("blight_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<ColourOptions>> BOILING = register("boiling", false, ColourOptions.DESERIALIZER, ColourOptions::codec);
    public static final Supplier<ESimpleParticleType> BROILING_SEED = register("broiling_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<ColourOptions>> CAULDRON_BREW = register("cauldron_brew", false, ColourOptions.DESERIALIZER, ColourOptions::codec);
    public static final Supplier<ParticleType<ColourOptions>> CAULDRON_COOK = register("cauldron_cook", false, ColourOptions.DESERIALIZER, ColourOptions::codec);
    public static final Supplier<ParticleType<ColouredCircleOptions>> CIRCLE_MAGIC = register("circle_magic", false, ColouredCircleOptions.DESERIALIZER, ColouredCircleOptions::codec);
    public static final Supplier<ESimpleParticleType> CURSE_SEED = register("curse_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<ColouredCircleOptions>> FERTILITY = register("fertility", false, ColouredCircleOptions.DESERIALIZER, ColouredCircleOptions::codec);
    public static final Supplier<ESimpleParticleType> FERTILITY_SEED = register("fertility_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ESimpleParticleType> IMPRISONMENT_CAGE = register("imprisonment_cage", () -> new ESimpleParticleType(false));
    public static final Supplier<ESimpleParticleType> IMPRISONMENT_CAGE_SEED = register("imprisonment_cage_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<ColourOptions>> KETTLE_COOK = register("kettle_cook", false, ColourOptions.DESERIALIZER, ColourOptions::codec);
    public static final Supplier<ParticleType<TwoColourOptions>> POPPET = register("poppet", false, TwoColourOptions.DESERIALIZER, TwoColourOptions::codec);
    public static final Supplier<ESimpleParticleType> PROTECTION = register("protection", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<DoubleOptions>> PROTECTION_SEED = register("protection_seed", false, DoubleOptions.DESERIALIZER, DoubleOptions::codec);
    public static final Supplier<ParticleType<DelayedPosOptions>> REMOVE_CURSE = register("remove_curse", false, DelayedPosOptions.DESERIALIZER, DelayedPosOptions::codec);
    public static final Supplier<ESimpleParticleType> REMOVE_CURSE_SEED = register("remove_curse_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<DelayedPosOptions>> SKY_WRATH = register("sky_wrath", false, DelayedPosOptions.DESERIALIZER, DelayedPosOptions::codec);
    public static final Supplier<ESimpleParticleType> SKY_WRATH_SEED = register("sky_wrath_seed", () -> new ESimpleParticleType(false));
    public static final Supplier<ParticleType<DoubleOptions>> TRANSPOSITION_IRON_SEED = register("transposition_iron_seed", false, DoubleOptions.DESERIALIZER, DoubleOptions::codec);

    private static <T extends ParticleType<?>> Supplier<T> register(String name, Supplier<T> particleTypeSupplier) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.PARTICLE_TYPE, name, particleTypeSupplier);
    }

    private static <T extends ParticleOptions> Supplier<ParticleType<T>> register(String pKey, boolean override, ParticleOptions.Deserializer<T> pDeserializer, final Function<ParticleType<T>, MapCodec<T>> pCodecFactory) {
        return CommonServices.COMMON_REGISTRY.register(
            BuiltInRegistries.PARTICLE_TYPE,
            pKey,
            () -> new ParticleType<>(override, pDeserializer) {
                public Codec<T> codec() {
                    return pCodecFactory.apply(this).codec();
                }
            }
        );
    }

    public static void load() {} // Method which exists purely to load the class.

}

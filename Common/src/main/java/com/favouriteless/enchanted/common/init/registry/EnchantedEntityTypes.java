package com.favouriteless.enchanted.common.init.registry;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.entities.*;
import com.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class EnchantedEntityTypes {

    public static final Supplier<EntityType<Mandrake>> MANDRAKE = register("mandrake", () -> Builder.of(Mandrake::new, MobCategory.MONSTER)
            .sized(0.4F, 0.7F).build(Enchanted.location("mandrake").toString()));

    public static final Supplier<EntityType<Broomstick>> BROOMSTICK = register("broomstick", () -> Builder.of(Broomstick::new, MobCategory.MISC)
            .sized(1.0F, 1.0F).clientTrackingRange(10).build(Enchanted.location("broomstick").toString()));

    public static final Supplier<EntityType<ThrowableBrew>> THROWABLE_BREW = register("throwable_brew", () -> Builder.of(ThrowableBrew::new, MobCategory.MISC)
            .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(Enchanted.location("throwable_brew").toString()));

    public static final Supplier<EntityType<FamiliarCat>> FAMILIAR_CAT = register("familiar_cat", () -> Builder.of(FamiliarCat::new, MobCategory.CREATURE)
            .sized(0.6F, 0.7F).clientTrackingRange(8).build(Enchanted.location("familiar_cat").toString()));



    private static <T extends EntityType<?>> Supplier<T> register(String name, Supplier<T> entityTypeSupplier) {
        return CommonServices.COMMON_REGISTRY.register(Registry.ENTITY_TYPE, name, entityTypeSupplier);
    }

    public static void load() {} // Method which exists purely to load the class.

}
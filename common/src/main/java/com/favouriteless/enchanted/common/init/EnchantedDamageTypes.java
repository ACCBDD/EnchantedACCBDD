package com.favouriteless.enchanted.common.init;


import com.favouriteless.enchanted.Enchanted;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class EnchantedDamageTypes {

    public static final ResourceKey<DamageType> SACRIFICE = ResourceKey.create(Registries.DAMAGE_TYPE, Enchanted.location("sacrifice"));
    public static final ResourceKey<DamageType> SOUND = ResourceKey.create(Registries.DAMAGE_TYPE, Enchanted.location("sound"));

}

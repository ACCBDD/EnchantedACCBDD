package net.favouriteless.enchanted.common.init;

import net.favouriteless.enchanted.common.Enchanted;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class EFeatures {

    public static class Trees {
        public static final ResourceKey<ConfiguredFeature<?, ?>> ALDER = configured(Enchanted.id("alder_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN = configured(Enchanted.id("hawthorn_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> ROWAN = configured(Enchanted.id("rowan_tree"));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> configured(ResourceLocation location) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, location);
    }

}

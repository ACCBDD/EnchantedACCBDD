package net.favouriteless.enchanted.common.items.component;

import net.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class EDataComponents {

    public static final Supplier<DataComponentType<EntityRefData>> ENTITY_REF = register("entity_ref", b -> b.persistent(EntityRefData.CODEC).networkSynchronized(EntityRefData.STREAM_CODEC).cacheEncoding());
    public static final Supplier<DataComponentType<OptionalBlockPosData>> OPTIONAL_BLOCK_POS = register("block_pos", b -> b.persistent(OptionalBlockPosData.CODEC).networkSynchronized(OptionalBlockPosData.STREAM_CODEC).cacheEncoding());
    public static final Supplier<DataComponentType<OptionalLevelKeyData>> OPTIONAL_LEVEL_KEY = register("level_key", b -> b.persistent(OptionalLevelKeyData.CODEC).networkSynchronized(OptionalLevelKeyData.STREAM_CODEC).cacheEncoding());

    private static <T> Supplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.DATA_COMPONENT_TYPE, name, () -> builder.apply(DataComponentType.builder()).build());
    }

    public static void load() {} // Method which exists purely to load the class.

}

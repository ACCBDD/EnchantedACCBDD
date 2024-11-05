package favouriteless.enchanted.common.items.component;

import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class EDataComponentTypes {

    public static final Supplier<DataComponentType<EntityRefData>> ENTITY_REF = register("entity_ref", b -> b.persistent(EntityRefData.CODEC).networkSynchronized(EntityRefData.STREAM_CODEC).cacheEncoding());
    public static final Supplier<DataComponentType<BlockPosData>> BLOCK_POS = register("block_pos", b -> b.persistent(BlockPosData.CODEC).networkSynchronized(BlockPosData.STREAM_CODEC).cacheEncoding());

    private static <T> Supplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.DATA_COMPONENT_TYPE, name, () -> builder.apply(DataComponentType.builder()).build());
    }

    public static void load() {} // Method which exists purely to load the class.

}

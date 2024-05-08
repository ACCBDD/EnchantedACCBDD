package favouriteless.enchanted.common.init.registry.util;

import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class BlockRegistryDescriptor<T extends Block> extends RegistryDescriptor<T> {

    private LootInfo lootInfo = new LootInfo();

    public BlockRegistryDescriptor(String name, Supplier<T> blockSupplier) {
        super(name, blockSupplier);
    }

    public static <T extends Block> BlockRegistryDescriptor<T> of(String name, Supplier<T> blockSupplier) {
        return new BlockRegistryDescriptor<>(name, blockSupplier);
    }

    public LootInfo getLootInfo() {
        return lootInfo;
    }

    public BlockRegistryDescriptor<T> dropSelf() {
        lootInfo.type = LootType.SELF;
        return this;
    }

    public BlockRegistryDescriptor<T> drop(Supplier<? extends ItemLike> item) {
        lootInfo.type = LootType.OTHER;
        lootInfo.drop = item;
        return this;
    }

    public BlockRegistryDescriptor<T> drop(ItemLike item) {
        return drop(() -> item);
    }

    @SafeVarargs
    public final BlockRegistryDescriptor<T> tools(Supplier<? extends ItemLike>... items) {
        lootInfo.tools = items;
        return this;
    }

    public BlockRegistryDescriptor<T> silk() {
        lootInfo.silk = true;
        return this;
    }

    public Supplier<T> register() {
        return CommonServices.COMMON_REGISTRY.registerBlock(this);
    }

    public static class LootInfo {
        public LootType type = LootType.NONE;
        public Supplier<? extends ItemLike> drop = null;
        public Supplier<? extends ItemLike>[] tools = null;
        public boolean silk = false;
    }

    public enum LootType {
        NONE,
        SELF,
        OTHER
    }

}
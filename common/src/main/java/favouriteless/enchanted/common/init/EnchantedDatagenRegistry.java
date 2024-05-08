package favouriteless.enchanted.common.init;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Objects get registered for automatic datagen in here. It's a weird way to do it but datagen code doesn't exist here.
 */
public class EnchantedDatagenRegistry {

    public static final Map<Supplier<Block>, Supplier<ItemLike>[]> BLOCK_LOOT = new HashMap<>();

}

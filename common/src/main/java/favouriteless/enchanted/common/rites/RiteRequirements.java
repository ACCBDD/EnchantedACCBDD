package favouriteless.enchanted.common.rites;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public record RiteRequirements(Map<CirclePart, Block> circles, Map<EntityType<?>, Integer> entities, Map<Item, Integer> items, int power, int tickPower) {

    public static final Codec<RiteRequirements> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(CirclePart.CODEC, BuiltInRegistries.BLOCK.byNameCodec()).optionalFieldOf("circles", new HashMap<>()).forGetter(RiteRequirements::circles),
            Codec.unboundedMap(BuiltInRegistries.ENTITY_TYPE.byNameCodec(), Codec.INT).optionalFieldOf("entities", new HashMap<>()).forGetter(RiteRequirements::entities),
            Codec.unboundedMap(BuiltInRegistries.ITEM.byNameCodec(), Codec.INT).optionalFieldOf("items", new HashMap<>()).forGetter(RiteRequirements::items),
            Codec.INT.optionalFieldOf("power", 0).forGetter(RiteRequirements::power),
            Codec.INT.optionalFieldOf("tick_power", 0).forGetter(RiteRequirements::tickPower)
    ).apply(instance, RiteRequirements::new));

}

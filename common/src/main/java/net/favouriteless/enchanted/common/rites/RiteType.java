package net.favouriteless.enchanted.common.rites;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.init.EData;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

public class RiteType {

    public static final Codec<RiteType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("items").forGetter(r -> r.items),
            Codec.unboundedMap(CircleMagicShape.HOLDER_CODEC, BuiltInRegistries.BLOCK.byNameCodec()).optionalFieldOf("shapes", Map.of()).forGetter(r -> r.shapes),
            BuiltInRegistries.ENTITY_TYPE.byNameCodec().listOf().optionalFieldOf("entities", List.of()).forGetter(r -> r.entities),
            Codec.INT.optionalFieldOf("power", 0).forGetter(r -> r.power),
            Codec.INT.optionalFieldOf("tick_power", 0).forGetter(r -> r.tickPower),
            RiteFactoryRegistry.CODEC.fieldOf("factory").forGetter(r -> r.factory)
    ).apply(instance, RiteType::new));

    private final List<ItemStack> items;
    private final Map<Holder<CircleMagicShape>, Block> shapes;
    private final List<EntityType<?>> entities;
    private final int power;
    private final int tickPower;
    private final RiteFactory factory;

    public RiteType(List<ItemStack> items, Map<Holder<CircleMagicShape>, Block> shapes, List<EntityType<?>> entities,
                    int power, int tickPower, RiteFactory factory) {
        this.items = items;
        this.shapes = shapes;
        this.entities = entities;
        this.power = power;
        this.tickPower = tickPower;
        this.factory = factory;
    }


    public boolean matches(Level level, BlockPos pos, List<Entity> inputs) {
        for(Entry<Holder<CircleMagicShape>, Block> entry : shapes.entrySet()) {
            if(!entry.getKey().value().matches(level, pos, entry.getValue()))
                return false;
        }

        List<EntityType<?>> entities = getEntities();
        inputs.forEach(entity -> entities.remove(entity.getType()));
        if(!entities.isEmpty())
            return false;

        List<ItemStack> items = getItems();
        for(Entity input : inputs) {
            if(input instanceof ItemEntity itemEntity) {
                ItemStack item = itemEntity.getItem();
                if(item.isEmpty())
                    break;

                for(ItemStack required : items) {
                    if(ItemUtil.isSameItemPartial(item, required)) {
                        required.shrink(item.getCount());
                    }
                }
            }
        }
        items.removeIf(ItemStack::isEmpty);

        return items.isEmpty();
    }

    /**
     * @return A copy of this RiteRequirements' item list.
     */
    public List<ItemStack> getItems() {
        return items.stream().map(ItemStack::copy).collect(Collectors.toList());
    }

    /**
     * @return A copy of this RiteRequirements' entity list.
     */
    public List<EntityType<?>> getEntities() {
        return new ArrayList<>(entities);
    }

    /**
     * @return This RiteRequirements' shapes map.
     */
    public Map<Holder<CircleMagicShape>, Block> getShapes() {
        return shapes;
    }

    public int getPower() {
        return power;
    }

    public int getTickPower() {
        return tickPower;
    }

    public Rite create(ServerLevel level, BlockPos pos, UUID caster, List<ItemStack> itemsConsumed) {
        return factory.create(new BaseRiteParams(factory.id(), tickPower, level, pos, caster, itemsConsumed));
    }

    public static RiteType getFirstMatching(Level level, BlockPos pos) {
        Registry<RiteType> reg = level.registryAccess().registry(EData.RITE_TYPES_REGISTRY).orElse(null);
        if(reg == null)
            return null;

        List<Entity> entities = level.getEntities(null, new AABB(
                pos.getX()-3, pos.getY(), pos.getZ()-3,
                pos.getX()+4, pos.getY()+1, pos.getZ()+4
                ));

        for(RiteType type : reg) {
            if(type.matches(level, pos, entities))
                return type;
        }

        return null;
    }

}

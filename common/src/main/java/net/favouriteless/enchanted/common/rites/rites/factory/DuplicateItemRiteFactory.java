package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.CreateItemRite;
import net.favouriteless.enchanted.common.rites.rites.DuplicateItemRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record DuplicateItemRiteFactory(Item targetItem, int count) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("duplicate_item");

    public static final MapCodec<DuplicateItemRiteFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(f -> f.targetItem),
            Codec.INT.optionalFieldOf("count", 1).forGetter(f -> f.count)
    ).apply(instance, DuplicateItemRiteFactory::new));

    @Override
    public Rite create(BaseRiteParams params) {
        return new DuplicateItemRite(params, targetItem, count);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

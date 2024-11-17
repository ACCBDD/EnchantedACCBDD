package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.EntityBoundCreateItemRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record EntityBoundCreateItemFactory(List<ItemStack> items) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("entity_bound_create_item");

    public static final MapCodec<EntityBoundCreateItemFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("items").forGetter(f -> f.items)
    ).apply(instance, EntityBoundCreateItemFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new EntityBoundCreateItemRite(baseParams, params, items);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

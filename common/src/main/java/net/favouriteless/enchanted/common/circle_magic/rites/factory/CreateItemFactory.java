package net.favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.circle_magic.rites.CreateItemRite;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record CreateItemFactory(List<ItemStack> items) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("create_item");

    public static final MapCodec<CreateItemFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("items").forGetter(f -> f.items)
    ).apply(instance, CreateItemFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new CreateItemRite(baseParams, params, items);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public List<ItemStack> getOutputs() {
        return items;
    }

}

package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.EntityBoundCreateItemRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
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

    @Override
    public List<ItemStack> getOutputs() {
        return items;
    }

}

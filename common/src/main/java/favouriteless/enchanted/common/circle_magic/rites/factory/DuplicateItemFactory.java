package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.DuplicateItemRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record DuplicateItemFactory(Item targetItem, int count) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("duplicate_item");

    public static final MapCodec<DuplicateItemFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(f -> f.targetItem),
            Codec.INT.optionalFieldOf("Count", 1).forGetter(f -> f.count)
    ).apply(instance, DuplicateItemFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new DuplicateItemRite(baseParams, params, targetItem, count);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public List<ItemStack> getOutputs() {
        return List.of(new ItemStack(targetItem, count));
    }

}

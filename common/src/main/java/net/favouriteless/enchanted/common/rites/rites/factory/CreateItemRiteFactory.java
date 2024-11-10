package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.CreateItemRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.TotalEclipseRite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.UUID;

public record CreateItemRiteFactory(List<ItemStack> items) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("create_item");

    public static final MapCodec<CreateItemRiteFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("items").forGetter(f -> f.items)
    ).apply(instance, CreateItemRiteFactory::new));

    @Override
    public Rite create(int tickPower, ServerLevel level, BlockPos pos, UUID caster, UUID target) {
        return new CreateItemRite(ID, tickPower, level, pos, caster, target, this.items);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

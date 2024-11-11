package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.CommandRite;
import net.favouriteless.enchanted.common.rites.rites.CreateItemRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record CommandRiteFactory(List<List<String>> commands, int delay) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("command");

    public static final MapCodec<CommandRiteFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.listOf(1, Integer.MAX_VALUE).listOf(0, Integer.MAX_VALUE).fieldOf("commands").forGetter(f -> f.commands),
            Codec.INT.optionalFieldOf("delay", 0).forGetter(f -> f.delay)
    ).apply(instance, CommandRiteFactory::new));

    @Override
    public Rite create(BaseRiteParams params) {
        return new CommandRite(params, commands, delay);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

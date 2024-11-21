package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.rites.CommandRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record CommandFactory(List<List<String>> commands, int delay) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("command");

    public static final MapCodec<CommandFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.listOf(1, Integer.MAX_VALUE).listOf(0, Integer.MAX_VALUE).fieldOf("commands").forGetter(f -> f.commands),
            Codec.INT.optionalFieldOf("delay", 0).forGetter(f -> f.delay)
    ).apply(instance, CommandFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return new CommandRite(baseParams, params, commands, delay);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

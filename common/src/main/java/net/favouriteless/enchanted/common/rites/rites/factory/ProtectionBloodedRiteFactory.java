package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.ProtectionBloodedRite;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.minecraft.resources.ResourceLocation;

public record ProtectionBloodedRiteFactory(int radius, int duration, boolean blocksPlayers) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("protection_blooded");

    public static final MapCodec<ProtectionBloodedRiteFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("radius").forGetter(f -> f.radius),
            Codec.INT.optionalFieldOf("duration", Integer.MAX_VALUE).forGetter(f -> f.duration),
            Codec.BOOL.optionalFieldOf("blocks_players", false).forGetter(f -> f.blocksPlayers)
    ).apply(instance, ProtectionBloodedRiteFactory::new));

    @Override
    public Rite create(BaseRiteParams params) {
        return new ProtectionBloodedRite(params, radius, duration, blocksPlayers);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

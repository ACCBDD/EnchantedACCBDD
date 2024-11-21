package favouriteless.enchanted.common.circle_magic.rites.factory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import favouriteless.enchanted.api.rites.RiteFactory;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.RiteTargetingType;
import favouriteless.enchanted.common.circle_magic.rites.ProtectionEntityRite;
import favouriteless.enchanted.common.circle_magic.rites.ProtectionRite;
import favouriteless.enchanted.common.circle_magic.rites.ProtectionWaystoneRite;
import favouriteless.enchanted.common.circle_magic.rites.Rite;
import favouriteless.enchanted.common.circle_magic.rites.Rite.BaseRiteParams;
import favouriteless.enchanted.common.circle_magic.rites.Rite.RiteParams;
import net.minecraft.resources.ResourceLocation;

public record ProtectionFactory(RiteTargetingType target, int radius, int duration, boolean blocksPlayers) implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("protection");

    public static final MapCodec<ProtectionFactory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RiteTargetingType.CODEC.optionalFieldOf("target", RiteTargetingType.DEFAULT).forGetter(f -> f.target),
            Codec.INT.fieldOf("radius").forGetter(f -> f.radius),
            Codec.INT.optionalFieldOf("duration", Integer.MAX_VALUE).forGetter(f -> f.duration),
            Codec.BOOL.optionalFieldOf("blocks_players", false).forGetter(f -> f.blocksPlayers)
    ).apply(instance, ProtectionFactory::new));

    @Override
    public Rite create(BaseRiteParams baseParams, RiteParams params) {
        return switch(target) {
            case DEFAULT -> new ProtectionRite(baseParams, params, radius, duration, blocksPlayers);
            case LOCATION -> new ProtectionWaystoneRite(baseParams, params, radius, duration, blocksPlayers);
            case ENTITY -> new ProtectionEntityRite(baseParams, params, radius, duration, blocksPlayers);
        };
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

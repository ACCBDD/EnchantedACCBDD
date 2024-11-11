package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.Rite.BaseRiteParams;
import net.favouriteless.enchanted.common.rites.rites.TotalEclipseRite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class TotalEclipseRiteFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("total_eclipse");

    public static final MapCodec<TotalEclipseRiteFactory> CODEC = MapCodec.unit(TotalEclipseRiteFactory::new);

    @Override
    public Rite create(BaseRiteParams params) {
        return new TotalEclipseRite(params);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

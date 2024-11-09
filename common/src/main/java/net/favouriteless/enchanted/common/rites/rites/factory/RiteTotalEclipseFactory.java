package net.favouriteless.enchanted.common.rites.rites.factory;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.favouriteless.enchanted.common.rites.rites.RiteTotalEclipse;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class RiteTotalEclipseFactory implements RiteFactory {

    public static final ResourceLocation ID = Enchanted.id("total_eclipse");

    public static final MapCodec<RiteTotalEclipseFactory> CODEC = MapCodec.unit(RiteTotalEclipseFactory::new);

    @Override
    public Rite create(int tickPower, ServerLevel level, BlockPos pos, UUID caster, UUID target) {
        return new RiteTotalEclipse(ID, tickPower, level, pos, caster, target);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

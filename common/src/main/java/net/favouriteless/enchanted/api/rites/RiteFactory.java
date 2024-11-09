package net.favouriteless.enchanted.api.rites;

import net.favouriteless.enchanted.common.rites.rites.Rite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface RiteFactory {

    Rite create(int tickPower, ServerLevel level, @Nullable BlockPos pos, @Nullable UUID caster, @Nullable UUID target);

    ResourceLocation id();
}

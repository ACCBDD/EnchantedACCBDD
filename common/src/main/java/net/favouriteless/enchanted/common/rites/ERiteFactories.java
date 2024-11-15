package net.favouriteless.enchanted.common.rites;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.rites.rites.BloodedTransposeCasterRite;
import net.favouriteless.enchanted.common.rites.rites.factory.*;
import net.minecraft.resources.ResourceLocation;

public class ERiteFactories {

    public static void load() {
        register(TotalEclipseRiteFactory.ID, TotalEclipseRiteFactory.CODEC);
        register(CreateItemRiteFactory.ID, CreateItemRiteFactory.CODEC);
        register(LocationBoundCreateItemRiteFactory.ID, LocationBoundCreateItemRiteFactory.CODEC);
        register(DuplicateItemRiteFactory.ID, DuplicateItemRiteFactory.CODEC);
        register(EntityBoundCreateItemRiteFactory.ID, EntityBoundCreateItemRiteFactory.CODEC);
        register(CommandRiteFactory.ID, CommandRiteFactory.CODEC);
        register(ApplyCurseFactory.ID, ApplyCurseFactory.CODEC);
        register(RemoveCurseFactory.ID, RemoveCurseFactory.CODEC);
        register(BroilingRiteFactory.ID, BroilingRiteFactory.CODEC);
        register(SanctityRiteFactory.ID, SanctityRiteFactory.CODEC);
        register(ImprisonmentRiteFactory.ID, ImprisonmentRiteFactory.CODEC);
        register(ProtectionRiteFactory.ID, ProtectionRiteFactory.CODEC);
        register(WaystoneProtectionRiteFactory.ID, WaystoneProtectionRiteFactory.CODEC);
        register(BloodedProtectionRiteFactory.ID, BloodedProtectionRiteFactory.CODEC);
        register(SummonEntityRiteFactory.ID, SummonEntityRiteFactory.CODEC);
        register(WaystoneTransposeCasterRiteFactory.ID, WaystoneTransposeCasterRiteFactory.CODEC);
        register(BloodedTransposeCasterRiteFactory.ID, BloodedTransposeCasterRiteFactory.CODEC);
    }

    public static void register(ResourceLocation id, MapCodec<? extends RiteFactory> codec) {
        RiteFactoryRegistry.register(id, codec);
    }

}

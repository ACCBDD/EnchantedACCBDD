package net.favouriteless.enchanted.common.rites;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.rites.rites.factory.*;
import net.minecraft.resources.ResourceLocation;

public class ERiteFactories {

    public static void load() {
        register(ApplyCurseFactory.ID, ApplyCurseFactory.CODEC);
        register(BroilingFactory.ID, BroilingFactory.CODEC);
        register(CommandFactory.ID, CommandFactory.CODEC);
        register(CreateItemFactory.ID, CreateItemFactory.CODEC);
        register(DuplicateItemFactory.ID, DuplicateItemFactory.CODEC);
        register(EntityBoundCreateItemFactory.ID, EntityBoundCreateItemFactory.CODEC);
        register(ImprisonmentFactory.ID, ImprisonmentFactory.CODEC);
        register(LocationBoundCreateItemFactory.ID, LocationBoundCreateItemFactory.CODEC);
        register(ProtectionFactory.ID, ProtectionFactory.CODEC);
        register(RemoveCurseFactory.ID, RemoveCurseFactory.CODEC);
        register(SanctityFactory.ID, SanctityFactory.CODEC);
        register(SkyWrathFactory.ID, SkyWrathFactory.CODEC);
        register(SummonEntityFactory.ID, SummonEntityFactory.CODEC);
        register(TotalEclipseFactory.ID, TotalEclipseFactory.CODEC);
        register(TransposeBlocksFactory.ID, TransposeBlocksFactory.CODEC);
        register(TransposeCasterFactory.ID, TransposeCasterFactory.CODEC);
    }

    public static void register(ResourceLocation id, MapCodec<? extends RiteFactory> codec) {
        RiteFactoryRegistry.register(id, codec);
    }

}

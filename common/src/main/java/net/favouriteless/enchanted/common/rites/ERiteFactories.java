package net.favouriteless.enchanted.common.rites;

import com.mojang.serialization.MapCodec;
import net.favouriteless.enchanted.api.rites.RiteFactory;
import net.favouriteless.enchanted.common.rites.rites.factory.CreateItemRiteFactory;
import net.favouriteless.enchanted.common.rites.rites.factory.TotalEclipseRiteFactory;
import net.minecraft.resources.ResourceLocation;

public class ERiteFactories {

    public static void load() {
        register(TotalEclipseRiteFactory.ID, TotalEclipseRiteFactory.CODEC);
        register(CreateItemRiteFactory.ID, CreateItemRiteFactory.CODEC);
    }

    public static void register(ResourceLocation id, MapCodec<? extends RiteFactory> codec) {
        RiteFactoryRegistry.register(id, codec);
    }

}

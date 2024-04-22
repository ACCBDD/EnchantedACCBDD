package com.favouriteless.enchanted;

import com.favouriteless.enchanted.common.CommonConfig;
import com.favouriteless.enchanted.common.CommonEventsFabric;
import com.favouriteless.enchanted.common.entities.FamiliarCat;
import com.favouriteless.enchanted.common.entities.Mandrake;
import com.favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import com.favouriteless.enchanted.common.init.registry.EnchantedEntityTypes;
import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraftforge.fml.config.ModConfig.Type;

public class EnchantedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Enchanted.init();
        registerEntityAttributes();
        CommonEventsFabric.register();
        EnchantedItems.registerCompostables();
        EnchantedBlocks.registerFlammables();
        ForgeConfigRegistry.INSTANCE.register(Enchanted.MOD_ID, Type.COMMON, CommonConfig.SPEC, "enchanted-common.toml");
    }

    private static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EnchantedEntityTypes.MANDRAKE.get(), Mandrake.createAttributes());
        FabricDefaultAttributeRegistry.register(EnchantedEntityTypes.FAMILIAR_CAT.get(), FamiliarCat.createCatAttributes());
    }

}
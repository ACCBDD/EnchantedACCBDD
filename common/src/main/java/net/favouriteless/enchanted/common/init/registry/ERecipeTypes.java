package net.favouriteless.enchanted.common.init.registry;

import net.favouriteless.enchanted.common.recipes.*;
import net.favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ERecipeTypes {

    public static Supplier<RecipeType<ByproductRecipe>> BYPRODUCT = registerType("byproduct");
    public static Supplier<ByproductRecipe.Serializer> BYPRODUCT_SERIALIZER = ERecipeTypes.registerSerializer("byproduct", ByproductRecipe.Serializer::new);

    public static Supplier<RecipeType<DistillingRecipe>> DISTILLING = registerType("distilling");
    public static Supplier<DistillingRecipe.Serializer> DISTILLING_SERIALIZER = ERecipeTypes.registerSerializer("distilling", DistillingRecipe.Serializer::new);

    public static Supplier<RecipeType<WitchCauldronRecipe>> WITCH_CAULDRON = registerType("witch_cauldron");
    public static Supplier<WitchCauldronRecipe.Serializer> WITCH_CAULDRON_SERIALIZER = ERecipeTypes.registerSerializer("witch_cauldron", WitchCauldronRecipe.Serializer::new);

    public static Supplier<RecipeType<KettleRecipe>> KETTLE = registerType("kettle");
    public static Supplier<KettleRecipe.Serializer> KETTLE_SERIALIZER = ERecipeTypes.registerSerializer("kettle", KettleRecipe.Serializer::new);

    public static Supplier<RecipeType<SpinningRecipe>> SPINNING = registerType("wheel");
    public static Supplier<SpinningRecipe.Serializer> SPINNING_SERIALIZER = ERecipeTypes.registerSerializer("wheel", SpinningRecipe.Serializer::new);


    
    private static <T extends RecipeSerializer<?>> Supplier<T> registerSerializer(String name, Supplier<T> serializerSupplier) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.RECIPE_SERIALIZER, name, serializerSupplier);
    }

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> registerType(String name) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.RECIPE_TYPE, name, () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    public static void load() {} // Method which exists purely to load the class.

}
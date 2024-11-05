package favouriteless.enchanted.common.recipes;

import favouriteless.enchanted.common.recipes.serializers.SimpleSerializer;
import favouriteless.enchanted.platform.CommonServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ERecipeTypes {

    public static Supplier<RecipeType<ByproductRecipe>> BYPRODUCT = register("byproduct");
    public static Supplier<RecipeType<DistillingRecipe>> DISTILLING = register("distilling");
    public static Supplier<RecipeType<WitchCauldronRecipe>> WITCH_CAULDRON = register("witch_cauldron");
    public static Supplier<RecipeType<KettleRecipe>> KETTLE = register("kettle");
    public static Supplier<RecipeType<SpinningRecipe>> SPINNING = register("wheel");

    public static Supplier<SimpleSerializer<ByproductRecipe>> BYPRODUCT_SERIALIZER = ERecipeTypes.registerSerializer("byproduct", () -> SimpleSerializer.of(ByproductRecipe.CODEC, ByproductRecipe.STREAM_CODEC));
    public static Supplier<SimpleSerializer<DistillingRecipe>> DISTILLING_SERIALIZER = ERecipeTypes.registerSerializer("distilling", () -> SimpleSerializer.of(DistillingRecipe.CODEC, DistillingRecipe.STREAM_CODEC));
    public static Supplier<SimpleSerializer<WitchCauldronRecipe>> WITCH_CAULDRON_SERIALIZER = ERecipeTypes.registerSerializer("witch_cauldron", () -> SimpleSerializer.of(WitchCauldronRecipe.CODEC, WitchCauldronRecipe.STREAM_CODEC));
    public static Supplier<SimpleSerializer<KettleRecipe>> KETTLE_SERIALIZER = ERecipeTypes.registerSerializer("kettle", () -> SimpleSerializer.of(KettleRecipe.CODEC, KettleRecipe.STREAM_CODEC));
    public static Supplier<SimpleSerializer<SpinningRecipe>> SPINNING_SERIALIZER = ERecipeTypes.registerSerializer("wheel", () -> SimpleSerializer.of(SpinningRecipe.CODEC, SpinningRecipe.STREAM_CODEC));

    
    private static <T extends RecipeSerializer<?>> Supplier<T> registerSerializer(String name, Supplier<T> serializerSupplier) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.RECIPE_SERIALIZER, name, serializerSupplier);
    }

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> register(String name) {
        return CommonServices.COMMON_REGISTRY.register(BuiltInRegistries.RECIPE_TYPE, name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    public static void load() {} // Method which exists purely to load the class.

}
package net.favouriteless.enchanted.neoforge.datagen.builders.recipe;

import net.favouriteless.enchanted.common.Enchanted;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public abstract class ERecipeBuilder implements RecipeBuilder {

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return null;
    }

    @Override
    public void save(RecipeOutput output) {
        save(output, getDefaultName());
    }

    protected ResourceLocation getDefaultName() {
        return Enchanted.id(BuiltInRegistries.ITEM.getKey(getResult()).getPath());
    }
}

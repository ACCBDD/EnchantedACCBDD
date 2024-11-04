package favouriteless.enchanted.neoforge.datagen.builders.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class EnchantedFinishedRecipe implements FinishedRecipe {

    private final ResourceLocation id;

    public EnchantedFinishedRecipe(@NotNull ResourceLocation id) {
        this.id = id;
    }

    @Override
    @NotNull
    public ResourceLocation getId() {
        return id;
    }

    @Nullable
    public JsonObject serializeAdvancement() {
        return null;
    }

    @Nullable
    public ResourceLocation getAdvancementId() {
        return null;
    }

}
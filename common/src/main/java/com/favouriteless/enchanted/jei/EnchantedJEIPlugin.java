package com.favouriteless.enchanted.jei;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.api.rites.AbstractCreateItemRite;
import com.favouriteless.enchanted.client.screens.DistilleryScreen;
import com.favouriteless.enchanted.client.screens.SpinningWheelScreen;
import com.favouriteless.enchanted.client.screens.WitchOvenScreen;
import com.favouriteless.enchanted.common.init.EnchantedTags.Blocks;
import com.favouriteless.enchanted.common.init.registry.EnchantedItems;
import com.favouriteless.enchanted.common.init.registry.EnchantedMenuTypes;
import com.favouriteless.enchanted.common.init.registry.EnchantedRecipeTypes;
import com.favouriteless.enchanted.common.init.registry.RiteTypes;
import com.favouriteless.enchanted.jei.categories.*;
import com.favouriteless.enchanted.jei.container_handlers.DistilleryContainerHandler;
import com.favouriteless.enchanted.jei.container_handlers.SpinningWheelContainerHandler;
import com.favouriteless.enchanted.jei.container_handlers.WitchOvenContainerHandler;
import com.favouriteless.enchanted.jei.recipes.JEIMutandisRecipe;
import com.favouriteless.enchanted.common.menus.SpinningWheelMenu;
import com.favouriteless.enchanted.common.menus.WitchOvenMenu;
import com.favouriteless.enchanted.common.rites.RiteType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class EnchantedJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return Enchanted.location("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ByproductCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_BYPRODUCT));
        registration.addRecipeCategories(new DistilleryCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_DISTILLING));
        registration.addRecipeCategories(new SpinningWheelCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_SPINNING));
        registration.addRecipeCategories(new WitchCauldronCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_WITCH_CAULDRON));
        registration.addRecipeCategories(new KettleCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_KETTLE));
        registration.addRecipeCategories(new RiteCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_RITE));
        registration.addRecipeCategories(new MutandisCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_MUTANDIS, new ItemStack(EnchantedItems.MUTANDIS.get()), Component.translatable("jei.enchanted.mutandis")));
        registration.addRecipeCategories(new MutandisCategory(registration.getJeiHelpers(), JEIRecipeTypes.RECIPE_TYPE_MUTANDIS_EXTREMIS, new ItemStack(EnchantedItems.MUTANDIS_EXTREMIS.get()), Component.translatable("jei.enchanted.mutandis_extremis")));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_BYPRODUCT, recipeManager.getAllRecipesFor(EnchantedRecipeTypes.BYPRODUCT.get()));
        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_DISTILLING, recipeManager.getAllRecipesFor(EnchantedRecipeTypes.DISTILLING.get()));
        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_SPINNING, recipeManager.getAllRecipesFor(EnchantedRecipeTypes.SPINNING.get()));

        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_WITCH_CAULDRON, recipeManager.getAllRecipesFor(EnchantedRecipeTypes.WITCH_CAULDRON.get()));
        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_KETTLE, recipeManager.getAllRecipesFor(EnchantedRecipeTypes.KETTLE.get()));

        List<AbstractCreateItemRite> riteCrafts = RiteTypes.getEntries().stream()
                .map(RiteType::create)
                .filter(rite -> rite instanceof AbstractCreateItemRite)
                .map(rite -> (AbstractCreateItemRite)rite).toList();
        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_RITE, riteCrafts);

        BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_PLANTS).ifPresent(tag -> registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_MUTANDIS, tag.stream()
                .filter(block -> !BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_BLACKLIST).map(t -> t.contains(block)).orElse(false))
                .map(block -> new JEIMutandisRecipe(Blocks.MUTANDIS_PLANTS, new ItemStack(block.value()), Component.translatable("jei.enchanted.mutandis.description"))).toList()));

        BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_EXTREMIS_PLANTS).ifPresent(tag -> registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_MUTANDIS_EXTREMIS, tag.stream()
                .filter(block -> !BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_EXTREMIS_BLACKLIST).map(t -> t.contains(block)).orElse(false))
                .map(block -> new JEIMutandisRecipe(Blocks.MUTANDIS_EXTREMIS_PLANTS, new ItemStack(block.value()), Component.translatable("jei.enchanted.mutandis.description"))).toList()));

        registration.addIngredientInfo(new ItemStack(EnchantedItems.CHALICE_FILLED.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.enchanted.chalice_filled"));
        registration.addIngredientInfo(new ItemStack(EnchantedItems.CHALICE_FILLED_MILK.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.enchanted.chalice_filled_milk"));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(WitchOvenMenu.class, EnchantedMenuTypes.WITCH_OVEN.get(),
                JEIRecipeTypes.RECIPE_TYPE_WITCH_CAULDRON, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(SpinningWheelMenu.class, EnchantedMenuTypes.SPINNING_WHEEL.get(),
                JEIRecipeTypes.RECIPE_TYPE_SPINNING, 0, 3, 4, 36);
        registration.addRecipeTransferHandler(new DistilleryTransferInfo());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(EnchantedItems.WITCH_OVEN.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_BYPRODUCT);
        registration.addRecipeCatalyst(EnchantedItems.DISTILLERY.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_DISTILLING);
        registration.addRecipeCatalyst(EnchantedItems.SPINNING_WHEEL.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_SPINNING);
        registration.addRecipeCatalyst(EnchantedItems.WITCH_CAULDRON.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_WITCH_CAULDRON);
        registration.addRecipeCatalyst(EnchantedItems.KETTLE.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_KETTLE);
        registration.addRecipeCatalyst(EnchantedItems.CHALK_WHITE.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.CHALK_GOLD.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.CHALK_PURPLE.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.CHALK_RED.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.MUTANDIS.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_MUTANDIS);
        registration.addRecipeCatalyst(EnchantedItems.MUTANDIS_EXTREMIS.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_MUTANDIS_EXTREMIS);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGenericGuiContainerHandler(DistilleryScreen.class, new DistilleryContainerHandler());
        registration.addGenericGuiContainerHandler(SpinningWheelScreen.class, new SpinningWheelContainerHandler());
        registration.addGenericGuiContainerHandler(WitchOvenScreen.class, new WitchOvenContainerHandler());
    }

}
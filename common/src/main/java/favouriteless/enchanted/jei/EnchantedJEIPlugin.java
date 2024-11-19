package favouriteless.enchanted.jei;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.api.rites.AbstractRite;
import favouriteless.enchanted.api.rites.CreateItemRite;
import favouriteless.enchanted.client.screens.DistilleryScreen;
import favouriteless.enchanted.client.screens.SpinningWheelScreen;
import favouriteless.enchanted.client.screens.WitchOvenScreen;
import favouriteless.enchanted.common.init.EnchantedData;
import favouriteless.enchanted.common.init.EnchantedTags.Blocks;
import favouriteless.enchanted.common.init.registry.*;
import favouriteless.enchanted.common.menus.SpinningWheelMenu;
import favouriteless.enchanted.common.menus.WitchOvenMenu;
import favouriteless.enchanted.common.circle_magic.rites.CirclePart;
import favouriteless.enchanted.common.circle_magic.rites.RiteRequirements;
import favouriteless.enchanted.jei.categories.*;
import favouriteless.enchanted.jei.container_handlers.DistilleryContainerHandler;
import favouriteless.enchanted.jei.container_handlers.SpinningWheelContainerHandler;
import favouriteless.enchanted.jei.container_handlers.WitchOvenContainerHandler;
import favouriteless.enchanted.jei.recipes.JEIMutandisRecipe;
import favouriteless.enchanted.jei.recipes.JEIRiteRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JeiPlugin
public class EnchantedJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return Enchanted.id("jei_plugin");
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


        EnchantedJEITextures.registerCirclePartPrefix(CirclePart.SMALL, "small");
        EnchantedJEITextures.registerCirclePartPrefix(CirclePart.MEDIUM, "medium");
        EnchantedJEITextures.registerCirclePartPrefix(CirclePart.LARGE, "large");
        EnchantedJEITextures.registerBlockSuffix(EnchantedBlocks.RITUAL_CHALK.get(), "white");
        EnchantedJEITextures.registerBlockSuffix(EnchantedBlocks.OTHERWHERE_CHALK.get(), "purple");
        EnchantedJEITextures.registerBlockSuffix(EnchantedBlocks.NETHER_CHALK.get(), "red");

        Optional<Registry<RiteRequirements>> optional = Minecraft.getInstance().level.registryAccess().registry(EnchantedData.RITE_REQUIREMENTS_REGISTRY);
        List<JEIRiteRecipe> riteCrafts = new ArrayList<>();
        optional.ifPresent(registry -> {
            for(ResourceLocation key : RiteTypes.getKeys()) {
                AbstractRite rite = RiteTypes.get(key).create();
                ItemStack[] out = rite instanceof CreateItemRite itemRite ? itemRite.getResultItems() : null;
                RiteRequirements requirements = registry.get(key);

                if(out != null && requirements != null)
                    riteCrafts.add(new JEIRiteRecipe(RiteTypes.get(key), requirements, out));
            }
        });
        registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_RITE, riteCrafts);

        BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_PLANTS).ifPresent(tag -> registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_MUTANDIS, tag.stream()
                .filter(block -> !BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_BLACKLIST).map(t -> t.contains(block)).orElse(false))
                .map(block -> new JEIMutandisRecipe(Blocks.MUTANDIS_PLANTS, new ItemStack(block.value()), Component.translatable("jei.enchanted.mutandis.description"))).toList()));

        BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_EXTREMIS_PLANTS).ifPresent(tag -> registration.addRecipes(JEIRecipeTypes.RECIPE_TYPE_MUTANDIS_EXTREMIS, tag.stream()
                .filter(block -> !BuiltInRegistries.BLOCK.getTag(Blocks.MUTANDIS_EXTREMIS_BLACKLIST).map(t -> t.contains(block)).orElse(false))
                .map(block -> new JEIMutandisRecipe(Blocks.MUTANDIS_EXTREMIS_PLANTS, new ItemStack(block.value()), Component.translatable("jei.enchanted.mutandis.description"))).toList()));

        registration.addIngredientInfo(new ItemStack(EnchantedItems.CHALICE_FILLED.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.enchanted.chalice_filled"));
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
        registration.addRecipeCatalyst(EnchantedItems.RITUAL_CHALK.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.GOLDEN_CHALK.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.OTHERWHERE_CHALK.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
        registration.addRecipeCatalyst(EnchantedItems.NETHER_CHALK.get().getDefaultInstance(), JEIRecipeTypes.RECIPE_TYPE_RITE);
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
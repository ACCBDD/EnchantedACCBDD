package net.favouriteless.enchanted.integrations.jei;

import net.favouriteless.enchanted.client.screens.DistilleryScreen;
import net.favouriteless.enchanted.client.screens.SpinningWheelScreen;
import net.favouriteless.enchanted.client.screens.WitchOvenScreen;
import net.favouriteless.enchanted.common.Enchanted;
import net.favouriteless.enchanted.common.init.EData;
import net.favouriteless.enchanted.common.init.registry.EItems;
import net.favouriteless.enchanted.common.init.registry.EMenuTypes;
import net.favouriteless.enchanted.common.init.registry.ERecipeTypes;
import net.favouriteless.enchanted.common.menus.SpinningWheelMenu;
import net.favouriteless.enchanted.common.menus.WitchOvenMenu;
import net.favouriteless.enchanted.common.util.RecipeUtils;
import net.favouriteless.enchanted.integrations.jei.categories.*;
import net.favouriteless.enchanted.integrations.jei.container_handlers.DistilleryContainerHandler;
import net.favouriteless.enchanted.integrations.jei.container_handlers.SpinningWheelContainerHandler;
import net.favouriteless.enchanted.integrations.jei.container_handlers.WitchOvenContainerHandler;
import net.favouriteless.enchanted.integrations.jei.recipes.JeiMutandisRecipe;
import net.favouriteless.enchanted.integrations.jei.recipes.JeiRiteRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class EJeiPlugin implements IModPlugin {

    public static final ResourceLocation ID = Enchanted.id("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers helpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();

        registration.addRecipeCategories(new ByproductCategory(guiHelper));
        registration.addRecipeCategories(new SpinningCategory(guiHelper));
        registration.addRecipeCategories(new DistillingCategory(guiHelper));
        registration.addRecipeCategories(new RiteCategory(guiHelper));
        registration.addRecipeCategories(new CauldronCategory(guiHelper));
        registration.addRecipeCategories(new KettleCategory(guiHelper));
        registration.addRecipeCategories(new MutandisCategory(guiHelper, EJeiRecipeTypes.MUTANDIS, EItems.MUTANDIS.get(), Component.translatable("jei.enchanted.mutandis")));
        registration.addRecipeCategories(new MutandisCategory(guiHelper, EJeiRecipeTypes.MUTANDIS_EXTREMIS, EItems.MUTANDIS_EXTREMIS.get(), Component.translatable("jei.enchanted.mutandis_extremis")));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(EJeiRecipeTypes.BYPRODUCT, RecipeUtils.getRecipes(ERecipeTypes.BYPRODUCT.get()));
        registration.addRecipes(EJeiRecipeTypes.SPINNING, RecipeUtils.getRecipes(ERecipeTypes.SPINNING.get()));
        registration.addRecipes(EJeiRecipeTypes.CAULDRON, RecipeUtils.getRecipes(ERecipeTypes.WITCH_CAULDRON.get()));
        registration.addRecipes(EJeiRecipeTypes.KETTLE, RecipeUtils.getRecipes(ERecipeTypes.KETTLE.get()));
        registration.addRecipes(EJeiRecipeTypes.DISTILLING, RecipeUtils.getRecipes(ERecipeTypes.DISTILLING.get()));

        JeiRiteRecipe.register(registration, Minecraft.getInstance().getConnection().registryAccess().registry(EData.RITE_TYPES_REGISTRY).get());
        JeiMutandisRecipe.register(registration);
        registration.addIngredientInfo(new ItemStack(EItems.CHALICE_FILLED.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.enchanted.chalice_filled"));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(WitchOvenMenu.class, EMenuTypes.WITCH_OVEN.get(), EJeiRecipeTypes.BYPRODUCT, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(SpinningWheelMenu.class, EMenuTypes.SPINNING_WHEEL.get(), EJeiRecipeTypes.SPINNING, 0, 3, 4, 36);
        registration.addRecipeTransferHandler(new DistilleryTransferInfo());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(EItems.WITCH_OVEN.get().getDefaultInstance(), EJeiRecipeTypes.BYPRODUCT);
        registration.addRecipeCatalyst(EItems.SPINNING_WHEEL.get().getDefaultInstance(), EJeiRecipeTypes.SPINNING);
        registration.addRecipeCatalyst(EItems.WITCH_CAULDRON.get().getDefaultInstance(), EJeiRecipeTypes.CAULDRON);
        registration.addRecipeCatalyst(EItems.KETTLE.get().getDefaultInstance(), EJeiRecipeTypes.KETTLE);
        registration.addRecipeCatalyst(EItems.DISTILLERY.get().getDefaultInstance(), EJeiRecipeTypes.DISTILLING);
        registration.addRecipeCatalyst(EItems.GOLDEN_CHALK.get().getDefaultInstance(), EJeiRecipeTypes.RITE);
        registration.addRecipeCatalyst(EItems.MUTANDIS.get().getDefaultInstance(), EJeiRecipeTypes.MUTANDIS);
        registration.addRecipeCatalyst(EItems.MUTANDIS_EXTREMIS.get().getDefaultInstance(), EJeiRecipeTypes.MUTANDIS_EXTREMIS);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGenericGuiContainerHandler(DistilleryScreen.class, new DistilleryContainerHandler());
        registration.addGenericGuiContainerHandler(SpinningWheelScreen.class, new SpinningWheelContainerHandler());
        registration.addGenericGuiContainerHandler(WitchOvenScreen.class, new WitchOvenContainerHandler());
    }

}
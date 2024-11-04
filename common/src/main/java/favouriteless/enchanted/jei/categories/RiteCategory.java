package favouriteless.enchanted.jei.categories;

import favouriteless.enchanted.Enchanted;
import favouriteless.enchanted.common.init.registry.EnchantedItems;
import favouriteless.enchanted.jei.EnchantedJEITextures;
import favouriteless.enchanted.common.rites.CirclePart;
import com.mojang.blaze3d.vertex.PoseStack;
import favouriteless.enchanted.jei.recipes.JEIRiteRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class RiteCategory implements IRecipeCategory<JEIRiteRecipe> {

    private final IJeiHelpers jeiHelpers;
    private final RecipeType<JEIRiteRecipe> recipeTypeRite;

    private static final int GLYPH_SIZE = 110;
    private static final int START_RADIUS = 15;
    private static final int RADIUS_INCREMENT = 15;

    private final IDrawableStatic glyph_golden;
    private final List<IDrawableStatic> circles = new ArrayList<>();
    private final IDrawableAnimated arrow;

    public RiteCategory(IJeiHelpers jeiHelpers, RecipeType<JEIRiteRecipe> recipeTypeRite) {
        this.jeiHelpers = jeiHelpers;
        this.recipeTypeRite = recipeTypeRite;
        IDrawableStatic arrow = jeiHelpers.getGuiHelper().createDrawable(Enchanted.id("textures/gui/witch_oven.png"), 176, 14, 24, 17);
        this.arrow = jeiHelpers.getGuiHelper().createAnimatedDrawable(arrow, 120, IDrawableAnimated.StartDirection.LEFT, false);
        glyph_golden = buildTexture(Enchanted.id("textures/gui/jei/gold_glyph.png"), GLYPH_SIZE, GLYPH_SIZE, jeiHelpers);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, JEIRiteRecipe recipe, IFocusGroup focuses) {
        List<ItemStack> itemList = new ArrayList<>();
        for(Item item : recipe.requirements().items().keySet())
            itemList.add(new ItemStack(item, recipe.requirements().items().get(item)));

        int circleNum = 1;
        int itemsRemaining = itemList.size();

        while(itemsRemaining > 0) {
            int radius = START_RADIUS + (circleNum-1)*RADIUS_INCREMENT;
            int limit = (int)Math.round(Math.pow(6, circleNum));
            int itemCount = Math.min(itemsRemaining, limit);

            for(int i = 0; i < itemCount; i++) {
                ItemStack stack = itemList.get(0);
                itemList.remove(stack);

                double angle = Math.toRadians(i * (360.0D / itemCount) + 180);
                int cx = (int) Math.round(Math.sin(-angle) * radius) - 8;
                int cy = (int) Math.round(Math.cos(-angle) * radius) - 8;

                builder.addSlot(RecipeIngredientRole.INPUT, 47 + cx, 60 + cy).addItemStack(stack);
                itemsRemaining--;
            }
            circleNum++;
        }

        ItemStack[] itemsOut = recipe.output();
        int numRows = (int)Math.ceil(itemsOut.length / 3.0D);
        int height = numRows * 17;
        int startX = 119;
        int startY = 61 - (int)Math.round(height/2.0D);
        for(int i = 0; i < itemsOut.length; i++) {
            ItemStack stack = itemsOut[i];
            builder.addSlot(RecipeIngredientRole.OUTPUT, startX + (i%3)*17, startY + i/3*17).addItemStack(stack);
        }

        circles.clear();
        for(CirclePart circlePart : recipe.requirements().circles().keySet()) {
            Block block = recipe.requirements().circles().get(circlePart);
            ResourceLocation textureLocation = EnchantedJEITextures.getCircleTextureLocation(circlePart, block);
            if(textureLocation != null) {
                circles.add(buildTexture(textureLocation, GLYPH_SIZE, GLYPH_SIZE, jeiHelpers));
            }
        }
    }

    @Override
    public void draw(JEIRiteRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
        for(IDrawableStatic drawable : circles) {
            drawable.draw(gui, 0, 14);
        }
        glyph_golden.draw(gui, 0, 14);
        this.arrow.draw(gui, 95, 53);

        ResourceLocation riteName = recipe.type().getId();
        String nameText = Component.translatable("rite." + riteName.getNamespace() + "." + riteName.getPath()).getString();
        drawText(Minecraft.getInstance(), gui, nameText, 180, 0, 0xFFFFFFFF);
        drawText(Minecraft.getInstance(), gui, "Required Altar Power : " + recipe.requirements().power(), 180, 112, 0xFFFFFFFF);

        if(!recipe.requirements().entities().isEmpty()) {
            PoseStack poseStack = gui.pose();
            poseStack.pushPose();
            poseStack.scale(0.5F, 0.5F, 0.5F);
            drawText(Minecraft.getInstance(), gui, "Has additional requirements", 360, 18, 0xFFFFFFFF);
            poseStack.popPose();
        }
    }

    private void drawText(Minecraft minecraft, GuiGraphics gui, String text, int x, int y, int mainColor) {
        int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
        int width = minecraft.font.width(text);
        int cx = x/2 - width/2 - 1;
        gui.drawString(minecraft.font, text, cx + 1, y, shadowColor, false);
    }

    private IDrawableStatic buildTexture(ResourceLocation resourceLocation, int width, int height, IJeiHelpers helper) {
        IDrawableBuilder builder = helper.getGuiHelper().drawableBuilder(resourceLocation, 0, 0, width, height);
        builder.setTextureSize(width, height);
        return builder.build();
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.enchanted.circle_magic");
    }

    @Override
    public IDrawable getBackground() {
        return jeiHelpers.getGuiHelper().createDrawable(Enchanted.id("textures/gui/jei/circle_magic.png"), 0, 0, 180, 120);
    }

    @Override
    public IDrawable getIcon() {
        return this.jeiHelpers.getGuiHelper().createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EnchantedItems.RITUAL_CHALK.get()));
    }

    @Override
    public RecipeType<JEIRiteRecipe> getRecipeType() {
        return recipeTypeRite;
    }

}
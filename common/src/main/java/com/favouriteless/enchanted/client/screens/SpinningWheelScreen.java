package com.favouriteless.enchanted.client.screens;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.blocks.entity.SpinningWheelBlockEntity;
import com.favouriteless.enchanted.common.menus.SpinningWheelMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class SpinningWheelScreen extends AbstractContainerScreen<SpinningWheelMenu> {

    private static final ResourceLocation TEXTURE = Enchanted.location("textures/gui/spinning_wheel.png");

    public static final int ICONS_U = 176;

    public static final int BAR0_XPOS = 27;
    public static final int BAR1_XPOS = 64;
    public static final int BAR_YPOS = 25;
    public static final int BAR0_ICON_V = 0;
    public static final int BAR1_ICON_V = 20;
    public static final int BAR_HEIGHT = 20;
    public static final int BAR_WIDTH = 15;

    public static final int WHEEL_XPOS = 86;
    public static final int WHEEL_YPOS = 25;
    public static final int WHEEL_FRAME1_V = 40;
    public static final int WHEEL_FRAME2_V = 74;
    public static final int WHEEL_SIZE = 34;

    public SpinningWheelScreen(SpinningWheelMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        imageWidth = 176;
        imageHeight = 166;
    }

    public void render(GuiGraphics gui, int xMouse, int yMouse, float partialTicks) {
        this.renderBackground(gui);
        super.render(gui, xMouse, yMouse, partialTicks);
        this.renderTooltip(gui, xMouse, yMouse);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTicks, int x, int y) {
        int edgeSpacingX = (width - imageWidth) / 2;
        int edgeSpacingY = (height - imageHeight) / 2;
        gui.blit(TEXTURE, edgeSpacingX, edgeSpacingY, 0, 0, imageWidth, imageHeight);

        double progression = (double)menu.getSpinProgress() / SpinningWheelBlockEntity.SPIN_DURATION;

        int barSize = (int)Math.round(BAR_HEIGHT * Math.min(progression * 10.0D, 1.0D));
        if(menu.getSlot(1).hasItem())
            gui.blit(TEXTURE, leftPos + BAR0_XPOS, topPos + BAR_YPOS + BAR_HEIGHT - barSize, ICONS_U, BAR0_ICON_V + BAR_HEIGHT - barSize, BAR_WIDTH, barSize);
        if(menu.getSlot(2).hasItem())
            gui.blit(TEXTURE, leftPos + BAR1_XPOS, topPos + BAR_YPOS + BAR_HEIGHT - barSize, ICONS_U, BAR1_ICON_V + BAR_HEIGHT - barSize, BAR_WIDTH, barSize);

        if(progression > 0.1D) { // If over 10%, spin wheel

            int frame = (int)Math.round((progression - 0.1D) * 200) % 3;

            if(frame == 0)
                gui.blit(TEXTURE, leftPos + WHEEL_XPOS, topPos + WHEEL_YPOS, ICONS_U, WHEEL_FRAME2_V, WHEEL_SIZE, WHEEL_SIZE);
            else if(frame == 2)
                gui.blit(TEXTURE, leftPos + WHEEL_XPOS, topPos + WHEEL_YPOS, ICONS_U, WHEEL_FRAME1_V, WHEEL_SIZE, WHEEL_SIZE);
        }

    }

    @Override
    protected void renderLabels(GuiGraphics gui, int xMouse, int yMouse) {
        gui.drawString(font, title, (imageWidth / 2 - font.width(title) / 2), titleLabelY, Color.DARK_GRAY.getRGB(), false);
        gui.drawString(font, minecraft.player.getInventory().getDisplayName(), inventoryLabelX, inventoryLabelY, Color.DARK_GRAY.getRGB(), false);
    }

}
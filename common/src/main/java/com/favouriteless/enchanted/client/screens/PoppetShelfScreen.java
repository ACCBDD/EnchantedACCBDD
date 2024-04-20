package com.favouriteless.enchanted.client.screens;

import com.favouriteless.enchanted.Enchanted;
import com.favouriteless.enchanted.common.menus.PoppetShelfMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class PoppetShelfScreen extends AbstractContainerScreen<PoppetShelfMenu> {

    private static final ResourceLocation TEXTURE = Enchanted.location("textures/gui/poppet_shelf.png");

    public PoppetShelfScreen(PoppetShelfMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        this.imageWidth = 176;
        this.imageHeight = 131;
        this.inventoryLabelY = imageHeight - 94; // This is set in super constructor
    }

    @Override
    public void render(GuiGraphics gui, int xMouse, int yMouse, float partialTicks) {
        this.renderBackground(gui);
        super.render(gui, xMouse, yMouse, partialTicks);
        this.renderTooltip(gui, xMouse, yMouse);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int edgeSpacingX = (this.width - this.imageWidth) / 2;
        int edgeSpacingY = (this.height - this.imageHeight) / 2;
        gui.blit(TEXTURE, edgeSpacingX, edgeSpacingY, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics gui, int x, int y) {
        gui.drawString(font, title, (imageWidth / 2 - font.width(title) / 2), titleLabelY, Color.DARK_GRAY.getRGB());
    }

}

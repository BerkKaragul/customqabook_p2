package com.berk.customqabook_p2;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CustomBookScreen extends Screen {
    protected final Player player; // Changed to protected
    protected final ItemStack bookStack; // Changed to protected

        public CustomBookScreen(Player player, ItemStack bookStack, MutableComponent title) {
        super(Component.literal("Custom Book"));
        this.player = player;
        this.bookStack = bookStack;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        // Clear the background (or draw a custom one)
        this.renderBackground(graphics);

        // Draw the text
        int titleY = 20;
        int titleX = (this.width - this.font.width(Component.literal("Add your questions here:"))) / 2;
        graphics.drawString(this.font, Component.literal("Add your questions here:"), titleX, titleY, 0xFFFFFF);

        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;  // This screen does not pause the game
    }
}

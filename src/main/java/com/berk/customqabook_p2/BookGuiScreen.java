package com.berk.customqabook_p2;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.glfw.GLFW;

public class BookGuiScreen extends Screen {
    private EditBox questionInput;

    public BookGuiScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        int width = this.width / 2;
        int height = this.height / 4;
        this.questionInput = new EditBox(this.font, width - 100, height, 200, 20, Component.literal("Question"));
        this.addRenderableWidget(questionInput);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        int titleWidth = this.font.width(Component.literal("Custom Book GUI"));
        int titleX = (this.width - titleWidth) / 2;
        graphics.drawString(this.font, Component.literal("Custom Book GUI"), titleX, 15, 0xFFFFFF);
        this.questionInput.render(graphics, mouseX, mouseY, partialTicks);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ENTER && this.questionInput.isFocused()) {
            // Handle "Enter" key press
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
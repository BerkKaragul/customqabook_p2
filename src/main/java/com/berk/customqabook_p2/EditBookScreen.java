package com.berk.customqabook_p2;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EditBookScreen extends CustomBookScreen {
    private EditBox questionInput;
    private Button addQuestionButton;
    // ... other elements (e.g., for multiple choice options)

    public EditBookScreen(Player player, ItemStack bookStack) {
        super(player, bookStack, Component.literal("Edit Book"));
        System.out.println("EditBookScreen constructor called");
    }

    private void handleAddQuestion(Button button) {
        // 1. Get the question text from questionInput
        String questionText = questionInput.getValue();

        // 2. Create a CompoundTag to store question data
        CompoundTag questionTag = new CompoundTag();
        questionTag.putString("question", questionText);
        // ... Add other data to the tag based on answer type, options, etc.

        // 3. Get the bookStack's NBT and add the questionTag to the list
        CompoundTag nbt = bookStack.getOrCreateTag();
        ListTag questionList = nbt.getList("Questions", Tag.TAG_COMPOUND);
        questionList.add(questionTag);
        nbt.put("Questions", questionList);

        // 4. (Optional) Clear the questionInput for the next question
        questionInput.setValue("");
    }

    @Override
    protected void init() {
        super.init();

        // Use the provided width and height parameters
        int x = width / 2 - 100; // center horizontally
        int y = height / 4 + 20; // below the title
        int elementWidth = 200;
        int elementHeight = 20;

        this.questionInput = new EditBox(this.font, x, y, elementWidth, elementHeight, Component.literal("Enter question"));
        this.addRenderableWidget(questionInput);

        // ... (add other elements)

        int buttonY = y + height + 10; // Position below the questionInput
        this.addQuestionButton = Button.builder(Component.literal("Add Question"), this::handleAddQuestion)
                .bounds(x, buttonY, width, height)
                .build();
        this.addRenderableWidget(addQuestionButton);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics); // Make sure this line is present
        graphics.drawString(this.font, "Test Label", 10, 10, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, delta); // Call super.render at the end
    }

    // ... (Implement keyPressed and mouseClicked for handling input and interaction)
}
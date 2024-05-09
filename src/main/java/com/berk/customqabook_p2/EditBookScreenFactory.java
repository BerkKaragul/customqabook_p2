package com.berk.customqabook_p2;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class EditBookScreenFactory implements MenuScreens.ScreenConstructor {
    @Override
    public Screen create(AbstractContainerMenu menu, Inventory inventory, Component title) {
        if (menu instanceof EditBookContainer) {
            System.out.println("Creating EditBookScreen instance"); // Add this line
            return new EditBookScreen(inventory.player, ((EditBookContainer) menu).getBookStack());
        }
        return null; // Or throw an exception if the menu type is unexpected
    }
}
package com.berk.customqabook_p2;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import static com.berk.customqabook_p2.CustomQaBook_p2.EDIT_BOOK_MENU;

public class EditBookContainer extends AbstractContainerMenu {
    private final ItemStack bookStack;

    public ItemStack getBookStack() {
        return this.bookStack;
    }

    public EditBookContainer(int windowId, Inventory playerInventory, ItemStack bookStack) {
        super(EDIT_BOOK_MENU.get(), windowId);
        this.bookStack = bookStack;
        CompoundTag nbt = bookStack.getTag(); // Access the NBT data here
        // ... (Use the NBT data as needed)
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            ItemStack itemstack2 = itemstack1.copy();
            int containerSize = this.slots.size(); // Get the number of slots in the container
            if (index < containerSize) {
                if (!this.moveItemStackTo(itemstack1, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            return itemstack2;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true; // Or implement your own validation logic
    }
}
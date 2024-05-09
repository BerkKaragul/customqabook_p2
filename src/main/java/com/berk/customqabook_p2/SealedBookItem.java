package com.berk.customqabook_p2;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class SealedBookItem extends Item {

    public SealedBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            if (isSealed(itemStack)) {
                // Open a GUI to show the sealed book contents
                openSealedBookScreen((ServerPlayer) player, itemStack);
            } else {
                // Open a GUI to edit the book
                openBookGuiScreen((ServerPlayer) player, itemStack);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    private boolean isSealed(ItemStack stack) {
        // Check if the book is sealed based on its NBT data
        CompoundTag tag = stack.getTag();
        return tag != null && tag.getBoolean("Sealed");
    }

    private void openBookGuiScreen(ServerPlayer player, ItemStack stack) {
        // Simulated action of opening a GUI for editing the book
        player.sendSystemMessage(Component.literal("Book is editable. Opening GUI...").withStyle(ChatFormatting.GREEN));
    }

    private void openSealedBookScreen(ServerPlayer player, ItemStack stack) {
        // Simulated action of opening a GUI for viewing the sealed book
        player.sendSystemMessage(Component.literal("Book is sealed. Showing contents...").withStyle(ChatFormatting.GOLD));
    }
}
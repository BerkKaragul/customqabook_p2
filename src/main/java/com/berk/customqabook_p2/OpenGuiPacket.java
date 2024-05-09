package com.berk.customqabook_p2;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenGuiPacket {
    public static final int ANSWER_BOOK_SCREEN = 0;
    public static final int EDIT_BOOK_SCREEN = 1;

    private final int guiId;
    private final ItemStack stack;

    public OpenGuiPacket(int guiId, ItemStack stack) {
        this.guiId = guiId;
        this.stack = stack;
    }

    public static void encode(OpenGuiPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.guiId);
        buf.writeItemStack(msg.stack, false);
    }

    public static OpenGuiPacket decode(FriendlyByteBuf buf) {
        return new OpenGuiPacket(buf.readInt(), buf.readItem());
    }

    public static void handle(OpenGuiPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = msg.stack;
                if (msg.guiId == EDIT_BOOK_SCREEN) {
                    System.out.println("Received packet to open EditBookScreen"); // Add this line
                    player.openMenu(new SimpleMenuProvider(
                            (id, inventory, playerEntity) -> new EditBookContainer(id, inventory, stack),
                            stack.getHoverName()
                    ));
                    System.out.println("Opened EditBookContainer menu"); // Add this line
                } else if (msg.guiId == ANSWER_BOOK_SCREEN) {
                    // Open AnswerBookScreen (similar implementation)
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
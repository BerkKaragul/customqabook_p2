package com.berk.customqabook_p2;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class CustomBookItem extends Item {
    public CustomBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            MenuProvider containerProvider = new SimpleMenuProvider(
                    (id, inventory, playerEntity) -> new EditBookContainer(id, inventory, itemStack),
                    itemStack.getHoverName()
            );
            NetworkHooks.openScreen((ServerPlayer) player, containerProvider, buf -> buf.writeItemStack(itemStack, true));
            System.out.println("Sent packet to open EditBookScreen"); // Add this line
        }
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}
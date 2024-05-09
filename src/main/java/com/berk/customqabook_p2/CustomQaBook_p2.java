package com.berk.customqabook_p2;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(CustomQaBook_p2.MOD_ID)
public class CustomQaBook_p2 {
    public static final String MOD_ID = "customqabook";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);

    public static final RegistryObject<MenuType<EditBookContainer>> EDIT_BOOK_MENU =
            CONTAINERS.register("edit_book_menu",
                    () -> IForgeMenuType.create((windowId, inv, data) -> new EditBookContainer(windowId, inv, data.readItem())));

    public static final RegistryObject<Item> SEALED_BOOK = ITEMS.register("sealed_book", () ->
            new CustomBookItem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(SEALED_BOOK.get()))
            .displayItems((parameters, output) -> {
                output.accept(SEALED_BOOK.get());
            }).build());

    public CustomQaBook_p2()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Registering custom items, blocks, and menus for the mod
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        MENUS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this); // Oyun içi olay dinleyicisi kaydı
    }

    private void setup(final FMLCommonSetupEvent event) {
        System.out.println("Custom QA Book mod setup complete!");
    }
}

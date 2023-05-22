package net.julexar.testmod.item;

import net.julexar.testmod.TestMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);
    // Create an Item (duplicate to create another)
    public static final RegistryObject<Item> NAMEHERE = ITEMS.register("namehere", () ->
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.TESTMOD_TAB)
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

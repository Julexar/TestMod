package net.julexar.testmod.item;

import net.julexar.testmod.TestMod;
import net.julexar.testmod.block.ModBlocks;
import net.julexar.testmod.block.custom.JumpyBlock;
import net.julexar.testmod.item.custom.EightBall;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);
    // Create an Item (duplicate to create another)
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon", () ->
            new Item(
                    new Item.Properties().tab(ModCreativeModeTab.ITEM_TAB)
            )
    );
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon", () ->
        new Item(
                new Item.Properties().tab(ModCreativeModeTab.ITEM_TAB)
        )
    );

    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball", () ->
        new EightBall(
                new Item.Properties().tab(ModCreativeModeTab.ITEM_TAB)
        )
    );

    public static final RegistryObject<Item> BLUEBERRRY_SEEDS = ITEMS.register("blueberry_seeds", () ->
        new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CROP_TAB)
                )
    );

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry", () ->
        new Item(
                new Item.Properties()
                        .tab(ModCreativeModeTab.CROP_TAB)
                        .food(new FoodProperties.Builder()
                                .nutrition(2)
                                .saturationMod(2f)
                                .build()
                        )
        )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

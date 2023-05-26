package net.julexar.testmod.item;

import net.julexar.testmod.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab("testmod_items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ZIRCON.get());
        }
    };
    public static final CreativeModeTab BLOCK_TAB = new CreativeModeTab("testmod_blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.ZIRCON_BLOCK.get());
        }
    };

    public static final CreativeModeTab CROP_TAB = new CreativeModeTab("testmod_crops") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BLUEBERRRY_SEEDS.get());
        }
    };
}

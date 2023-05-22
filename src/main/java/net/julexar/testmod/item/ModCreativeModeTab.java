package net.julexar.testmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TESTMOD_TAB = new CreativeModeTab("testmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.NAMEHERE.get());
        }
    };
}

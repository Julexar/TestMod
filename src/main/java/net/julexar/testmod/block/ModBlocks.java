package net.julexar.testmod.block;

import net.julexar.testmod.TestMod;
import net.julexar.testmod.block.custom.BlueBerryCropBlock;
import net.julexar.testmod.block.custom.JumpyBlock;
import net.julexar.testmod.item.ModCreativeModeTab;
import net.julexar.testmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);
    // Create a Block (duplicate for more)
    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block", () ->
            new Block(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(6f)
                    .requiresCorrectToolForDrops()
            ),
            ModCreativeModeTab.BLOCK_TAB
    );
    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore", () ->
        new Block(BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(6f)
                .requiresCorrectToolForDrops()
        ),
        ModCreativeModeTab.BLOCK_TAB
    );
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore", () ->
        new Block(BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(6f)
                .requiresCorrectToolForDrops()
        ),
        ModCreativeModeTab.BLOCK_TAB
    );

    public static final RegistryObject<Block> ENDSTONE_ZIRCON_ORE = registerBlock("endstone_zircone_ore", () ->
        new Block(BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(6f)
                .requiresCorrectToolForDrops()
        ),
        ModCreativeModeTab.BLOCK_TAB
    );

    public static final RegistryObject<Block> NETHER_ZIRCON_ORE = registerBlock("nether_zircon_ore", () ->
        new Block(BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(6f)
                .requiresCorrectToolForDrops()
        ),
        ModCreativeModeTab.BLOCK_TAB
    );

    public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block", () ->
        new JumpyBlock(BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(6f)
        ),
        ModCreativeModeTab.BLOCK_TAB
    );

    public static final RegistryObject<Block> BLUEBERRY_CROP = BLOCKS.register("blueberry_crop", () ->
        new BlueBerryCropBlock(BlockBehaviour.Properties
                .copy(Blocks.WHEAT)
        )
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    };

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    };
}

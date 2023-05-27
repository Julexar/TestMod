package net.julexar.testmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.julexar.testmod.TestMod;
import net.julexar.testmod.block.ModBlocks;
import net.julexar.testmod.item.ModItems;
import net.julexar.testmod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 12);

            trades.get(1).add((trader, rand) ->
                new MerchantOffer(
                        new ItemStack(
                                Items.EMERALD,
                                2
                        ),
                        stack,
                        10,
                        8,
                        0.02F
                )
            );
        }

        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BLUEBERRY.get(), 15);
            ItemStack stack2 = new ItemStack(ModItems.BLUEBERRRY_SEEDS.get(), 30);

            trades.get(1).add((pTrader, pRandom) ->
                new MerchantOffer(
                        new ItemStack(
                                Items.EMERALD,
                                5
                        ),
                        stack,
                        10,
                        8,
                        0.02F
                )
            );
            trades.get(2).add((pTrader, pRandom) ->
                new MerchantOffer(
                        new ItemStack(
                                Items.EMERALD,
                                3
                        ),
                        stack2,
                        5,
                        8,
                        0.02F
                )
            );
        }

        if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModBlocks.JUMPY_BLOCK.get(), 1);

            trades.get(1).add((pTrader, pRandom) ->
                new MerchantOffer(
                        new ItemStack(
                                Items.EMERALD,
                                5
                        ),
                        stack,
                        5,
                        8,
                        0.02F
                )
            );
        }
    }
}

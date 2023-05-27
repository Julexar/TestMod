package net.julexar.testmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.julexar.testmod.TestMod;
import net.julexar.testmod.block.ModBlocks;
import net.julexar.testmod.item.ModItems;
import net.julexar.testmod.networking.ModMessages;
import net.julexar.testmod.networking.packet.ThirstDataSyncS2CPacket;
import net.julexar.testmod.thirst.PlayerThirst;
import net.julexar.testmod.thirst.PlayerThirstProvider;
import net.julexar.testmod.villager.ModVillagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
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
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(TestMod.MOD_ID, "properties"), new PlayerThirstProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerThirst.class);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                if (thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005F) {
                    thirst.remThirst(1);
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), ((ServerPlayer) event.player));
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            }
        }
    }
}

package net.julexar.testmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EightBall extends Item {
    public EightBall(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pUsedHand == InteractionHand.MAIN_HAND) {
            outputNumber(pPlayer);
            pPlayer.getCooldowns().addCooldown(this,20);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.literal("Right click to get a random number between 1 and 8").withStyle(ChatFormatting.AQUA));
        } else {
            pTooltipComponents.add(Component.literal("Press SHIFT for more Info").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputNumber(Player player) {
        player.sendSystemMessage(Component.literal("" + getRand()));
    }

    private int getRand() {
        return RandomSource.createNewThreadLocalInstance().nextInt(8)+1;
    }
}

package net.julexar.testmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

    private void outputNumber(Player player) {
        player.sendSystemMessage(Component.literal("" + getRand()));
    }

    private int getRand() {
        return RandomSource.createNewThreadLocalInstance().nextInt(8)+1;
    }
}

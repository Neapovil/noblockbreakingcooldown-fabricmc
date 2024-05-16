package com.github.neapovil.noblockbreakingcooldown.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.github.neapovil.noblockbreakingcooldown.NoBlockBreakingCooldown;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin
{
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private int blockBreakingCooldown;

    @Redirect(method = "updateBlockBreakingProgress",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I",
                    opcode = Opcodes.PUTFIELD))
    private void updateBlockBreakingProgress(ClientPlayerInteractionManager interactionManager, int value)
    {
        if (this.flag())
        {
            value = 0;
        }

        this.blockBreakingCooldown = value;
    }

    @Redirect(method = "attackBlock",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I",
                    opcode = Opcodes.PUTFIELD))
    private void attackBlock(ClientPlayerInteractionManager interactionManager, int value)
    {
        if (this.flag())
        {
            value = 0;
        }

        this.blockBreakingCooldown = value;
    }

    private boolean flag()
    {
        final NoBlockBreakingCooldown mod = NoBlockBreakingCooldown.instance();
        return mod.config().enabled;
    }
}

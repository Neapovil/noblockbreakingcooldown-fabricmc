package com.github.neapovil.noblockbreakingcooldown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public final class NoBlockBreakingCooldown implements ClientModInitializer
{
    public static final Logger LOGGER = LoggerFactory.getLogger("NoBlockBreakingCooldown");
    private static NoBlockBreakingCooldown instance;

    @Override
    public void onInitializeClient()
    {
        instance = this;

        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }

    public static NoBlockBreakingCooldown instance()
    {
        return instance;
    }

    public ModConfig config()
    {
        final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        return config;
    }
}

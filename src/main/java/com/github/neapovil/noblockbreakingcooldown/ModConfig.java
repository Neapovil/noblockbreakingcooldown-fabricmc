package com.github.neapovil.noblockbreakingcooldown;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "noblockbreakingcooldown")
public class ModConfig implements ConfigData
{
    @ConfigEntry.Category("general")
    public boolean enabled = true;
}

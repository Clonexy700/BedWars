package com.clonexy.bedwars;

import com.clonexy.bedwars.commands.BedWarsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public String prefix = "§7[§dBedWars§7] §r";

    @Override
    public void onEnable() {
        initCommands();
    }

    @Override
    public void onDisable() {

    }

    public void initCommands() {
        new BedWarsCommand(this);
    }
}

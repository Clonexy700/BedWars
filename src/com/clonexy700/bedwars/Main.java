package com.clonexy700.bedwars;

import com.clonexy700.bedwars.commands.BedWarsCommand;
import com.clonexy700.bedwars.listener.*;
import com.clonexy700.bedwars.utils.GameState;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public String prefix = "§7[§dBedWars§7] §r";

    @Override
    public void onEnable() {
        initCommands();
        initListener();
        GameState.setGameState(GameState.LOBBY);
    }

    @Override
    public void onDisable() {

    }

    public void initCommands() {
        new BedWarsCommand(this);
    }
    public void initListener() {
        new BuildListener(this);
        new EntityDamageListener(this);
        new FoodLevelChangeListener(this);
        new InteractListener(this);
        new JoinListener(this);
        new WeatherChangeListener(this);
    }
}


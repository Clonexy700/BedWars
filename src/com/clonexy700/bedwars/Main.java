package com.clonexy700.bedwars;

import com.clonexy700.bedwars.commands.BedWarsCommand;
import com.clonexy700.bedwars.listener.*;
import com.clonexy700.bedwars.utils.VillagerGUI;
import com.clonexy700.bedwars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public String prefix = "§7[§dBedWars§7] §r";

    public ScoreboardUtil scoreboardUtil;
    public StatsManager statsManager;
    public MySQL mySQL;

    public static VillagerGUI villager;

    public List<ItemSpawner> itemSpawners = new ArrayList<>();

    @Override
    public void onEnable() {
        initCommands();
        initListener();
        initConfig();
        initWorlds();
        villager = new VillagerGUI();
        GameState.setGameState(GameState.LOBBY);
        scoreboardUtil = new ScoreboardUtil(this);
        mySQL = new MySQL(this, getConfig().getString("mysql.username")
                , getConfig().getString("mysql.password"),
                getConfig().getString("mysql.host"), getConfig().getString("mysql.database"));
        statsManager = new StatsManager(this);
        statsManager.setup();

        Bukkit.getOnlinePlayers().forEach(p -> JoinListener.join(this, p));

        getConfig().addDefault("spawner.BRONZE", new ArrayList<>());
        getConfig().addDefault("spawner.GOLD", new ArrayList<>());
        getConfig().addDefault("spawner.IRON", new ArrayList<>());
        getConfig().options().copyDefaults(true);

        getConfig().getList("spawner.BRONZE").forEach(o -> new ItemSpawner((Location) o, ItemSpawner.Type.BRONZE));
        getConfig().getList("spawner.GOLD").forEach(o -> new ItemSpawner((Location) o, ItemSpawner.Type.GOLD));
        getConfig().getList("spawner.IRON").forEach(o -> new ItemSpawner((Location) o, ItemSpawner.Type.IRON));

    }

    @Override
    public void onDisable() {

    }

    public void initConfig() {
        getConfig().addDefault("mysql.host", "127.0.0.1");
        getConfig().addDefault("mysql.username", "username");
        getConfig().addDefault("mysql.password", "password");
        getConfig().addDefault("mysql.database", "bedwars");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void initCommands() {
        new BedWarsCommand(this);
    }
    public void initListener() {
        new BuildListener(this);
        new EntityDamageListener(this);
        new FoodLevelChangeListener(this);
        new InteractListener(this);
        new InventoryClickListener(this);
        new JoinListener(this);
        new PlayerItemDropListener(this);
        new WeatherChangeListener(this);
        new EntityInteractListener(this);
        new PlayerDeathListener(this);
    }

    public void initWorlds() {
        if (getConfig().get("location.spawn.1") != null) {
            Bukkit.getServer().createWorld(new WorldCreator(getConfig().getString("location.spawn.1.world")).generateStructures(false));
            Bukkit.getServer().getWorlds().add(Bukkit.getWorld(getConfig().getString("location.spawn.1.world")));
        }
        if (getConfig().get("location.lobby") != null) {
            Bukkit.getServer().createWorld(new WorldCreator(getConfig().getString("location.lobby.world")).generateStructures(false));
            Bukkit.getServer().getWorlds().add(Bukkit.getWorld(getConfig().getString("location.lobby.world")));
        }
    }
}


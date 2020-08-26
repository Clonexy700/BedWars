package com.clonexy.bedwars.listener;

import com.clonexy.bedwars.Main;
import com.clonexy.bedwars.utils.LobbyCountdown;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private Main main;
    public JoinListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void  onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(main.prefix + "§d" + e.getPlayer().getName() + "§7Joined to the game");
        try {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().getArmorContents();
            e.getPlayer().updateInventory();
            e.getPlayer().setExp(0);
            e.getPlayer().setFoodLevel(20);
            e.getPlayer().setHealth(20);
            e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            e.getPlayer().setAllowFlight(false);
            e.getPlayer().setLevel(0);
            e.getPlayer().teleport((Location) main.getConfig().get("location.lobby"));

            LobbyCountdown.start(false);
        } catch (NullPointerException e1) {
            e.getPlayer().sendMessage(main.prefix + "§cThe BedWars plugin is not yet fully set up");
        }
    }
}

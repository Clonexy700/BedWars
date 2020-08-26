package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Set;

public class InteractListener implements Listener {
    private Main main;

    public InteractListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.BED) {
                Set<String> teams = main.getConfig().getConfigurationSection("location.spawn").getKeys(false);
                int size = 9;
                if (teams.size() <= 9) {
                    size = 9;
                } else if (teams.size() <= 18) {
                    size = 18;
                } else if (teams.size() <= 27) {
                    size = 27;
                }
                Inventory inv = Bukkit.createInventory(null, size, "§6Choose your team");
            }
        }
    }
}

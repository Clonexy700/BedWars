package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameManager;
import com.clonexy700.bedwars.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    private Main main;

    public InventoryClickListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getClickedInventory().getName().equals("§6Choose your team")) {
                if (e.getCurrentItem() != null) {
                    String team = e.getCurrentItem().getItemMeta().getDisplayName().substring(9);
                    if (GameManager.getTeamPlayers(team).contains(e.getWhoClicked())) {
                        GameManager.setPlayerTeam((Player) e.getWhoClicked(), team);
                    }
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(main.prefix + "You are in §bTeam #0" + team);
                }
                e.setCancelled(true);
            }
        }
    }
}

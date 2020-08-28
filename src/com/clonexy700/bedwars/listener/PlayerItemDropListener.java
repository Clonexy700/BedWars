package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerItemDropListener implements Listener {
    private Main main;

    public PlayerItemDropListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    public static List<Entity> entities = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerDropItemEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            e.setCancelled(true);
        } else {
            entities.add(e.getItemDrop());
        }
    }
}

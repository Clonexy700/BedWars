package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {
    private Main main;

    public BuildListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
                e.setCancelled(true);
        }
    }
}

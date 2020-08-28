package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameManager;
import com.clonexy700.bedwars.utils.GameState;
import com.clonexy700.bedwars.utils.PlacedBlock;
import com.clonexy700.bedwars.utils.ScoreboardUtil;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Bed;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuildListener implements Listener {
    private Main main;

    public static HashMap<Byte, Location> bedList = new HashMap<>();

    public BuildListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    boolean b = false;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                e.setCancelled(true);
            }
        } else {
            b = false;
            if (e.getBlock().getType() == Material.GLASS) {
                e.setCancelled(false);
                bedList.put(e.getBlock().getState().getRawData(), e.getBlock().getLocation().clone());
                int teamid = GameManager.removeBed(e.getBlock().getLocation());
                if (GameManager.getPlayerTeam(e.getPlayer()).equalsIgnoreCase(teamid + "")) {
                    e.getPlayer().sendMessage(main.prefix + "§dYou cannot break your bed!");
                    e.setCancelled(true);
                    return;
                }
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.playSound(e.getBlock().getLocation(), Sound.ENTITY_WITHER_DEATH, 3, 1);
                    p.sendMessage(main.prefix + "§cBed of"
                            + ScoreboardUtil.getColor(teamid + "")
                            + "§bTeam#0" + teamid + "§c was destroyed!");
                });
                return;
            }
            try {
                for (PlacedBlock placedBlock : GameManager.placedBlock) {
                    if (placedBlock.getLocation().equals(e.getBlock().getLocation())) {
                        b = true;
                        GameManager.placedBlock.remove(placedBlock);
                    }
                }
            } catch (Exception e1) {

            }
            if (!b) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                e.setCancelled(true);
            }
        } else {
            GameManager.placedBlock.add(new PlacedBlock(e.getBlockPlaced().getLocation(),
                    Material.AIR, (byte) 0));
        }
    }
}

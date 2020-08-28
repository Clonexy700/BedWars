package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameManager;
import com.clonexy700.bedwars.utils.GameState;
import com.clonexy700.bedwars.utils.LocationUtil;
import com.clonexy700.bedwars.utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeathListener implements Listener {

    private Main main;

    public PlayerDeathListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            if (GameState.getGameState() == GameState.INGAME) {
                if (GameManager.isBedAlive(e.getEntity())) {
                    if (e.getEntity().getKiller() == null) {
                        e.setDeathMessage(Main.getPlugin(Main.class).prefix + "§7" + e.getEntity().getName() + "§ddead! §7");
                    } else {
                        e.setDeathMessage(Main.getPlugin(Main.class).prefix + "§7" + e.getEntity().getName() + "§dwas killed by §7" +
                                e.getEntity().getKiller().getName());
                    }
                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
                        @Override
                        public void run() {
                            e.getEntity().spigot().respawn();


                            GameManager.checkStop();
                        }
                    }, 5);
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (GameManager.isBedAlive(e.getPlayer())) {
            e.setRespawnLocation(LocationUtil.get("location.spawn." +
                    GameManager.getPlayerTeam(e.getPlayer()), Main.getPlugin(Main.class)));
            GameManager.alive.remove(e.getPlayer());
        } else {
            GameManager.alive.remove(e.getPlayer());
            Player p = e.getPlayer();
            Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(p));
            p.setGameMode(GameMode.SPECTATOR);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.setExp(0);
            p.setLevel(0);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            p.updateInventory();
            e.setRespawnLocation(LocationUtil.get("location.spectator", Main.getPlugin(Main.class)));
        }
    }
}

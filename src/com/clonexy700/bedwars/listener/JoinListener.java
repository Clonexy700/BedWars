package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    public class JoinListener implements Listener {
        private Main main;
        public JoinListener(Main main) {
            this.main = main;
            Bukkit.getPluginManager().registerEvents(this, main);
        }

    public static void join(Main main, Player p) {
        main.statsManager.setupPlayer(Objects.requireNonNull(UUIDFetcher.getUniqueId(p.getName())).toString());
        try {
            p.teleport(LocationUtil.get("location.lobby", main));
            p.getPlayer().setGameMode(GameMode.SURVIVAL);
            p.getPlayer().getInventory().clear();
            p.getPlayer().getInventory().getArmorContents();
            p.getPlayer().updateInventory();
            p.getPlayer().setExp(0);
            p.getPlayer().setFoodLevel(20);
            p.getPlayer().setHealth(20);
            p.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            p.getPlayer().setAllowFlight(false);
            p.getPlayer().setLevel(0);

            LobbyCountdown.start(false);

            main.scoreboardUtil.setTeam(p.getPlayer().getName(), "001default");

            p.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.BED).name("§6Team choose").build());
            p.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.NETHER_STAR).name("§6Statistics").build());

        } catch (NullPointerException e1) {
            p.getPlayer().sendMessage(main.prefix + "§cThe BedWars plugin is not yet fully set up");
        }
    }

    @EventHandler
    public void  onJoin(PlayerJoinEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            e.setJoinMessage(main.prefix + "§d" + e.getPlayer().getName() + "§7Joined to the game");
            join(main, e.getPlayer());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (LobbyCountdown.isStarted) {
                if (Bukkit.getOnlinePlayers().size() <= 4) {
                    Bukkit.getScheduler().cancelTask(LobbyCountdown.sched);
                }
            }
        } else {
            GameManager.checkStop();
        }
    }
}

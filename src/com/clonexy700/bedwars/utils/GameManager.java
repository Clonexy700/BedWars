package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.listener.BuildListener;
import com.clonexy700.bedwars.listener.PlayerItemDropListener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    public static List<PlacedBlock> placedBlock = new ArrayList<>();
    public static HashMap<String, List<Player>> playersinTeam = new HashMap<>();
    public static int time = 60*90;

    private static int sched;

    public static List<Player> alive = new ArrayList<>();
    private static HashMap<Integer, Location> spawnLocations = new HashMap<>();

    private static Main main = Main.getPlugin(Main.class);

    public static void start() {
        GameState.setGameState(GameState.INGAME);
        setRandomTeam();

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.teleport(LocationUtil.get("location.spawn." + getPlayerTeam(all), Main.getPlugin(Main.class)));
            spawnLocations.put(Integer.parseInt(getPlayerTeam(all)),
                    LocationUtil.get("location.spawn." + getPlayerTeam(all), Main.getPlugin(Main.class)));
            alive.add(all);
            all.setGameMode(GameMode.SURVIVAL);
            all.getInventory().clear();
            all.getInventory().getArmorContents();
            all.updateInventory();
            all.setExp(0);
            all.setFoodLevel(20);
            all.setHealth(20);
            all.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            all.setAllowFlight(false);
            all.setLevel(0);
        }
        main.itemSpawners.forEach(ItemSpawner::startSpawning);


        GameState.setGameState(GameState.INGAME);
        Main.getPlugin(Main.class).scoreboardUtil.showSidebar();

        sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                Main.getPlugin(Main.class).scoreboardUtil.scoreboard.resetScores("§eTime: " + getTime());
                time--;
                Main.getPlugin(Main.class).scoreboardUtil.getObjective().getScore("§eTime: " + getTime()).setScore(10);

                if (time == 0) {

                }
            }
        }, 20, 20);
    }

    public static void checkStop() {
        List<Team> teams = new ArrayList<>();
        GameManager.alive.forEach(p -> {
            teams.add(main.scoreboardUtil.scoreboard.getEntryTeam(p.getName()));
        });
        if (teams.size() <= 1) {
            GameManager.stop();
            String id = teams.get(0).getDisplayName().substring(5);
            Bukkit.getOnlinePlayers().forEach(p -> {
                p.sendMessage(main.prefix + "§aTeam §e" + "Team #0" + id + " §a Win the game!");
            });
        }
    }

    public static void stop() {
        Bukkit.getScheduler().cancelTask(sched);
        resetMap();
        main.itemSpawners.forEach(ItemSpawner::stopSpawning);
        PlayerItemDropListener.entities.forEach(Entity::remove);
        BuildListener.bedList.forEach((data, location) -> {
            location.getBlock().setType(Material.GLASS);

            BlockState state = location.getBlock().getState();
            state.setType(Material.BED_BLOCK);

            if (data == (byte) 0x0) {
                Block block = location.clone().add(1, 0, 0).getBlock();
                block.setType(Material.BED_BLOCK);
            }

            state.setRawData(data);
            state.update(true, true);

        });
        Bukkit.getOnlinePlayers().forEach(p -> {
            Bukkit.getOnlinePlayers().forEach(p1 -> {
                p1.showPlayer(p);
                p.showPlayer(p1);
            });
            p.teleport(LocationUtil.get("location.lobby", Main.getPlugin(Main.class)));
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.updateInventory();
            p.setExp(0);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            p.setAllowFlight(false);
            p.setLevel(0);
        });
    }

    public static void setPlayerTeam(Player p, String team) {
        for (String s: playersinTeam.keySet()) {
            if (playersinTeam.get(s).contains(p)) {
                playersinTeam.remove(p);
            }
        }

        List<Player> list = new ArrayList<>();
        if (playersinTeam.containsKey(team)) {
            list = playersinTeam.get(team);
        }
        list.add(p);
        playersinTeam.put(team, list);

        Main.getPlugin(Main.class).scoreboardUtil.setTeam(p.getName(), team);
    }
    public static List<Player> getTeamPlayers(String team) {
        return playersinTeam.get(team);
    }

    public static String getPlayerTeam(Player p) {
        for (String team : playersinTeam.keySet()) {
            if (playersinTeam.get(team).contains(p)) {
                return team;
            }
        }
        return null;
    }

    public static void setRandomTeam() {
        Main main = Main.getPlugin(Main.class);
        for (Player p: Bukkit.getOnlinePlayers()) {
            boolean inTeam = false;
            for (Team team : main.scoreboardUtil.teams.values()) {
                if(team.hasEntry(p.getName())) {
                    inTeam = true;
                    continue;
                }
            }
            if (!inTeam) {
                int smallest_count = 200;
                Team smallest_team = null;
                for (Team team : main.scoreboardUtil.teams.values()) {
                    if (team.getEntries().size() < smallest_count) {
                        smallest_count = team.getEntries().size();
                        smallest_team = team;
                    }
                }
                setPlayerTeam(p, smallest_team.getName().substring(5));
            }
        }
    }
    public static void resetMap() {
        placedBlock.forEach(block -> {
            block.getLocation().getBlock().setType(block.getMaterial());
            block.getLocation().getBlock().setData(block.getData());
        });
    }
    public static String getTime() {
        int sec = time;
        int min = 0;
        while (sec >= 60) {
            sec -= 60;
            min++;
        }
        if (String.valueOf(sec).split("").length == 1) {
            return min + ":0" + sec;
        } else {
            return min + ":" + sec;
        }
    }

    private static List<Integer> removedBeds = new ArrayList<>();

    public static boolean isBedAlive(Player p) {
        if (removedBeds.contains(Integer.parseInt(getPlayerTeam(p)))) {
            return false;
        }
        return true;
    }

    public static boolean isBedAlive(int id) {
        if (removedBeds.contains(id)) {
            return false;
        }
        return true;
    }

    public static int removeBed(Location location) {
        int r = 0;
        for (Integer i : spawnLocations.keySet()) {
            if (spawnLocations.get(i).distance(location) <= 20) {
                removedBeds.add(i);
                r = i;
            }
        }

        Main.getPlugin(Main.class).scoreboardUtil.scoreboard.resetScores("§a✓" + ScoreboardUtil.getColor(r + "") + "Team #0" + r);
        int size = ScoreboardUtil.teams.get("00000" + r).getEntries().size();
        Main.getPlugin(Main.class).scoreboardUtil.scoreboard.getObjective(DisplaySlot.SIDEBAR)
                .getScore("§a❌" + ScoreboardUtil.getColor(r + "")
                        + "Team #0" + r).setScore(size);


        return r;
    }

}

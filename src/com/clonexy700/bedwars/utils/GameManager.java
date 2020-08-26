package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    public static HashMap<String, List<Player>> playersinTeam = new HashMap<>();

    public static void start() {
        GameState.setGameState(GameState.INGAME);
        setRandomTeam();

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.teleport((Location) Main.getPlugin(Main.class).getConfig()
                    .get("location.spawn." + getPlayerTeam(all)));
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
}

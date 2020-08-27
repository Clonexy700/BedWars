package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// What is ahegao? Is what a type of cheese?
public class ScoreboardUtil {
    private Main main;
    public Scoreboard scoreboard;
    public HashMap<String, Team> teams = new HashMap<>();

    public ScoreboardUtil(Main main) {
        this.main = main;
        generate();
    }

    public void generate() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("dummy", "teams");
        scoreboard.registerNewTeam("001default").setPrefix("§8");
        try {
            for (String teamnumber: main.getConfig().getConfigurationSection("location.spawn").getKeys(false)) {
                Team team = scoreboard.registerNewTeam("00000" + teamnumber);
                team.setPrefix(ChatColor.valueOf(
                        main.getConfig().getString("location.spawn." + teamnumber + ".color"))
                        + "Team #0" + teamnumber + " | ");
                team.setSuffix(ChatColor.valueOf(
                        main.getConfig().getString("location.spawn" + teamnumber + ".color")) + "");
                team.setAllowFriendlyFire(false);
                teams.put(teamnumber, team);
                GameManager.playersinTeam.put(teamnumber, new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showSidebar();
    }

    public void show (Player p) {
        p.setScoreboard(scoreboard);
    }

    public void setTeam(String playername, String teamname) {
        scoreboard.getTeam(teamname).addEntry(playername);
        Bukkit.getOnlinePlayers().forEach(p -> p.setScoreboard(scoreboard));
    }

    Objective objective;

    public void showSidebar() {
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);
        objective = scoreboard.registerNewObjective(System.currentTimeMillis() + "", "teams");
        objective.setDisplayName("§6BedWars");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (GameState.getGameState() == GameState.LOBBY) {
            objective.getScore(" ").setScore(3);
            objective.getScore("§eMap").setScore(2);
            try {
                objective.getScore(LocationUtil.get("location.spawn.1", main)
                        .getWorld().getName().toUpperCase()).setScore(1);
            } catch (NullPointerException e) {

            }
        } else {
            List<Team> allteams = new ArrayList<>(teams.values());
            for (int i = 0; i<allteams.size(); i++) {
                int size = allteams.get(i).getEntries().size();
                if (size != 0) {
                    objective.getScore("§a✓" + ChatColor.valueOf(
                            main.getConfig().getString("location.spawn." + allteams.get(i).getDisplayName().substring(5) + ".color"))
                            + "Team #0" + allteams.get(i).getDisplayName().substring(5)).setScore(size);
                } else {
                    objective.getScore("§a❌" + ChatColor.valueOf(
                            main.getConfig().getString("location.spawn." + allteams.get(i).getDisplayName().substring(5) + ".color"))
                            + "Team #0" + allteams.get(i).getDisplayName().substring(5)).setScore(size);
                }
            }
            objective.getScore("§eTime: " + GameManager.getTime()).setScore(10);
            objective.getScore(" ").setScore(9);
        }
    }

    public Objective getObjective() {
        return objective;
    }

}

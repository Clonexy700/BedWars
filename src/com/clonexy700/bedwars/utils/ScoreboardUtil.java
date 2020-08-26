package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// What is ahegao? Is what a type of cheese?
public class ScoreboardUtil {
    private Main main;
    private Scoreboard scoreboard;
    private HashMap<String, Team> teams = new HashMap<>();

    public ScoreboardUtil(Main main) {
        this.main = main;
        generate();
    }

    public void generate() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("dummy", "teams");
        scoreboard.registerNewTeam("001default").setPrefix("§7");
        for (String teamnumber: main.getConfig().getConfigurationSection("location.spawn").getKeys(false)) {
            Team team = scoreboard.registerNewTeam("00000" + teamnumber);
            team.setPrefix("§bTeam #0"+ teamnumber + " | ");
            team.setAllowFriendlyFire(false);
            teams.put(teamnumber, team);
            GameManager.playersinTeam.put(teamnumber, new ArrayList<Player>());
        }
    }

    public void show (Player p) {
        p.setScoreboard(scoreboard);
    }

    public void setTeam(String playername, String teamname) {
        scoreboard.getTeam(teamname).addEntry(playername);
        Bukkit.getOnlinePlayers().forEach(p -> p.setScoreboard(scoreboard));
    }
}

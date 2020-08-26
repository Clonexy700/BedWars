package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    public static HashMap<String, List<Player>> playersinTeam = new HashMap<>();

    public static void start() {
        GameState.setGameState(GameState.INGAME);
    }

    public static void setPlayerTeam(Player p, String team) {
        List<Player> list = new ArrayList<>();
        for (String s: playersinTeam.keySet()) {
            if (playersinTeam.get(s).contains(p)) {
                playersinTeam.remove(p);
            }
        }
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
}

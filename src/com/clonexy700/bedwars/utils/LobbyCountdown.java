package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;

public class LobbyCountdown {

    public static boolean isStarted = false;
    static int count = 60;
    public static int sched;

    public static void start(boolean force) {
        if (GameState.getGameState() != GameState.LOBBY) {
            return;
        }
        if (force || Bukkit.getOnlinePlayers().size() == 4) {
            if (!isStarted) {
                if (force) {
                    count = 5;
                }
                sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(count));

                        if (count==60 || count == 45 || count == 30 || count == 15 || count <= 5 && count != 0){
                            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix
                            + "§dGame starts in $8" + count + "seconds§d!"));

                        } else if (count==0) {
                            Bukkit.getScheduler().cancelTask(sched);
                            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix
                                    + "§dGame started!"));
                            GameManager.start();
                            return;
                        }
                        count--;
                    }
                }, 20, 20);
            }
        }
    }
}

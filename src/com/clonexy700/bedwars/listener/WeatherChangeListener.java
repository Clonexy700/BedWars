package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
    private Main main;

    public WeatherChangeListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
        e.getWorld().setStorm(false);
        e.getWorld().setWeatherDuration(0);
        e.getWorld().setThundering(false);
    }
}

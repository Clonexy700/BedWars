package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

    public static void save(String path, Location location, Main main) {
        main.getConfig().set(path + ".world", location.getWorld().getName());
        main.getConfig().set(path + ".x", location.getX());
        main.getConfig().set(path + ".y", location.getY());
        main.getConfig().set(path + ".z", location.getZ());
        main.getConfig().set(path + ".yaw", location.getYaw());
        main.getConfig().set(path + ".pitch", location.getPitch());
        main.saveConfig();
    }

    public static Location get(String path, Main main) {
        String world = main.getConfig().getString(path + ".world");
        double x = main.getConfig().getDouble(path + ".x");
        double y = main.getConfig().getDouble(path + ".y");
        double z = main.getConfig().getDouble(path + ".z");
        Float yaw = Float.valueOf(main.getConfig().getString(path + ".yaw"));
        Float pitch = Float.valueOf(main.getConfig().getString(path + ".pitch"));

        return new Location(Bukkit.getWorld(world), x, y, z ,yaw, pitch);
    }
}

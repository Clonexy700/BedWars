package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Location;

public class setSpawn {
    private static int i = 0;
    public static int set(Location location, Main main) {
        i++;
        LocationUtil.save("location.spawn." + i, location, main);
        return i;
    }
}

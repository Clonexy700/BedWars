package com.clonexy700.bedwars.utils;

import org.bukkit.Location;
import org.bukkit.Material;

public class PlacedBlock {
    private final Location location;
    private final Material material;
    private final byte data;

    public PlacedBlock(Location location, Material material, byte data) {
        this.location = location;
        this.material = material;
        this.data = data;
    }

    public byte getData() {
        return data;
    }

    public Location getLocation() {
        return location;
    }

    public Material getMaterial() {
        return material;
    }
}

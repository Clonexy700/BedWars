package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class ItemSpawner {

    private int sched;
    private Material material;
    private Location location;
    private Type type;
    private List<Entity> entities = new ArrayList<>();

    public enum Type {
        BRONZE(Material.CLAY_BRICK, "§aBronze", 20),
        GOLD(Material.GOLD_INGOT, "§aGold", 30*20),
        IRON(Material.IRON_INGOT, "§aIron", 15*20);

        private Material material;
        private String name;
        private int time;

        Type(Material material, String name, int time) {
            this.material = material;
            this.name = name;
            this.time = time;
        }

        public Material getMaterial() {
            return material;
        }

        public String getName() {
            return name;
        }

        public int getTime() {
            return time;
        }
    }

    public ItemSpawner (Location location, Type type) {
        this.location = location;
        this.type = type;
        Main.getPlugin(Main.class).itemSpawners.add(this);
    }

    public void startSpawning() {
        sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                Entity entity = location.getWorld().dropItemNaturally(
                        location, new ItemBuilder(type.getMaterial()).name(type.getName()).build());
                entities.add(entity);
            }
        }, 20, type.getTime());
    }

    public void stopSpawning() {
        Bukkit.getScheduler().cancelTask(sched);
        entities.forEach(entity -> entity.remove());
    }

    public void save() {
        Main main = Main.getPlugin(Main.class);

        List<Location> list = (List<Location>) main.getConfig().getList("spawner." + type.toString());

        list.add(this.location);

        main.getConfig().set("spawner." + this.type.toString(), list);
        main.saveConfig();
    }
}

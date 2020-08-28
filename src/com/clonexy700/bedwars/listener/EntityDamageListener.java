package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    private Main main;

    public EntityDamageListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getEntity() instanceof Player) {
                if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    e.getEntity().teleport((Location) main.getConfig().get("location.lobby"));
                }
            }
            e.setCancelled(true);
        } else {
            if (e.getEntity() instanceof Villager) {
                if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                        e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ) {
                    e.setCancelled(true);
                }
            }
        }
    }
}

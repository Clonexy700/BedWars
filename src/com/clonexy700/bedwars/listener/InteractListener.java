package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.GameManager;
import com.clonexy700.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class InteractListener implements Listener {
    private Main main;

    public InteractListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.BED) {
                Set<String> teams = main.getConfig().getConfigurationSection("location.spawn").getKeys(false);
                int size = 9;
                if (teams.size() <= 9) {
                    size = 9;
                } else if (teams.size() <= 18) {
                    size = 18;
                } else if (teams.size() <= 27) {
                    size = 27;
                }
                Inventory inv = Bukkit.createInventory(null, size, "§6Choose your team");

                for (String s: teams) {
                    try {
                        byte subid = 5;
                        if (GameManager.getTeamPlayers(s).size() == main.getConfig().getInt("")) {
                            subid = 14;
                        }
                        ItemBuilder itemBuilder = new ItemBuilder(new ItemStack(Material.WOOL, 1, subid))
                                .name(s);
                        itemBuilder.lore("");
                        for (int i = 0; i < main.getConfig().getInt("location.teamsize"); i++) {
                            try {
                                itemBuilder.lore("§a" + GameManager.playersinTeam.get(s).get(i).getName());
                            } catch (Exception e1) {
                                itemBuilder.lore("§Empty");
                            }
                        }
                        inv.setItem(Integer.parseInt(s), itemBuilder.build());
                    } catch (NullPointerException e1) {
                        e.getPlayer().sendMessage(main.prefix + "§cError happened! §7[Code: #1]");
                    }
                }

                e.getPlayer().openInventory(inv);
            }
        }
    }
}

package com.clonexy700.bedwars.listener;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.VillagerGUI;
import com.clonexy700.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class EntityInteractListener implements Listener {

    private Main main;
    private Inventory inventory;

    public EntityInteractListener(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
        createInventory();
    }

    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent e) {
            if (e.getRightClicked() instanceof VillagerGUI) {
                e.setCancelled(true);
                e.getPlayer().closeInventory();
                e.getPlayer().openInventory(inventory);
        }
    }

    public void createInventory() {
        inventory = Bukkit.createInventory(null, 9, "§6Shop");
        inventory.addItem(new ItemBuilder(Material.HARD_CLAY).name("Blocks").build());
        inventory.addItem(new ItemBuilder(Material.DIAMOND_SWORD).name("Weapons").build());
        inventory.addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("Armor").build());
        inventory.addItem(new ItemBuilder(Material.CHEST).name("Chest").build());
        inventory.addItem(new ItemBuilder(Material.BOW).name("Bow").build());
        inventory.addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).name("Pickaxe").build());
        inventory.addItem(new ItemBuilder(Material.POTION).name("Potions").build());
        inventory.addItem(new ItemBuilder(Material.APPLE).name("Food").build());
        inventory.addItem(new ItemBuilder(Material.EMERALD).name("Special").build());

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equalsIgnoreCase("§6shop")) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
            Player p = (Player) e.getWhoClicked();
            switch (e.getCurrentItem().getType()) {
                case HARD_CLAY:
                    new VillagerGUI().openBlocks(p);
                case DIAMOND_SWORD:
                    new VillagerGUI().openWeapons(p);
                case DIAMOND_CHESTPLATE:
                    new VillagerGUI().openArmor(p);
                case CHEST:
                    new VillagerGUI().openChests(p);
                case BOW:
                    new VillagerGUI().openBows(p);
                case DIAMOND_PICKAXE:
                    new VillagerGUI().openPickaxes(p);
                case POTION:
                    new VillagerGUI().openPotions(p);
                case APPLE:
                    new VillagerGUI().openFood(p);
                case EMERALD:
                    new VillagerGUI().openSpecial(p);

            }
        }
    }

}

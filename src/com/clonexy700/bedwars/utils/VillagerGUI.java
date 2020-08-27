package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.merchant.Merchant;
import com.clonexy700.bedwars.merchant.MerchantOffer;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.HashMap;

public class VillagerGUI {

    public VillagerGUI() {

    }

    public void openChests(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Chest");
        MerchantOffer chest = new MerchantOffer(iron(1), new ItemStack(Material.CHEST));
        m.addOffer(chest);
        MerchantOffer teamChest = new MerchantOffer(gold(2), enderChest());
        m.addOffer(teamChest);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openBows(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Bow");
        MerchantOffer bowLvlOne = new MerchantOffer(gold(3), BowLVL1());
        m.addOffer(bowLvlOne);
        MerchantOffer bowLvlTwo = new MerchantOffer(gold(6), BowLVL2());
        m.addOffer(bowLvlTwo);
        MerchantOffer bowLvlThree = new MerchantOffer(gold(13), BowLVL3());
        m.addOffer(bowLvlThree);
        MerchantOffer offer2 = new MerchantOffer(gold(8), arrowRain());
        m.addOffer(offer2);
        MerchantOffer offer = new MerchantOffer(gold(3), enderBow());
        m.addOffer(offer);
        MerchantOffer arrow = new MerchantOffer(gold(1), arrow());
        m.addOffer(arrow);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openPickaxes(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Pickaxes");
        MerchantOffer pickaxeLvlOne = new MerchantOffer(bronze(4), PickaxeLVL1());
        m.addOffer(pickaxeLvlOne);
        MerchantOffer pickaxeLvlTwo = new MerchantOffer(iron(2), PickaxeLVL2());
        m.addOffer(pickaxeLvlTwo);
        MerchantOffer pickaxeLvlThree = new MerchantOffer(gold(1), PickaxeLVL3());
        m.addOffer(pickaxeLvlThree);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openBlocks(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Blocks");
        MerchantOffer sandstone = new MerchantOffer(bronze(1), new ItemStack(Material.STAINED_CLAY, 2));
        m.addOffer(sandstone);
        MerchantOffer enderstone = new MerchantOffer(bronze(7), new ItemStack(Material.ENDER_STONE, 1));
        m.addOffer(enderstone);
        MerchantOffer ironblock = new MerchantOffer(iron(3), new ItemStack(Material.IRON_BLOCK));
        m.addOffer(ironblock);
        MerchantOffer glassblock = new MerchantOffer(bronze(2), new ItemStack(Material.GLASS));
        m.addOffer(glassblock);
        MerchantOffer glowstone = new MerchantOffer(iron(2), new ItemStack(Material.GLOWSTONE));
        m.addOffer(glowstone);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openArmor(Player p) {
        Color color = Color.WHITE;
        Merchant m = new Merchant();
        m.setTitle("Armor");
        MerchantOffer leatherHelmet = new MerchantOffer(bronze(1), getArmor(Material.LEATHER_HELMET, color));
        m.addOffer(leatherHelmet);
        MerchantOffer leatherLeggings = new MerchantOffer(bronze(1), getArmor(Material.LEATHER_LEGGINGS, color));
        m.addOffer(leatherLeggings);
        MerchantOffer leatherBoots = new MerchantOffer(bronze(1), getArmor(Material.LEATHER_BOOTS, color));
        m.addOffer(leatherBoots);
        MerchantOffer offer5 = new MerchantOffer(iron(1), ChainmailLVL1());
        m.addOffer(offer5);
        MerchantOffer offer6 = new MerchantOffer(iron(4), ChainmailLVL2());
        m.addOffer(offer6);
        MerchantOffer offer7 = new MerchantOffer(iron(7), ChainmailLVL3());
        m.addOffer(offer7);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openWeapons(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Weapon");
        MerchantOffer knockback = new MerchantOffer(bronze(8), Knockback());
        m.addOffer(knockback);
        MerchantOffer woodSword = new MerchantOffer(iron(1), WoodSword());
        m.addOffer(woodSword);
        MerchantOffer goldSword = new MerchantOffer(iron(3), GoldSword());
        m.addOffer(goldSword);
        MerchantOffer ironSword = new MerchantOffer(gold(5), IronSword());
        m.addOffer(ironSword);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openFood(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Weapon");
        MerchantOffer offer8 = new MerchantOffer(bronze(1), new ItemStack(Material.COOKIE, 2));
        m.addOffer(offer8);
        MerchantOffer offer9 = new MerchantOffer(bronze(3), new ItemStack(Material.COOKED_BEEF, 1));
        m.addOffer(offer9);
        MerchantOffer offer10 = new MerchantOffer(iron(1), new ItemStack(Material.CAKE));
        m.addOffer(offer10);
        MerchantOffer offer11 = new MerchantOffer(gold(2), new ItemStack(Material.GOLDEN_APPLE));
        m.addOffer(offer11);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openPotions(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Potions");
        MerchantOffer offer8 = new MerchantOffer(iron(3), new ItemStack(Material.POTION, 2, (byte) 8197));
        m.addOffer(offer8);
        MerchantOffer offer9 = new MerchantOffer(iron(5), new ItemStack(Material.POTION, 1, (byte) 8229));
        m.addOffer(offer9);
        MerchantOffer offer10 = new MerchantOffer(iron(7), new ItemStack(Material.POTION, (byte) 8201));
        m.addOffer(offer10);
        m.setCustomer(p);
        m.openTrading(p);
    }

    public void openSpecial(Player p) {
        Merchant m = new Merchant();
        m.setTitle("Potions");
        MerchantOffer offer8 = new MerchantOffer(bronze(1), new ItemStack(Material.LADDER, 1));
        m.addOffer(offer8);
        MerchantOffer offer9 = new MerchantOffer(bronze(28), new ItemStack(Material.POTION, 1));
        m.addOffer(offer9);
        MerchantOffer offer10 = new MerchantOffer(gold(1), new ItemStack(Material.TNT, 1));
        m.addOffer(offer10);
        MerchantOffer offer11 = new MerchantOffer(gold(5), new ItemStack(Material.FISHING_ROD, 1));
        m.addOffer(offer11);
        MerchantOffer offer12 = new MerchantOffer(gold(12), platform());
        m.addOffer(offer12);
        m.setCustomer(p);
        m.openTrading(p);
    }

    private ItemStack getArmor(Material type, Color color) {
        ItemStack stack = new ItemStack(type);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setColor(color);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack bronze(int amount) {
        return getItem(Material.CLAY_BRICK, amount, (byte) 0, null, "§cBronze");
    }

    private ItemStack gold(int amount) {
        return getItem(Material.GOLD_INGOT, amount, (byte) 0, null, "§6Gold");
    }

    private ItemStack iron(int amount) {
        return getItem(Material.IRON_INGOT, amount, (byte) 0, null, "§7Iron");
    }

    private ItemStack enderChest() {
        return getItem(Material.ENDER_CHEST, 1, (byte) 0, null, "§5§lTeam chest");
    }

    private ItemStack enderBow() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.ARROW_INFINITE, 1);
        ItemStack is = getItem(Material.BOW, 1, (byte) 0, en, "§5§lEnder Bow");
        is.setDurability((short) 384);
        ItemMeta meta = is.getItemMeta();
        meta.setLore(Arrays.asList(" ", "Like an enderpearl, but bow, >.<", ""));
        return is;
    }

    private ItemStack arrowRain() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.ARROW_INFINITE, 1);
        ItemStack is = getItem(Material.BOW, 1, (byte) 0, en, "§5§lEnder Bow");
        is.setDurability((short) 384);
        return is;
    }

    private ItemStack platform() {
        return getItem(Material.NETHER_STAR, 1, (byte) 0, null, "§5§lPlatform");
    }

    private ItemStack PickaxeLVL1() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.DURABILITY, 1);
        en.put(Enchantment.DIG_SPEED, 1);
        return getItem(Material.WOOD_PICKAXE, 1, (byte) 0, en, "§5§lWooden pickaxe");
    }

    private ItemStack PickaxeLVL2() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.DURABILITY, 1);
        en.put(Enchantment.DIG_SPEED, 1);
        return getItem(Material.STONE_PICKAXE, 1, (byte) 0, en, "§5§lStone pickaxe");
    }

    private ItemStack PickaxeLVL3() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.DURABILITY, 1);
        en.put(Enchantment.DIG_SPEED, 1);
        return getItem(Material.IRON_PICKAXE, 1, (byte) 0, en, "§5§lIron pickaxe");
    }

    private ItemStack BowLVL1() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.ARROW_INFINITE, 1);
        return getItem(Material.BOW, 1, (byte) 0, en, "§5§lBow lvl 1");
    }

    private ItemStack BowLVL2() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.ARROW_INFINITE, 1);
        en.put(Enchantment.ARROW_DAMAGE, 1);
        return getItem(Material.IRON_PICKAXE, 1, (byte) 0, en, "§5§lBow lvl 2");
    }

    private ItemStack BowLVL3() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.ARROW_INFINITE, 1);
        en.put(Enchantment.ARROW_DAMAGE, 3);
        en.put(Enchantment.ARROW_KNOCKBACK, 1);
        return getItem(Material.IRON_PICKAXE, 1, (byte) 0, en, "§5§lBow lvl 3");
    }

    private ItemStack arrow() {
        return getItem(Material.ARROW, 1, (byte) 0, null, "§5§lArrow");
    }

    private ItemStack Knockback() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        return getItem(Material.STICK, 1, (byte) 0, en, "§5§lStick of knockback");
    }

    private ItemStack WoodSword() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        return getItem(Material.WOOD_SWORD, 1, (byte) 0, en, "§5§lWooden sword");
    }

    private ItemStack GoldSword() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        return getItem(Material.GOLD_SWORD, 1, (byte) 0, en, "§5§lWooden sword");
    }

    private ItemStack IronSword() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        return getItem(Material.IRON_SWORD, 1, (byte) 0, en, "§5§lWooden sword");
    }

    private ItemStack ChainmailLVL1() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.DURABILITY, 1);
        en.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return getItem(Material.CHAINMAIL_CHESTPLATE, 1, (byte) 0, en, "§5§lChainmail chestplate lvl1");
    }

    private ItemStack ChainmailLVL2() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.DURABILITY, 2);
        en.put(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return getItem(Material.CHAINMAIL_CHESTPLATE, 1, (byte) 0, en, "§5§lChainmail chestplate lvl2");
    }

    private ItemStack ChainmailLVL3() {
        HashMap<Enchantment, Integer> en = new HashMap<>();
        en.put(Enchantment.DURABILITY, 3);
        en.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        return getItem(Material.CHAINMAIL_CHESTPLATE, 1, (byte) 0, en, "§5§lChainmail chestplate lvl3");
    }

    private ItemStack getItem(Material mat, int amount, byte b, HashMap<Enchantment, Integer> enchant,
                              String displayname) {
        ItemStack stack = new ItemStack(mat, amount, b);
        if (enchant != null) {
            for (Enchantment en : enchant.keySet()) {
                stack.addUnsafeEnchantment(en, enchant.get(en));
            }
        }
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayname);
        stack.setItemMeta(meta);
        return stack;
    }

}

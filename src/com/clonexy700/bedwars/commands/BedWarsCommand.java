package com.clonexy700.bedwars.commands;

import com.clonexy700.bedwars.Main;
import com.clonexy700.bedwars.utils.ItemSpawner;
import org.bukkit.Bukkit;
import com.clonexy700.bedwars.utils.setSpawn;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BedWarsCommand implements CommandExecutor {

    private Main main;
    public BedWarsCommand(Main main) {
        this.main = main;
        Bukkit.getPluginCommand("bedwars").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 1 && args[0].equalsIgnoreCase("setlobby")){
            main.getConfig().set("location.lobby", p.getLocation());
            main.saveConfig();
            p.sendMessage(main.prefix + "§dYou have set up lobby!");
        } else if (args.length == 1 && args[0].equalsIgnoreCase("setspawn")) {
            int i = setSpawn.set(p.getLocation(), main);
            p.sendMessage(main.prefix + "§dYou have been spawn §8[ID=" + i + "] §dset up!");
        } else if (args.length == 1 && args[0].equalsIgnoreCase("setspec")) {
            main.getConfig().set("location.spectator", p.getLocation());
            main.saveConfig();
            p.sendMessage(main.prefix + "§dSpectator spawn location have been set up!");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("setspawn")) {
            try {
                main.getConfig().set("location.spawn." + Integer.parseInt(args[1]), p.getLocation());
                main.saveConfig();
                p.sendMessage(main.prefix + "§dYou have been spawn §8[ID=" + args[1] + "] §dset up!");
            } catch (NumberFormatException e) {
                p.sendMessage(main.prefix + "§cNumber is required");
            }

        }else if (args.length == 2 && args[0].equalsIgnoreCase("setteam")) {
            try {
                main.getConfig().set("location.teamsize", Integer.parseInt(args[1]));
                main.saveConfig();
                p.sendMessage(main.prefix + "§d Team size §8" + args[1] + "§dhave been set up!");
            } catch (NumberFormatException e) {
                p.sendMessage(main.prefix + "§cNumber is required");
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("setcolor")) {
            try {
                main.getConfig().set("location.spawn" + Integer.parseInt(args[1]) + ".color",
                        ChatColor.valueOf(args[2].toUpperCase()).name().toUpperCase());
                main.saveConfig();
                p.sendMessage(main.prefix + "The team" + ChatColor.valueOf(args[2].toUpperCase())
                + ChatColor.valueOf(args[2].toUpperCase()).name() + "have been set up!");
                main.scoreboardUtil.generate();
            } catch (NumberFormatException e) {
                p.sendMessage(main.prefix + "§cNumber is required");
            } catch (EnumConstantNotPresentException e) {
                p.sendMessage(main.prefix + "§cThis color could not be found");
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("tp")) {
            Bukkit.getServer().createWorld(new WorldCreator(args[1]).generateStructures(false));
            Bukkit.getServer().getWorlds().add(Bukkit.getWorld(args[1]));
            p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
        } else if (args.length == 2 && args[0].equalsIgnoreCase("addspawner")) {
            try {
                ItemSpawner itemSpawner = new ItemSpawner(p.getLocation(), ItemSpawner.Type.valueOf(args[1]));
                itemSpawner.save();
                p.sendMessage(main.prefix + "§aYou added a spawner!");
            } catch (Exception e) {
                p.sendMessage(main.prefix + "§cUse one of the following types: §8[BRONZE, IRON, GOLD]");
            }
        } else {
            showHelp(p);
        }

        return true;
    }
    public void showHelp(Player p) {
        p.sendMessage(main.prefix + "§Help:");
        p.sendMessage(main.prefix + "§7/bw setlobby $8 - §aSet lobby spawn");
        p.sendMessage(main.prefix + "§7/bw setspawn $8 - §aSet the next spawn");
        p.sendMessage(main.prefix + "§7/bw setspawn $8 - §aSet the spawn point");
        p.sendMessage(main.prefix + "§7/bw setspec $8 - §aSet the spectator spawn");
        p.sendMessage(main.prefix + "§7/bw setteam [Amount] $8 - §aSet the team size");
        p.sendMessage("");

    }
}

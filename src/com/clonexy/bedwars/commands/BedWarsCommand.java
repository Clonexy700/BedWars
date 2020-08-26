package com.clonexy.bedwars.commands;

import com.clonexy.bedwars.Main;
import org.bukkit.Bukkit;
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
        if (args.length == 0) {
            showHelp(p);
        } else if (args.length == 1) {

        }
        return true;
    }
    public void showHelp(Player p) {

    }
}

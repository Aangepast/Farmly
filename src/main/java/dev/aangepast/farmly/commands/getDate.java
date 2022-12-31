package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class getDate implements CommandExecutor {

    private Main plugin;

    public getDate(Main plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(ChatColor.DARK_GREEN + "Current day: " + ChatColor.GREEN + plugin.currentDay);

        return true;
    }
}

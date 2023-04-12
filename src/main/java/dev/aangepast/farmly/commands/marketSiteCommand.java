package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class marketSiteCommand implements CommandExecutor {

    private Main plugin;

    public marketSiteCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            File file = new File(plugin.getDataFolder().getAbsolutePath() + "/webhost/config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3View online the market prices:"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&B"+ Bukkit.getServer().getIp() + ":"+config.getInt("port")));
        }

        return true;
    }
}

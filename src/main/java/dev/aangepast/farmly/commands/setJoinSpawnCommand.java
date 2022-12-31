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

import java.io.File;
import java.io.IOException;

public class setJoinSpawnCommand implements CommandExecutor {

    private Main plugin;

    public setJoinSpawnCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            File file = new File(plugin.getDataFolder().getAbsolutePath() + "/server/login.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("location", ((Player) sender).getLocation());
            plugin.loginSpawn = ((Player) sender).getLocation();
            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sender.sendMessage(ChatColor.DARK_GRAY + "Set login location to your current position.");
        } else {
            Bukkit.getLogger().info("This command cannot be executed from the console.");
        }
        return true;
    }
}

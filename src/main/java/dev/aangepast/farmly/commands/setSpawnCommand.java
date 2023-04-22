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
import java.io.IOException;

public class setSpawnCommand implements CommandExecutor {

    private Main plugin;

    public setSpawnCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            File file = new File(plugin.getDataFolder().getAbsolutePath() + "/server/login.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("spawn", ((Player) sender).getLocation());
            plugin.Spawn = ((Player) sender).getLocation();
            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sender.sendMessage(ChatColor.DARK_GRAY + "Set spawn to your current location.");
        } else {
            Bukkit.getLogger().info("This command cannot be executed from the console.");
        }
        return true;
    }
}

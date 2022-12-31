package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class newProfile implements CommandExecutor {

    private Main plugin;

    public newProfile(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player player = (Player) sender;

            File file = new File(PlayerUtility.getFolderPath(player, plugin) + "/data.yml");
            if(file.exists() || PlayerUtility.getPlayerData(player) != null){
                player.sendMessage(ChatColor.RED + "You already have a profile! Delete this one first before creating a new one.");
            } else {
                PlayerData data = new PlayerData();
                data.setXp(0.0);
                data.setSkillXp(0.0);
                data.setSkillPoints(0);
                data.setLevelLifestock(1);
                data.setLevelFarming(1);
                data.setLevelForgaging(1);
                data.setLevelCrafting(1);
                data.setFarmId(plugin.currentFarmId++);
                data.setHasAccessTradeMarket(false);
                data.setLevel(0);
                data.setCash(100.0);
                PlayerUtility.setPlayerData(player, data);
                player.sendMessage(ChatColor.GREEN + "You've successfully created a new profile.");
                plugin.addFarmID();
            }
        }
        return true;
    }
}

package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class deleteProfile implements CommandExecutor {

    private Main plugin;

    public deleteProfile(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){return true;}

        Player player = (Player) sender;

        if(PlayerUtility.getPlayerData(player) != null){
            File file = new File(PlayerUtility.getFolderPath(player, plugin) + "/data.yml");
            if(file.exists() || PlayerUtility.getPlayerData(player) != null) {
                if(file.delete()){
                    PlayerUtility.deletePlayerData(player);
                    player.sendMessage(ChatColor.DARK_RED + "Your profile has been deleted, this cannot be reversed.");
                } else {
                    player.sendMessage(ChatColor.RED + "Couldn't delete your personal player data, contact a staff member if this continues.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "There is no profile to delete yet.");
            }
        }



        return true;
    }
}

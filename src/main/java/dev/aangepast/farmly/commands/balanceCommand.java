package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class balanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){return false;}

        Player player = (Player) sender;

        PlayerData playerData = PlayerUtility.getPlayerData(player);
        double cash = playerData.getCash();

        player.playSound(player.getLocation(), "block.note_block.pling",1,1);
        player.sendMessage(ChatColor.GREEN + "You currently have " + ChatColor.YELLOW + "$"+cash+ChatColor.GREEN + " .");

        return true;
    }
}

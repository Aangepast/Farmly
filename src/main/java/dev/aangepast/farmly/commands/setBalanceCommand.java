package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class setBalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){return false;}

        Player player = (Player) sender;

        if(!(args.length == 2)){player.sendMessage(ChatColor.RED + "Invalid arguments, try /setbalance <player> <amount>");return true;}

        Player target = Bukkit.getPlayer(args[1]);
        if(target == null){player.sendMessage(ChatColor.RED + "Target player not found, is the player online?");return true;}

        PlayerData targetData = PlayerUtility.getPlayerData(target);
        double oldAmount = targetData.getCash();
        double amount = Double.parseDouble(args[0]);

        targetData.setCash(amount);
        target.sendMessage(ChatColor.GREEN + "Your balance has been changed from " + ChatColor.YELLOW + "$" + oldAmount + ChatColor.GREEN + " to " + ChatColor.YELLOW + "$" +amount + ChatColor.GREEN + ".");

        return true;
    }
}

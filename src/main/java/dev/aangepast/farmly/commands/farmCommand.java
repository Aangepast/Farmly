package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.inventories.farmCommandInventory;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

public class farmCommand implements CommandExecutor {

    private Main plugin;

    public farmCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (PlayerUtility.getFarmData(player).getOwner() == null) {
                player.sendMessage(ChatColor.DARK_RED + "You need a farm to be able to execute this command. Try /start");
                return true;
            }

            farmCommandInventory.openInventory(player);

        }
        return true;
    }
}

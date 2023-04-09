package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.inventories.farmersMarketInventory;
import dev.aangepast.farmly.managers.cropManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class marketCommand implements CommandExecutor {

    private Main plugin;

    public marketCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){return true;}

        Player player = (Player) sender;

        if(!(args.length > 0)){
            player.sendMessage("geen subcatogorie");
            return true;
        }

        CropData crop = cropManager.getCrop(args[0]);

        if(crop == null){player.sendMessage("geen crop");return true;}

        farmersMarketInventory market = new farmersMarketInventory();
        market.openCropInventory(player, crop, plugin);

        return true;
    }
}

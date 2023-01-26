package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getBuilding implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player player = (Player) sender;

            if(args.length > 0 && args.length < 7){
                player.sendMessage("Received building.");
                PlayerUtility.giveBuilding(player, args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            }
        }



        return true;
    }
}

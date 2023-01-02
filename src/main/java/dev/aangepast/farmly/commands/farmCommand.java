package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.FarmData;
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

    public farmCommand(Main plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(PlayerUtility.getFarmData(player).getOwner() == null){
                player.sendMessage(ChatColor.DARK_RED + "You need a farm to be able to execute this command. Try /start");
                return true;
            }
            if(args.length > 0){

                FarmData farm = PlayerUtility.getFarmData(player);

                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("spawn")){

                        Location spawn = new Location(farm.getSpawn().getWorld(), farm.getSpawn().getBlockX(), farm.getSpawn().getBlockY(), farm.getSpawn().getBlockZ());
                        Chunk chunk = spawn.getChunk();
                        chunk.load();
                        player.sendMessage(ChatColor.GRAY + "Teleporting you in 3 seconds...");

                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage(ChatColor.GRAY + "You have been teleported to your farm!");
                                player.playSound(player.getLocation(), "entity.experience_orb.pickup",1,1);
                                player.teleport(spawn, PlayerTeleportEvent.TeleportCause.COMMAND);
                            }
                        }, 60);


                    }
                }
            }
        }



        return true;
    }
}

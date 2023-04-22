package dev.aangepast.farmly.commands;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class spawnCommand implements CommandExecutor {

    private Main plugin;

    public spawnCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            player.teleport(plugin.Spawn, PlayerTeleportEvent.TeleportCause.COMMAND);
            player.sendMessage(ChatColor.GRAY + "You have been teleported to spawn!");
            player.playSound(player.getLocation(), "entity.experience_orb.pickup",1,1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 5));
        } else {
            plugin.getLogger().info("This command can only be executed in-game.");
        }

        return true;
    }
}

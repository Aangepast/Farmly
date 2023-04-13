package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.FarmUtilities;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onCropBreak implements Listener {

    @EventHandler
    public void cropBreak(BlockBreakEvent e){
        if(!(e.getBlock() instanceof Ageable)){return;}
        Player player = e.getPlayer();
        FarmData farmData = PlayerUtility.getFarmData(player);
        if(!(FarmUtilities.isInArea(e.getBlock().getLocation(), farmData.getMinPos(), farmData.getMaxPos()))){
            e.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are not able to break crops here.");
        }
        PlayerData playerData = PlayerUtility.getPlayerData(player);
        e.setCancelled(true);
        e.getBlock().breakNaturally(player.getItemInUse());
    }

}

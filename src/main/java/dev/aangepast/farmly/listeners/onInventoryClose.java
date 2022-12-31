package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class onInventoryClose implements Listener {

    private Main plugin;

    public onInventoryClose(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Animal: ")){
            plugin.currentInteract.remove(e.getPlayer().getUniqueId().toString());
        }
    }

}

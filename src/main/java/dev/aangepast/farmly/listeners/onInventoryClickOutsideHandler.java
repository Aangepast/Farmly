package dev.aangepast.farmly.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClickOutsideHandler implements Listener {

    @EventHandler
    public void onOutsideInventoryClick(InventoryClickEvent e){
        if(e.getRawSlot()==-999){
            e.getWhoClicked().closeInventory();
        }
    }


}

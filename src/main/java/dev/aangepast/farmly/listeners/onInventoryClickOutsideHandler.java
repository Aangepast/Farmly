package dev.aangepast.farmly.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClickOutsideHandler implements Listener {

    @EventHandler
    public void onOutsideInventoryClick(InventoryClickEvent e){
        if(e.getRawSlot()==-999){
            e.getWhoClicked().closeInventory();
            Player player = (Player) e.getWhoClicked();
            player.playSound(player.getLocation(), "block.stone_pressureplate.click_off",1,1);
        }
    }


}

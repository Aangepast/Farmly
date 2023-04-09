package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class onItemPickup implements Listener {

    @EventHandler
    public void onPickupFix(EntityPickupItemEvent e){

        if(e.getEntity() instanceof Player){
            ItemStack newItem = null;

            if(e.getItem().getItemStack().getItemMeta().hasDisplayName()){return;}

            switch(e.getItem().getItemStack().getType()){
                case CARROT:
                    newItem = renameItem(e.getItem().getItemStack(), "&6Carrot", true, true, true, true);
                    break;
                case WHEAT:
                    newItem = renameItem(e.getItem().getItemStack(), "&eWheat", false, true, true, true);
                    break;
            }
            if(newItem == null){return;}

            e.getItem().setItemStack(newItem);
        }

    }

    public ItemStack renameItem(ItemStack item, String name, boolean plantable, boolean feedable, boolean sellable, boolean storable){
        ItemBuilder itemBuilder = new ItemBuilder(item).setName(Utils.translateHexColorCodes("&#", "", ChatColor.translateAlternateColorCodes('&', name))).addLoreLine(ChatColor.RESET + " ").addCheck("Plantable", plantable).addCheck("Feedable", feedable).addCheck("Sellable", sellable).addCheck("Storable", storable);
        return itemBuilder.toItemStack();
    }


}

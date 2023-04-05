package dev.aangepast.farmly.inventories;

import dev.aangepast.farmly.data.itemData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class farmersMarketInventory {

    public void openInventory(Player player, itemData itemData){

        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Trade Market: " + itemData.getDisplayName());



        player.openInventory(inventory);


    }

}

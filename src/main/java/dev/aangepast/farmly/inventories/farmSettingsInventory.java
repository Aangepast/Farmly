package dev.aangepast.farmly.inventories;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class farmSettingsInventory {

    public static void openInventory(Player player){
        Inventory inv = Bukkit.createInventory(player, InventoryType.DISPENSER, ChatColor.DARK_GRAY + "Farm settings");
        player.openInventory(inv);

    }

}

package dev.aangepast.farmly.inventories;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class farmersMarketInventory {

    public void openCropInventory(Player player, CropData cropData, Main plugin){

        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Trading: " + cropData.getDisplayName());

        ItemBuilder tradeItem = new ItemBuilder(cropData.getMaterial(), 1).setName(cropData.getDisplayName()).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.translateAlternateColorCodes('&', "&8▪ &cBuy Price: &e$" + plugin.market.getCropBuyPrice(cropData))).addLoreLine(ChatColor.translateAlternateColorCodes('&', "&8▪ &aSell Price: &e$" + plugin.market.getCropSellPrice(cropData)));
        ItemStack itemStack = tradeItem.toItemStack();
        // maak item nog ff verder af met right click for sell all en left click for 1 sell
        // misschien ook nog ff dynamische prijzen maken?

        inventory.setItem(13,itemStack);

        player.openInventory(inventory);


    }

}

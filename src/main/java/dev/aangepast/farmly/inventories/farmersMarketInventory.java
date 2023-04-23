package dev.aangepast.farmly.inventories;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class farmersMarketInventory {

    public void openCropInventory(Player player, CropData cropData, Main plugin){

        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Trading: " + cropData.getDisplayName());

        ItemStack tradeItem = new ItemBuilder(cropData.getMaterial(), 1).setName(cropData.getDisplayName()).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.translateAlternateColorCodes('&', "&8▪ &cCurrent Buy Price: &e$" + plugin.market.getCropBuyPrice(cropData))).addLoreLine(ChatColor.translateAlternateColorCodes('&', "&8▪ &aCurrent Sell Price: &e$" + plugin.market.getCropSellPrice(cropData))).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#cccccc") + ChatColor.ITALIC.toString() + "Market prices change based on exchange actions.").toItemStack();
        double sellPriceX64 = plugin.market.getCropSellPrice(cropData) * 64;

        ItemStack item = new ItemBuilder(cropData.getMaterial(), 1).toItemStack();
        int amount = plugin.market.getAmount(player, item);
        double sellAllPrice = plugin.market.getCropSellPrice(cropData) * amount;
        ItemStack sellItem = new ItemBuilder(Material.RED_CONCRETE, 1).setName(Utils.translateHexColorCodes("&#", "", "&#ff6666Sell &#ff3333" + (ChatColor.stripColor(cropData.getDisplayName())) + " &#ff6666product")).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#ec9679Sell for an amount of "+amount+":")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#ec9679" + amount + " x " + plugin.market.getCropSellPrice(cropData) + " = $"+sellAllPrice)).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#ecb379Sell for an amount of 64:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#ecb37964 x "+plugin.market.getCropSellPrice(cropData) + " = $"+sellPriceX64)).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#eccf79Sell for an amount of 1:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#eccf791 x " + plugin.market.getCropSellPrice(cropData) + " = $"+plugin.market.getCropSellPrice(cropData))).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Left-click to sell 1").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Right-click to sell 64").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Middle-click to sell all").toItemStack();
        ItemStack buyItem = new ItemBuilder(Material.LIME_CONCRETE, 1).setName(Utils.translateHexColorCodes("&#", "", "&#47d147Buy &#2eb82e" + (ChatColor.stripColor(cropData.getDisplayName())) + " &#47d147product")).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#99cc00Buy for an amount of 64:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#99cc0064 x " + plugin.market.getCropBuyPrice(cropData) + " = $"+(64*plugin.market.getCropBuyPrice(cropData)))).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#66cc00Buy for an amount of 16:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#66cc0016 x "+plugin.market.getCropBuyPrice(cropData) + " = $"+(16*plugin.market.getCropBuyPrice(cropData)))).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#33cc00Buy for an amount of 1:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#33cc001 x " + plugin.market.getCropBuyPrice(cropData) + " = $"+plugin.market.getCropBuyPrice(cropData))).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Left-click to buy 1").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Right-click to buy 16").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Middle-click to buy 64").toItemStack();
        ItemStack chartsItem = new ItemBuilder(Material.LECTERN, 1).setName(ChatColor.AQUA + "Market graphs").addLoreLine(ChatColor.DARK_GRAY + " ▪ " + ChatColor.GRAY + "This will redirect you to our discord graphs channel.").toItemStack();

        // maak item nog ff verder af met right click for sell all en left click for 1 sell
        // misschien ook nog ff dynamische prijzen maken?

        inventory.setItem(13,tradeItem);
        inventory.setItem(15,sellItem);
        inventory.setItem(11,buyItem);
        inventory.setItem(31,chartsItem);

        player.openInventory(inventory);


    }

}

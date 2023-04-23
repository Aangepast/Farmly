package dev.aangepast.farmly.managers;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.interfaces.Market;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.PlayerUtility;
import dev.aangepast.farmly.utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class marketManager implements Market {

    private HashMap<CropData, Double> cropSellPrices = new HashMap<>();
    private HashMap<CropData, Double> cropBuyPrices = new HashMap<>();

    public HashMap<CropData, Double> getCropSellPrices() {
        return cropSellPrices;
    }

    public void setCropSellPrices(HashMap<CropData, Double> cropSellPrices) {
        this.cropSellPrices = cropSellPrices;
    }

    public void addCropSellPrice(CropData cropData, double price){
        this.cropSellPrices.put(cropData,price);
    }

    public void setCropSellPrice(CropData cropData, double price){
        this.cropSellPrices.remove(cropData);
        this.cropSellPrices.put(cropData,price);
    }

    public void removeCropSellPrice(CropData cropData){
        this.cropSellPrices.remove(cropData);
    }

    public void setCropBuyPrice(CropData cropData, double price){
        this.cropBuyPrices.remove(cropData);
        this.cropBuyPrices.put(cropData, price);
    }

    public void removeCropBuyPrice(CropData cropData){
        this.cropBuyPrices.remove(cropData);
    }

    public void addCropBuyPrice(CropData cropData, double price){
        this.cropBuyPrices.put(cropData, price);
    }

    public HashMap<CropData, Double> getCropBuyPrices() {
        return cropBuyPrices;
    }

    public void setCropBuyPrices(HashMap<CropData, Double> cropBuyPrices) {
        this.cropBuyPrices = cropBuyPrices;
    }

    public double getCropSellPrice(CropData cropData){
        double price = cropSellPrices.get(cropData);
        if(!(price > 0.01)){return price + 0.01;}
        return price;
    }

    public double getCropBuyPrice(CropData cropData){
        double price = cropBuyPrices.get(cropData);
        if(!(price > 0.01)){return price + 0.01;}
        return price;
    }

    public int getAmount(Player player, ItemStack itemStack){

        int amount = 0;

        if(player == null || itemStack == null){return amount;}

        for(int i = 0;i < 36;i++) {
            ItemStack slot = player.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(itemStack)) {
                continue;
            }
            amount += slot.getAmount();
        }
        return amount;
    }

    public boolean buyCrop(Player player, marketManager manager, CropData cropData, int amount){

        player.sendMessage(ChatColor.GRAY + "Purchasing...");

        PlayerData user = PlayerUtility.getPlayerData(player);

        double tempPrice = manager.getCropBuyPrice(cropData);
        for(int i = 0;i<amount;i++){
            tempPrice += 0.01;
        }
        double averagePrice = tempPrice / amount;

        if(user.getCash() >= tempPrice){

            ItemStack boughtItem = new ItemBuilder(cropData.getMaterial(), amount).toItemStack();

            int counter = 0;

            for(ItemStack item : player.getInventory().getContents()){
                if(item == null){counter += 64;continue;}
                if(item.getType().equals(Material.AIR)){counter += 64;continue;}
                if(item.equals(boughtItem)){
                    counter += item.getMaxStackSize()-item.getAmount();
                    if(counter >= amount){
                        break;
                    }
                }
            }

            if(amount > counter){
                player.sendMessage(ChatColor.RED + "You don't have enough inventory space!");
                return false;
            }

            user.setCash(user.getCash()-tempPrice);
            double newMarketPrice = manager.getCropBuyPrice(cropData);
            for(int i = 0;i<amount;i++){
                newMarketPrice += 0.01;
            }
            manager.setCropBuyPrice(cropData,newMarketPrice);
            player.getInventory().addItem(boughtItem);
            player.sendMessage(ChatColor.GREEN + "You have bought " + ChatColor.YELLOW + amount + "x " + cropData.getDisplayName() + ChatColor.GREEN + " for an average price of " + ChatColor.YELLOW + "$"+averagePrice + ChatColor.GREEN + " each item.");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You don't have enough money to buy this.");
        }
        return false;
    }

    @Override
    public boolean sellCrop(Player player, marketManager manager, CropData cropData, int amount) {
        return false;
    }

    @Override
    public void refreshInventory(Player player, String inventoryTitle, marketManager manager, CropData cropData, Main plugin) {
        if(inventoryTitle.contains(ChatColor.DARK_GRAY + "Trading: ")){
            ItemStack tradeItem = new ItemBuilder(cropData.getMaterial(), 1).setName(cropData.getDisplayName()).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.translateAlternateColorCodes('&', "&8▪ &cCurrent Buy Price: &e$" + plugin.market.getCropBuyPrice(cropData))).addLoreLine(ChatColor.translateAlternateColorCodes('&', "&8▪ &aCurrent Sell Price: &e$" + plugin.market.getCropSellPrice(cropData))).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#cccccc") + ChatColor.ITALIC.toString() + "Market prices change based on exchange actions.").toItemStack();
            double sellPriceX64 = plugin.market.getCropSellPrice(cropData) * 64;

            ItemStack item = new ItemBuilder(cropData.getMaterial(), 1).toItemStack();
            int amount = plugin.market.getAmount(player, item);
            double sellAllPrice = plugin.market.getCropSellPrice(cropData) * amount;
            ItemStack sellItem = new ItemBuilder(Material.RED_CONCRETE, 1).setName(Utils.translateHexColorCodes("&#", "", "&#ff6666Sell &#ff3333" + (ChatColor.stripColor(cropData.getDisplayName())) + " &#ff6666product")).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#ec9679Sell for an amount of "+amount+":")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#ec9679" + amount + " x " + plugin.market.getCropSellPrice(cropData) + " = $"+sellAllPrice)).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#ecb379Sell for an amount of 64:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#ecb37964 x "+plugin.market.getCropSellPrice(cropData) + " = $"+sellPriceX64)).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#eccf79Sell for an amount of 1:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#eccf791 x " + plugin.market.getCropSellPrice(cropData) + " = $"+plugin.market.getCropSellPrice(cropData))).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Left-click to sell 1").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Right-click to sell 64").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Middle-click to sell all").toItemStack();
            ItemStack buyItem = new ItemBuilder(Material.LIME_CONCRETE, 1).setName(Utils.translateHexColorCodes("&#", "", "&#47d147Buy &#2eb82e" + (ChatColor.stripColor(cropData.getDisplayName())) + " &#47d147product")).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#99cc00Buy for an amount of 64:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#99cc0064 x " + plugin.market.getCropBuyPrice(cropData) + " = $"+(64*plugin.market.getCropBuyPrice(cropData)))).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#66cc00Buy for an amount of 16:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#66cc0016 x "+plugin.market.getCropBuyPrice(cropData) + " = $"+(16*plugin.market.getCropBuyPrice(cropData)))).addLoreLine(ChatColor.RESET + " ").addLoreLine(Utils.translateHexColorCodes("&#", "", "&#33cc00Buy for an amount of 1:")).addLoreLine(ChatColor.DARK_GRAY + " ▪ " + Utils.translateHexColorCodes("&#", "", "&#33cc001 x " + plugin.market.getCropBuyPrice(cropData) + " = $"+plugin.market.getCropBuyPrice(cropData))).addLoreLine(ChatColor.RESET + " ").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Left-click to buy 1").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Right-click to buy 16").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Middle-click to buy 64").toItemStack();

            Inventory inventory = (Inventory) player.getOpenInventory();

            inventory.setItem(13,tradeItem);
            inventory.setItem(15,sellItem);
            inventory.setItem(11,buyItem);
        }
    }
    // TODO Loop bij prijs weergave zodat je de werkelijke prijs ziet zoals bij buyCrop class
}

package dev.aangepast.farmly.managers;

import dev.aangepast.farmly.data.CropData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class marketManager {

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
}

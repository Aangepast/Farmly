package dev.aangepast.farmly.data;

import org.bukkit.inventory.ItemStack;

public class itemData {

    String rawName;
    String displayName;
    ItemStack item;

    int defaultSellAmount;
    int defaultBuyAmount;

    boolean isCrop;
    int growTime;
    int cropId;

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getDefaultSellAmount() {
        return defaultSellAmount;
    }

    public void setDefaultSellAmount(int defaultSellAmount) {
        this.defaultSellAmount = defaultSellAmount;
    }

    public int getDefaultBuyAmount() {
        return defaultBuyAmount;
    }

    public void setDefaultBuyAmount(int defaultBuyAmount) {
        this.defaultBuyAmount = defaultBuyAmount;
    }

    public boolean isCrop() {
        return isCrop;
    }

    public void setCrop(boolean crop) {
        isCrop = crop;
    }

    public int getGrowTime() {
        return growTime;
    }

    public void setGrowTime(int growTime) {
        this.growTime = growTime;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}

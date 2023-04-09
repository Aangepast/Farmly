package dev.aangepast.farmly.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemData {

    String rawName;
    String displayName;
    ItemStack item;
    Material material;

    int defaultSellAmount;
    int defaultBuyAmount;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

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

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}

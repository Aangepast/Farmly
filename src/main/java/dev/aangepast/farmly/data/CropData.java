package dev.aangepast.farmly.data;

import org.bukkit.Material;

public class CropData {

    private int crowTime;
    private String displayName;
    private String rawName;
    private int defaultBuyPrice;
    private int defaultSellPrice;
    private org.bukkit.Material Material;
    private CropType cropType;

    public int getCrowTime() {
        return crowTime;
    }

    public void setCrowTime(int crowTime) {
        this.crowTime = crowTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public int getDefaultBuyPrice() {
        return defaultBuyPrice;
    }

    public void setDefaultBuyPrice(int defaultBuyPrice) {
        this.defaultBuyPrice = defaultBuyPrice;
    }

    public int getDefaultSellPrice() {
        return defaultSellPrice;
    }

    public void setDefaultSellPrice(int defaultSellPrice) {
        this.defaultSellPrice = defaultSellPrice;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public org.bukkit.Material getMaterial() {
        return Material;
    }

    public void setMaterial(Material material) {
        Material = material;
    }
}

package dev.aangepast.farmly.data;

import org.bukkit.Material;

public class CropData {

    private int crowTime;
    private String displayName;
    private String rawName;
    private double defaultBuyPrice;
    private double defaultSellPrice;
    private org.bukkit.Material Material;
    private CropType cropType;
    private double xp;
    private int minDrops;
    private int maxDrops;

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

    public double getDefaultBuyPrice() {
        return defaultBuyPrice;
    }

    public void setDefaultBuyPrice(double defaultBuyPrice) {
        this.defaultBuyPrice = defaultBuyPrice;
    }

    public double getDefaultSellPrice() {
        return defaultSellPrice;
    }

    public void setDefaultSellPrice(double defaultSellPrice) {
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

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public int getMinDrops() {
        return minDrops;
    }

    public void setMinDrops(int minDrops) {
        this.minDrops = minDrops;
    }

    public int getMaxDrops() {
        return maxDrops;
    }

    public void setMaxDrops(int maxDrops) {
        this.maxDrops = maxDrops;
    }
}

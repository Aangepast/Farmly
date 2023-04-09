package dev.aangepast.farmly.managers;

import dev.aangepast.farmly.data.CropData;

import java.util.ArrayList;
import java.util.List;

public class cropManager {

    private static List<CropData> crops = new ArrayList<>();

    public static void addCrop(CropData cropData) {
        crops.add(cropData);
    }

    public static void removeCrop(CropData cropData){
        crops.remove(cropData);
    }

    public static CropData getCrop(String rawName){
        for (CropData crop : crops){
            if(crop.getRawName().equalsIgnoreCase(rawName)){
                return crop;
            }
        }
        return null;
    }

    public static List<CropData> getCrops() {
        return crops;
    }

    public static void setCrops(List<CropData> crops) {
        cropManager.crops = crops;
    }
}
package dev.aangepast.farmly.data;

import java.util.ArrayList;
import java.util.HashMap;

public class chartData {
    private HashMap<HashMap<Double, Double>, CropType> allPricesHistory;

    private HashMap<Double, Double> priceHistoryEntry = new HashMap<>();

    public HashMap<Double, Double> getPriceHistory() {
        return priceHistoryEntry;
    }

    public void setPriceHistory(HashMap<Double, Double> priceHistory) {
        this.priceHistoryEntry = priceHistory;
    }

    public HashMap<HashMap<Double, Double>, CropType> getAllPricesHistory() {
        return allPricesHistory;
    }

    public void setAllPricesHistory(HashMap<HashMap<Double, Double>, CropType> allPricesHistory) {
        this.allPricesHistory = allPricesHistory;
    }
}

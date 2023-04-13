package dev.aangepast.farmly.data;

import java.util.ArrayList;
import java.util.HashMap;

public class chartData {
    private HashMap<Integer, Double> chartData = new HashMap<>();
    private String chartName;

    public HashMap<Integer, Double> getChartData() {
        return chartData;
    }

    public void setChartData(HashMap<Integer, Double> chartData) {
        this.chartData = chartData;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
}

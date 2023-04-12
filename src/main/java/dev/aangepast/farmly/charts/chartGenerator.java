package dev.aangepast.farmly.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.*;

public class chartGenerator extends JFrame {

    public chartGenerator(){
        frameInit(); // verander dit nog even
    }

    private void initUI(){
        CategoryDataset set = createChart()
    }


    private JFreeChart createChart(CategoryDataset dataset, String name) {

        JFreeChart chart = ChartFactory.createAreaChart(
                "1Market Price " + name,
                "Time (1h)",
                "Price ($)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                true
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.3f);

        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);

        chart.setTitle(new TextTitle("Market Price " + name,
                new Font("Serif", java.awt.Font.BOLD, 18))
        );

        return chart;
    }

    private CategoryDataset createDataset() {
        double[][] data;
        // doe hier iets met chartData
    }
}

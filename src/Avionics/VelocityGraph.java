package Avionics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Creating the velocity graph panel for GUI
 */
public class VelocityGraph {

    private  XYSeries series1;

    // creates a line chart object and returns the chart panel
    public  JPanel createChartPanel() {

        // creating the graph
        String chartTitle = "";
        String xAxisLabel = "Time(s)";
        String yAxisLabel = "Velocity (m/s)";

        XYDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        // styles
        chart.setBackgroundPaint(new Color(245,245,219));
        chart.removeLegend();
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(204,204,204));
        ValueAxis yAxis = plot.getRangeAxis();
        ValueAxis xAxis = plot.getDomainAxis();
        yAxis.setRange(-100, 100);
        Font font = new Font("Monospace", Font.PLAIN, 12);
        yAxis.setTickLabelFont(font);
        xAxis.setTickLabelFont(font);

        return new ChartPanel(chart);
    }

    // creating the dataset
    public  XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        series1 = new XYSeries("Object 1");
        series1.add(0, 0);
        dataset.addSeries(series1);

        return dataset;
    }

    // adding data to the dataset
    public  void updateVelocityGraph(double time, double velocity){
        series1.add(time,velocity);
    }

}

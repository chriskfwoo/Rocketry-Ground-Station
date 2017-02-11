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


public class VelocityGraph {

    private  XYSeries series1;

    public  JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
        String chartTitle = "";
        String xAxisLabel = "Time(s)";
        String yAxisLabel = "Velocity (m/s)";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        chart.setBackgroundPaint(new Color(245,245,219));
        chart.removeLegend();

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(204,204,204));
        ValueAxis yAxis = plot.getRangeAxis();
        ValueAxis xAxis = plot.getDomainAxis();
        yAxis.setRange(-500, 500);



        Font font = new Font("Monospace", Font.PLAIN, 12);

        yAxis.setTickLabelFont(font);
        xAxis.setTickLabelFont(font);

        return new ChartPanel(chart);
    }


    public  XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        series1 = new XYSeries("Object 1");
        series1.add(0, 0);
//        series1.add(60, 0);

        dataset.addSeries(series1);

        return dataset;
    }

    public  void updateVelocityGraph(double time, double velocity){
        series1.add(time,velocity);
    }

}

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


public class AltitudeGraph {

    private  XYSeries series1;


    public  JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel

        // creating chart
        String chartTitle = "";
        String xAxisLabel = "Time(s)";
        String yAxisLabel = "Altitude(ft)";

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
        yAxis.setRange(1, 12000);
        Font font = new Font("Monospace", Font.PLAIN, 12);
        yAxis.setTickLabelFont(font);
        xAxis.setTickLabelFont(font);

        return new ChartPanel(chart);
    }


    public  XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        series1 = new XYSeries("Altitude");

        series1.add(0, 0);
        series1.add(3, 3000);
        series1.add(5, 5000);
        series1.add(8, 8000);
        series1.add(10, 10000);
        series1.add(60, 0);

        dataset.addSeries(series1);

//        XYSeries series2 = new XYSeries("Object 2");
//        series2.add(2.0, 1.0);
//        series2.add(2.5, 2.4);
//        series2.add(3.2, 1.2);
//        series2.add(3.9, 2.8);
//        series2.add(4.6, 3.0);
//        dataset.addSeries(series2);

        return dataset;
    }

    // TODO update dataset
    public  void updateAltitudeGraph(double time, double velocity){
        series1.add(time,velocity);
    }

}


package Avionics;

import Avionics.graphs.AccelerationGraph;
import Avionics.graphs.AltitudeGraph;
import Avionics.graphs.GpsGraph;

import javax.swing.*;
import java.awt.*;

/**
 * Creating the gui model for all the graphs
 */
public class GUIModel {
    private GUI gui;
    private AccelerationGraph taGraph;
    private AltitudeGraph aGraph;
    private GpsGraph gGraph;

    private JPanel altPanel;
    private JPanel accelPanel;
    private JPanel gpsPanel;

    public GUIModel(){

        // getting GUI Graph Panels
        gui = new GUI();
        altPanel = gui.getAltitudeGraph();
        accelPanel = gui.getAccelerationGraph();
        gpsPanel = gui.getGpsPanel();

        // setting layout for panels
        altPanel.setLayout(new java.awt.BorderLayout());
        accelPanel.setLayout(new java.awt.BorderLayout());
        gpsPanel.setLayout(new java.awt.BorderLayout());

        // creating graphs for panels
        aGraph = new AltitudeGraph();
        JPanel altChartPanel = aGraph.createChartPanel();
        taGraph = new AccelerationGraph();
        JPanel taChartPanel = taGraph.createChartPanel();
        gGraph = new GpsGraph();
        JPanel gpsChartPanel = gGraph.getMap();

        // adding graphs into panels
        altPanel.add(altChartPanel, BorderLayout.CENTER);
        altPanel.setBackground(Color.black);
        altPanel.validate();
        accelPanel.add(taChartPanel, BorderLayout.CENTER);
        accelPanel.setBackground(Color.black);
        accelPanel.validate();
        gpsPanel.add(gpsChartPanel, BorderLayout.CENTER);
        gpsPanel.setBackground(Color.black);
        gpsPanel.validate();
    }

    public GUI getGui() {
        return gui;
    }

    public AccelerationGraph getTaGraph() {
        return taGraph;
    }

    public AltitudeGraph getaGraph() {
        return aGraph;
    }

    public GpsGraph getgGraph() {
        return gGraph;
    }

    public JPanel getAltPanel() {
        return altPanel;
    }

    public JPanel getAccelPanel() {
        return accelPanel;
    }

    public JPanel getGpsPanel() {
        return gpsPanel;
    }
}

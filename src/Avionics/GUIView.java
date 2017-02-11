package Avionics;

import com.teamdev.jxmaps.MapViewOptions;

import javax.swing.*;
import java.awt.*;

public class GUIView {

    private GUI gui;
    private VelocityGraph vGraph;
    private AltitudeGraph aGraph;

    private JPanel altPanel;
    private JPanel velPanel;
    private JPanel googleMapsPanel;

    private JPanel velChartPanel;
    private JPanel altChartPanel;

    public GUIView(){

        //  getting GUI Graph Panels
        gui = new GUI();
        altPanel = gui.getAltitudeGraph();
        velPanel = gui.getVelocityGraphGraph();
        googleMapsPanel = gui.getGoogleMaps();

        // setting layout for panels
        altPanel.setLayout(new java.awt.BorderLayout());
        velPanel.setLayout(new java.awt.BorderLayout());
        googleMapsPanel.setLayout(new java.awt.BorderLayout());

        // creating graphs for panels
        aGraph = new AltitudeGraph();
        altChartPanel = aGraph.createChartPanel();
        vGraph = new VelocityGraph();
        velChartPanel = vGraph.createChartPanel();

        altPanel.add(altChartPanel, BorderLayout.CENTER);
        altPanel.setBackground(Color.black);
        altPanel.validate();

        velPanel.add(velChartPanel, BorderLayout.CENTER);
        velPanel.setBackground(Color.black);
        velPanel.validate();

        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        final GoogleMaps mapView = new GoogleMaps(options);

        googleMapsPanel.add(mapView, BorderLayout.CENTER);
        googleMapsPanel.setBackground(Color.black);
        googleMapsPanel.validate();

    }

    // update data panel
    public void updateData(String[] filterPacket){
        gui.updateLabels(filterPacket);
    }

    // update alt graph
    public void updateAltitudeGraph(double time, double velocity){
        aGraph.updateAltitudeGraph(time,velocity);
        altPanel.repaint();
    }

    // update velocity graph
    public void updateVelocityGraph(double time, double velocity){
        vGraph.updateVelocityGraph(time,velocity);
        velPanel.repaint();
    }

    // update google maps marker

}

package Avionics;

import Avionics.graphs.AccelerationGraph;
import Avionics.graphs.AltitudeGraph;
import Avionics.graphs.GpsGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Creating the GUI View for all the graphs
 */
public class GUIView {
    private GUI gui;
    private AccelerationGraph taGraph;
    private AltitudeGraph aGraph;
    private GpsGraph gGraph;

    private JPanel altPanel;
    private JPanel accelPanel;
    private JPanel gpsPanel;

    public GUIView(){

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

    // update gui labels
    public void updateGUILabels(String[] alterFiltered){
        gui.updateLabels(alterFiltered);
    }

    // listener to reset all graph
    public void addResetListener(ActionListener listenerForResetBtn){
        gui.getResetBtn().addActionListener(listenerForResetBtn);
    }

    // listener to fit all gps markers on gps graph
    public void addFitListener(ActionListener listenForFitBtn){
        gui.getFitMarkersBtn().addActionListener(listenForFitBtn);
    }

    // clear all graphs dataset
    public void clearAllGraphs(){
        aGraph.clear();
        taGraph.clear();
        gGraph.clear();
    }

    // fit all gps markers on gps graph
    public void fitGpsMarkers(){
        gGraph.seeMakers();
    }

    // update alt graph
    public void updateAltitudeGraph(double time, double alt){
        aGraph.updateAltitudeGraph(time,alt);
        // altPanel.repaint();
    }

    // update total acceleration graph
    public void updateAccelerationGraph(double time, double ta){
        taGraph.updateAccelerationGraph(time,ta);
        // accelPanel.repaint();
    }

    // update map marker
    public void updateMapMark(double lat, double lon) {
        gGraph.updateGpsGraph(lat,lon);
    }

}

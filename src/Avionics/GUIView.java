package Avionics;

import Avionics.graphs.AccelerationGraph;
import Avionics.graphs.AltitudeGraph;

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

    private JPanel altPanel;
    private JPanel accelPanel;

    public GUIView(){

        // getting GUI Graph Panels
        gui = new GUI();
        altPanel = gui.getAltitudeGraph();
        accelPanel = gui.getAccelerationGraph();

        // setting layout for panels
        altPanel.setLayout(new java.awt.BorderLayout());
        accelPanel.setLayout(new java.awt.BorderLayout());

        // creating graphs for panels
        aGraph = new AltitudeGraph();
        JPanel altChartPanel = aGraph.createChartPanel();
        taGraph = new AccelerationGraph();
        JPanel taChartPanel = taGraph.createChartPanel();

        // adding graphs into panels
        altPanel.add(altChartPanel, BorderLayout.CENTER);
        altPanel.setBackground(Color.black);
        altPanel.validate();
        accelPanel.add(taChartPanel, BorderLayout.CENTER);
        accelPanel.setBackground(Color.black);
        accelPanel.validate();

    }

    // update gui labels
    public void updateGUILabels(String[] alterFiltered){
        gui.updateLabels(alterFiltered);
    }

    // listener to reset all graph
    public void addResetListener(ActionListener listenerForResetBtn){
        gui.getResetBtn().addActionListener(listenerForResetBtn);
    }

    // clear all graphs dataset
    public void clearAllGraphs(){
        aGraph.clear();
        taGraph.clear();
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

}

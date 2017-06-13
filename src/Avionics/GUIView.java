package Avionics;

import Avionics.graphs.AccelerationGraph;
import Avionics.graphs.AltitudeGraph;
import Avionics.graphs.TemperatureGraph;

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
    private TemperatureGraph tGraph;

    private JPanel altPanel;
    private JPanel accelPanel;
    private JPanel tempPanel;

    public GUIView(){

        // getting GUI Graph Panels
        gui = new GUI();
        altPanel = gui.getAltitudeGraph();
        accelPanel = gui.getAccelerationGraph();
        tempPanel = gui.getTempGraph();

        // altitude graph
        altPanel.setLayout(new java.awt.BorderLayout());
        aGraph = new AltitudeGraph();
        JPanel altChartPanel = aGraph.createChartPanel();
        altPanel.add(altChartPanel, BorderLayout.CENTER);
        altPanel.setBackground(Color.black);
        altPanel.validate();

        // acceleration graph
        accelPanel.setLayout(new java.awt.BorderLayout());
        taGraph = new AccelerationGraph();
        JPanel taChartPanel = taGraph.createChartPanel();
        accelPanel.add(taChartPanel, BorderLayout.CENTER);
        accelPanel.setBackground(Color.black);
        accelPanel.validate();

        // temperature graph
        tempPanel.setLayout(new java.awt.BorderLayout());
        tempPanel.setPreferredSize(new Dimension(1,1));
        tGraph = new TemperatureGraph();
        JPanel tChartPanel = tGraph.createChartPanel();
        tempPanel.add(tChartPanel, BorderLayout.CENTER);
        tempPanel.setBackground(Color.black);
        tempPanel.validate();

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
        tGraph.clear();
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

    // update temp graph
    public void updateTempGraph(double time, double ta){
        tGraph.updateTempGraph(time,ta);
        // tempPanel.repaint();
    }

}

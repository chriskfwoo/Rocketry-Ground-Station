package Avionics;

//import com.teamdev.jxmaps.*;

import javax.swing.*;
import javax.swing.Icon;
import java.awt.*;

/**
 * creates the GUI graphs, filter the packets, update the labels and graphs
 */
public class ControllerGUI {

    private GUI gui;
    private VelocityGraph vGraph;
    private AltitudeGraph aGraph;

    private JPanel altPanel;
    private JPanel velPanel;

    // private GoogleMaps mapView;

    public ControllerGUI(){

        //  getting GUI Graph Panels
        gui = new GUI();
        altPanel = gui.getAltitudeGraph();
        velPanel = gui.getVelocityGraphGraph();
        //JPanel googleMapsPanel = gui.getGoogleMaps();

        // setting layout for panels
        altPanel.setLayout(new java.awt.BorderLayout());
        velPanel.setLayout(new java.awt.BorderLayout());
        //googleMapsPanel.setLayout(new java.awt.BorderLayout());

        // creating graphs for panels
        aGraph = new AltitudeGraph();
        JPanel altChartPanel = aGraph.createChartPanel();
        vGraph = new VelocityGraph();
        JPanel velChartPanel = vGraph.createChartPanel();

        altPanel.add(altChartPanel, BorderLayout.CENTER);
        altPanel.setBackground(Color.black);
        altPanel.validate();

        velPanel.add(velChartPanel, BorderLayout.CENTER);
        velPanel.setBackground(Color.black);
        velPanel.validate();

        // MapViewOptions options = new MapViewOptions();
        // options.importPlaces();
        // mapView = new GoogleMaps(options);

        // googleMapsPanel.add(mapView, BorderLayout.CENTER);
        // googleMapsPanel.setBackground(Color.black);
        // googleMapsPanel.validate();

    }

    // split the string into array and modify it to displa on GUI
    public void unfiltered (String unfiltered){

        String[] filtered = unfiltered.split(",");

        // modifying the filtered array to properly display on GUI
        String[] alterFiltered = new String[12];


        // format of the packet
        //  0          1      2      3                4    5              6  7  8          9 10  11
        // <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,         <gyro>
        //  950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     101#101#101


        // timestamp (ms)
        String timestamp = filtered[0];
        double seconds = Double.parseDouble(timestamp) / 1000.0;
        alterFiltered[0] = timestamp;

        // pitot
        String pitot = filtered[1];
        alterFiltered[1] = pitot;

        // barometer
        String barometer = filtered[2];
        alterFiltered[2] = barometer + " Pa";

        // altitude
        String altitude = filtered[3];
        double altValue = Double.parseDouble(altitude);
        alterFiltered[3] = altitude;

        // GPS lat,long
        String[] latlong = filtered[4].split("#");
        String latitude = latlong[0] + " " + latlong[1];
        String longitude = latlong[2] + " " + latlong[3];
        alterFiltered[4] = latitude;
        alterFiltered[5] = longitude;

        // acceleration x,y,z
        String[] acceleration = filtered[5].split("#");
        String accelx = acceleration[0];
        String accely = acceleration[1];
        String accelz = acceleration[2];
        alterFiltered[6] = accelx;
        alterFiltered[7] = accely;
        alterFiltered[8] = accelz;

        // gyro x,y,z
        String[] gyroscope = filtered[6].split("#");
        String gyrox = gyroscope[0];
        String gyroy = gyroscope[1];
        String gyroz = gyroscope[2];
        alterFiltered[9] = gyrox;
        alterFiltered[10] = gyroy;
        alterFiltered[11] = gyroz;

        gui.updateLabels(alterFiltered);
        updateAltitudeGraph(seconds,altValue);
        updateVelocityGraph(seconds,60);
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
    public void updateGoogleMaps() {
        //mapView.addMarkers();
    }
}

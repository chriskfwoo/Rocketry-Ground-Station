package Avionics;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import javax.swing.*;
import java.awt.*;

/**
 * creates the GUI graphs, filter the packets, update the labels and graphs
 */
public class ControllerGUI extends JFrame {

    private GUI gui;
    private VelocityGraph vGraph;
    private AltitudeGraph aGraph;
    private GpsGraph gGraph;

    private JPanel altPanel;
    private JPanel velPanel;
    private JPanel gpsPanel;

    // variables for GUI
    private String timestamp, pitot, barometer, altitude,latitude,longitude,accelx,accely,accelz,gyrox,gyroy,gyroz;
    private double seconds, altValue;

    // testing variable
    static double longitudeTest = -73.569315;

    public ControllerGUI(){

        //  getting GUI Graph Panels
        gui = new GUI();
        altPanel = gui.getAltitudeGraph();
        velPanel = gui.getVelocityGraphGraph();
        gpsPanel = gui.getGpsPanel();
        //gpsPanelHelper = gui.getGpsMapHelper();

        // setting layout for panels
        altPanel.setLayout(new java.awt.BorderLayout());
        velPanel.setLayout(new java.awt.BorderLayout());
        gpsPanel.setLayout(new java.awt.BorderLayout());

        // creating graphs for panels
        aGraph = new AltitudeGraph();
        JPanel altChartPanel = aGraph.createChartPanel();
        vGraph = new VelocityGraph();
        JPanel velChartPanel = vGraph.createChartPanel();
        gGraph = new GpsGraph();
        JPanel gpsChartPanel = gGraph.getMap();

        // adding graphs into panels
        altPanel.add(altChartPanel, BorderLayout.CENTER);
        altPanel.setBackground(Color.black);
        altPanel.validate();

        velPanel.add(velChartPanel, BorderLayout.CENTER);
        velPanel.setBackground(Color.black);
        velPanel.validate();

        gpsPanel.add(gpsChartPanel, BorderLayout.CENTER);
        gpsPanel.setBackground(Color.black);
        gpsPanel.validate();

//        map = new JMapViewer();
//        JButton button = new JButton("fit");
//        button.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                map.setDisplayToFitMapMarkers();
//            }
//        });
//        gpsPanelHelper.add(button);

    }

    // TODO MAKE SURE TO CHECK FOR CASES IF STRING IS BAD
    // split the string into array and modify it to display on GUI
    public void unfiltered (String unfiltered){

        /*** format of the packet
         0          1      2      3                4    5              6  7  8          9 10  11
         <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,         <gyro>
         950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     101#101#101
         */

        String[] filtered = unfiltered.split(",");

        // modifying the filtered array to properly display on GUI
        String[] alterFiltered = new String[12];

        try{

        // timestamp (ms)
        timestamp = filtered[0];
        seconds = Double.parseDouble(timestamp) / 1000.0;
        alterFiltered[0] = timestamp;

        // pitot
        pitot = filtered[1];
        // TODO calculate actual value of pitot
        alterFiltered[1] = pitot;

        // barometer
        barometer = filtered[2];
        alterFiltered[2] = barometer + " Pa";

        // altitude
        altitude = filtered[3];
        altValue = Double.parseDouble(altitude);
        alterFiltered[3] = altitude;

        // GPS lat,long
        String[] latlong = filtered[4].split("#");
        latitude = latlong[0] + " " + latlong[1];
        longitude = latlong[2] + " " + latlong[3];
        alterFiltered[4] = latitude;
        alterFiltered[5] = longitude;

        // acceleration x,y,z
        String[] acceleration = filtered[5].split("#");
        accelx = acceleration[0];
        accely = acceleration[1];
        accelz = acceleration[2];
        // TODO calculate actual value of accel
        alterFiltered[6] = accelx + "g";
        alterFiltered[7] = accely + "g";
        alterFiltered[8] = accelz + "g";

        // gyro x,y,z
        String[] gyroscope = filtered[6].split("#");
        gyrox = gyroscope[0];
        gyroy = gyroscope[1];
        gyroz = gyroscope[2];
        // TODO calculate actual value of gyro
        alterFiltered[9] = gyrox;
        alterFiltered[10] = gyroy;
        alterFiltered[11] = gyroz;

        longitudeTest += 0.005;
        }catch (Exception e){
            System.out.println("error was caught");
        }finally {
            gui.updateLabels(alterFiltered);
            updateAltitudeGraph(seconds, altValue);
            updateVelocityGraph(seconds,60);
            updateMapMark(45.496067, longitudeTest);
        }
    }

    public static void calculatePitotTube(int range){

        // ADC 3.3

    }


    public static void calculateAccelerometer(int xAccel, int yAccel, int zAccel){

        /**
         * x = xAccel
         * A = -32768
         * B = 32767
         * D =
         * C =
         *
         *
         *
         *
         *
         */
        //Range: -32768 to 32767
        // -16g to 16g
        //Y = (X-A)/(B-A) * (D-C) + C

    }

    public static void calculateGyroscope(){
        //Range: -32768 to 32767

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

    // update map marker
    public void updateMapMark(double lat, double lon) {
        gGraph.updateGpsGraph(lat,lon);
    }

}

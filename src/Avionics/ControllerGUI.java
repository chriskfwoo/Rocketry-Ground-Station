package Avionics;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

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
        alterFiltered[0] = Double.toString(seconds);

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
        // TODO calculate actual value of accel Calculations.calculateAcceleration();
        BigDecimal Ax = Calculations.calculateAcceleration(Integer.parseInt(accelx));
        BigDecimal Ay = Calculations.calculateAcceleration(Integer.parseInt(accely));
        BigDecimal Az = Calculations.calculateAcceleration(Integer.parseInt(accelz));
        alterFiltered[6] = Ax + "g";
        alterFiltered[7] = Ay + "g";
        alterFiltered[8] = Az + "g";

        // gyro x,y,z
        String[] gyroscope = filtered[6].split("#");
        gyrox = gyroscope[0];
        gyroy = gyroscope[1];
        gyroz = gyroscope[2];
        // TODO calculate actual value of gyro Calculations.calculateGyroscope();
        BigDecimal Gx = Calculations.calculateGyroscope(Integer.parseInt(gyrox));
        BigDecimal Gy = Calculations.calculateGyroscope(Integer.parseInt(gyroy));
        BigDecimal Gz = Calculations.calculateGyroscope(Integer.parseInt(gyroz));
        alterFiltered[9] = Gx +"";
        alterFiltered[10] = Gy + "";
        alterFiltered[11] = Gz + "";

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

package Avionics;

import Avionics.Graphs.AccelerationGraph;
import Avionics.Graphs.AltitudeGraph;
import Avionics.Graphs.GpsGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/**
 * creates the GUI graphs, filter the packets, update the labels and graphs
 */
public class ControllerGUI extends JFrame {

    private GUI gui;
    private AccelerationGraph taGraph;
    private AltitudeGraph aGraph;
    private GpsGraph gGraph;

    private JPanel altPanel;
    private JPanel accelPanel;
    private JPanel gpsPanel;

    // variables for GUI
    private String timestamp, pitot, barometer, altitude,latitude,longitude,accelx,accely,accelz,gyrox,gyroy,gyroz;
    private double seconds, altValue, totalA;

    // testing variable
    static double longitudeTest = -73.569315;

    public ControllerGUI(){

        //  getting GUI Graph Panels
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

        // reset all graphs on click
        gui.getResetBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aGraph.clear();
                taGraph.clear();
                gGraph.clear();
            }
        } );

        // fit all markers on the gps graph on click
        gui.getFitMarkersBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gGraph.seeMakers();
            }
        } );

    }

    // split the string into array and modify it to display on GUI
    public void unfiltered (String unfiltered){

        /*** format of the packet
         0          1      2      3                4    5              6  7  8          9 10  11
         <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,         <gyro>
         950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     101#101#101
         */

        // parsing the packet
        String[] filtered = unfiltered.split(",");

        // modifying the filtered array to properly display on GUI
        String[] alterFiltered = new String[12];

        try{

        // timestamp (ms)
        timestamp = filtered[0];
        seconds = Double.parseDouble(timestamp) / 1000.0;
        alterFiltered[0] = Double.toString(seconds) +"s";

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
        // convert to acceleration unit
        double Ax = Calculations.calculateAcceleration(Integer.parseInt(accelx));
        double Ay = Calculations.calculateAcceleration(Integer.parseInt(accely));
        double Az = Calculations.calculateAcceleration(Integer.parseInt(accelz));
        totalA = Calculations.calculatetotalAcceleration(Ax, Ay, Az);
        alterFiltered[6] = String.format("%.3f", Ax) + "g";
        alterFiltered[7] = String.format("%.3f", Ay) + "g";
        alterFiltered[8] = String.format("%.3f", Az) + "g";

        // gyro x,y,z
        String[] gyroscope = filtered[6].split("#");
        gyrox = gyroscope[0];
        gyroy = gyroscope[1];
        gyroz = gyroscope[2];
        // TODO calculate actual value of gyro
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
            // updating the graph components
            gui.updateLabels(alterFiltered);
            updateAltitudeGraph(seconds, altValue);
            updateAccelerationGraph(seconds,totalA);
            // TODO actual value of GPS
            updateMapMark(45.496067, longitudeTest);
        }
    }

    // update alt graph
    public void updateAltitudeGraph(double time, double alt){
        aGraph.updateAltitudeGraph(time,alt);
        altPanel.repaint();
    }

    // update total acceleration graph
    public void updateAccelerationGraph(double time, double ta){
        taGraph.updateAccelerationGraph(time,ta);
        accelPanel.repaint();
    }

    // update map marker
    public void updateMapMark(double lat, double lon) {
        gGraph.updateGpsGraph(lat,lon);
    }
}

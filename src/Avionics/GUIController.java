package Avionics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controller for the GUI, passing the data to the model and updating the view
 */
public class GUIController extends JFrame {

    private static GUIView view;
    private static GUIModel model;

    // variables for GUI
    private double totalA,seconds,altValue,accelx,accely,accelz;

    // string array to display GUI labels
    private static String[] alterFiltered;

    // testing variable
    static double longitudeTest = -73.569315;

    public GUIController(){

        // creating GUI view and model
        view = new GUIView();
        model = new GUIModel();

        // listeners
        view.addResetListener(new ResetListener());
        view.addFitListener(new FitListener());

    }

    // reset all graphs on click
    private static class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.clearAllGraphs();
        }
    }

    // fit all markers on the gps graph on click
    private static class FitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.fitGpsMarkers();
        }
    }

    // pass unfilered data to the model and update the view
    public void unfiltered (String unfiltered){

        /*** format of the packet
         0          1      2      3                4    5              6  7  8          9 10  11
         <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,         <gyro>
         */

        try{

        // pass to the GUI Model parsing
        alterFiltered = model.parsing(unfiltered);

        // variables to update GUI View graphs
        seconds = Double.parseDouble(alterFiltered[0]);
        altValue = Double.parseDouble(alterFiltered[3]);
        accelx = Double.parseDouble(alterFiltered[6]);
        accely = Double.parseDouble(alterFiltered[7]);
        accelz = Double.parseDouble(alterFiltered[8]);

        // GUI Model to calculate total acceleration
        totalA = model.calculateTotalAcceleration(accelx, accely, accelz);

        // testing purpose
        longitudeTest += 0.005;

        }catch (Exception e){
            System.out.println("error was caught while parsing");
        }finally {

            // updating GUI view
            view.updateGUILabels(alterFiltered);
            view.updateAltitudeGraph(seconds, altValue);
            view.updateAccelerationGraph(seconds,totalA);
            // TODO actual value of GPS
            view.updateMapMark(45.496067, longitudeTest);
        }
    }
}

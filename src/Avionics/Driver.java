package Avionics;


import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static double count = 0;
    private static GUIView view;

    // TODO set baud rate and serial port
    private static XbeeListener groundXbee;

    public static void main(String[] args) {

        view = new GUIView();

        EventQueue.invokeLater(() -> {
            try {

                //readXBeeDevice();
                velocityPlotting();
                //altitudePlotting();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    public static void readXBeeDevice(){

        groundXbee = new XbeeListener("COM1",9600);

        try {
            groundXbee.getDevice().open();
        } catch (XBeeException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // TODO Only read data when launch?

        // read packets every 3 times a second
        Runnable readPacket = () -> {

            // returns an XBeeMessage object containing the a byte data and the source address of the remote node that sent the data
            XBeeMessage xbeeMessage = groundXbee.getDevice().readData();

            // returns the data of the message in string format.
            String packet = xbeeMessage.getDataString();

            // TODO testing for reading device
            System.out.println(packet+"\n");

            // save the packets to a file
            groundXbee.savePackets(packet);

            // filter and update data for GUI
            // TODO last index is timestamp
            String[] arrayPacket = groundXbee.filterPacket(packet);
            view.updateData(arrayPacket);
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(readPacket, 0, 333, TimeUnit.MILLISECONDS);

        groundXbee.getDevice().close();
    }

    public static void velocityPlotting(){
        Runnable vplot = () -> {
            view.updateVelocityGraph(count,count);
            count++;
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(vplot, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public static void altitudePlotting(){
        Runnable aplot = () -> {
           // view.updateAltitudeGraph(alititude, timestamp);
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(aplot, 0, 1000, TimeUnit.MILLISECONDS);
    }

}

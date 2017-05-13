package Avionics;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static ControllerGUI view;

    // test variables
    private static int testInt = 2500;
    private static int testTime = 1000;
    private static int testAlt = 1000;
    private static String testString;

    public static void main(String[] args) {

        view = new ControllerGUI();

        EventQueue.invokeLater(() -> {
            try {
                testing();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void testing(){
        Runnable data = () -> {
            // <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,         <gyro>
            //  950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     101#101#101
            testAlt += 50;
            testString = testTime+","+Integer.toString(testInt++)+",99325,"+Integer.toString(testAlt)+",4529.8360#N#7334.74137#W,101#101#101,101#101#101";
            testTime+=1000;
            view.unfiltered(testString);
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(data, 0 , 1000 , TimeUnit.MILLISECONDS);
    }
}

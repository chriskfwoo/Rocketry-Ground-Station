package Avionics;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestDriver {

    private static ControllerGUI view;
    private static Date date= Calendar.getInstance().getTime();

    // test variables
    private static int testInt = 2500;
    private static int testTime = 1000;
    private static int testAlt = 1000;
    private static String testString;
    private static File file;
    private static FileWriter writer;
    private static PrintWriter pw;
    public static void main(String[] args) {

        view = new ControllerGUI();

        // for production
         File jarFile = new File(TestDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath());
         file = new File(jarFile.getParentFile().getParent(), "data_logs_"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");

        // for testing
        //file = new File("./src/Avionics/logs/data_logs"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");
;

        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw = new PrintWriter(writer);
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
            System.out.println(testString);
            view.unfiltered(testString);
            pw.write(testString+"\n");
            pw.close();
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(data, 0 , 1000 , TimeUnit.MILLISECONDS);
    }
}

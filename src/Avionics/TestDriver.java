package Avionics;

import java.awt.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestDriver {

    private static GUIController guiController;
    private static Date date= Calendar.getInstance().getTime();

    // test variables
    private static int testInt = 100000;
    private static int testTime = 1000;
    private static int testTemp = 1;
    private static String testString;
    private static File file;
    private static PrintWriter writer;

    public static void main(String[] args) {

        guiController = new GUIController();

        /**
         * writing to file -> data_logs_#dateday_#datehours_#datemins  , date = 0 = Sunday
         */

        //File jarFile = new File(TestDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //file = new File(jarFile.getParentFile(), "/data_logs_"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");
        //System.out.println(file);

        // for testing
        file = new File("./src/Avionics/logs/data_logs"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");

        try {
            writer = new PrintWriter(new FileOutputStream(file,true));
        } catch (FileNotFoundException e) {
            System.out.println("can't write to file");
        }

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
            // <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,      <temp>
            //  950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     50
            testString = testTime+",4000,"+Integer.toString(testInt)+",1000,4529.8360#N#7334.74137#W,101#101#101,"+testTemp;
            testTime+=1000;
            testInt = testInt + 500;
            testTemp = testTemp + 1;
            System.out.println(testString);

            // GUI controller
            guiController.unfiltered(testString);
            writer.write(testString+"\n");
            writer.close();
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(data, 0 , 1000 , TimeUnit.MILLISECONDS);
    }
}

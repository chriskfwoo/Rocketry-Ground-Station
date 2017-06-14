package Avionics;

import jssc.*;

import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


/**
 * JAVA SIMPLE SERIAL CONNECTOR - READ COMMUNICATION FROM ROCKET - MAIN DRIVER
 */
public class JSSC {

    private static SerialPort serialPort;
    private static GUIController guiController;
    private static File file;
    private static PrintWriter writer;
    private static Date date= Calendar.getInstance().getTime();

    public static void main(String[] args) {

        // getting serial ports list into the array
        String[] portNames = SerialPortList.getPortNames();

        if (portNames.length == 0) {
            System.out.println("Press Enter to exit...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }

        // must copy exact string
        System.out.println("Type port name, which you want to use, and press Enter...");
        Scanner in = new Scanner(System.in);
        String portName = in.next();

        serialPort = new SerialPort(portName);

        try {

            // setting up serial port
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_57600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);

            // initializing GUI controller
            guiController = new GUIController();

            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

        }
        catch (SerialPortException ex) {
            System.out.println("There are an error on writing string to port т: " + ex);
        }
    }

    private static class PortReader implements SerialPortEventListener {
        StringBuilder message = new StringBuilder();
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    byte buffer[] = serialPort.readBytes();
                    for (byte b: buffer) {

                        // end of packet
                        if ( (b == '\r' || b == '\n') && message.length() > 0) {

                            // one packet
                            String toProcess = message.toString();
                            System.out.println(toProcess);

                            /**
                             * writing to file -> data_logs_#dateday_#datehours_#datemins  , date = 0 = Sunday
                             */
                            // for production saved at location of executable JAR
                             File jarFile = new File(TestDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath());
                             file = new File(jarFile.getParentFile().getParent(), "/data_logs_"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");


                            // testing
                            //file = new File("./src/Avionics/logs/data_logs"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");
                            try {
                                writer = new PrintWriter(new FileOutputStream(file,true));
                            } catch (FileNotFoundException e) {
                                System.out.println("can't write to file");
                            }

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                // GUI Controller
                                public void run() {
                                    guiController.unfiltered(toProcess);
                                    writer.write(toProcess+"\n");
                                    writer.close();
                                }
                            });

                            message.setLength(0);
                        }
                        else {
                            //<msTick>,<pitot>,<bar>,<gpsAlt>,<gpsPos>,<accel>,<gyro>
                            message.append((char)b);
                        }
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
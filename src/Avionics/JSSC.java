package Avionics;

import jssc.*;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


/**
 * JAVA SIMPLE SERIAL CONNECTOR - READ COMMUNICATION FROM ROCKET - MAIN DRIVER
 */
public class JSSC {
    private static SerialPort serialPort;
    private static ControllerGUI view;
    private static File file;
    private static FileWriter writer;
    private static PrintWriter pw;

    private static Date date= Calendar.getInstance().getTime();


    public static void main(String[] args) {

        // writing to file -> data_logs_#dateday_#datehours_#datemins  , date = 0 = Sunday
        // saved at location of JAR

        // File jarFile = new File(TestDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        // file = new File(jarFile.getParentFile().getParent(), "/logs/data_logs_"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");

        // for testing
        file = new File("./src/Avionics/logs/data_logs"+date.getDay()+"-"+date.getHours()+"-"+date.getMinutes()+".csv");

        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw = new PrintWriter(writer);

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
            serialPort.openPort();

            serialPort.setParams(SerialPort.BAUDRATE_57600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);

            view = new ControllerGUI();

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
                        if ( (b == '\r' || b == '\n') && message.length() > 0) {
                            String toProcess = message.toString();
                            System.out.println(toProcess);

                            // write to file
                            pw.write(toProcess+"\n");
                            pw.close();

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    view.unfiltered(toProcess);
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
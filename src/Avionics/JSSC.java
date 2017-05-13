package Avionics;

import jssc.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class JSSC {
    private static SerialPort serialPort;

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

//        System.out.println("Type port name, wshich you want to use, and press Enter...");
//        Scanner in = new Scanner(System.in);s
//        String portName = in.next();

        serialPort = new SerialPort("/dev/ttyUSB0");

        try {
            serialPort.openPort();

            serialPort.setParams(SerialPort.BAUDRATE_57600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);

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

                            PrintWriter pw = new PrintWriter(new FileWriter("data_logs.csv", true));
                            pw.write(toProcess+"\n");
                            pw.close();
                            //   Platform.runLater(new Runnable() {
                            //     @Override public void run() {
                            //      processMessage(toProcess);
                            //      }
                            //    });
                            message.setLength(0);
                        }
                        else {
                            //<msTick>,<pitot>,<bar>,<gpsAlt>,<gpsPos>,<accel>,<gyro>
                            //950,2048,99325,167.8,4529.8360#N#7334.74137#W,101#101#101,101#101#101
                            //System.out.print((char)b);
                            message.append((char)b);
                        }
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
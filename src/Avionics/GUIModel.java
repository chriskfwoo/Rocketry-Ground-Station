package Avionics;

import java.math.BigDecimal;

/**
 * Creating all the GUI Model for all the view logic, filtering the packets, calculations methods
 */
public class GUIModel {

    public static void main(String[] args) {
        System.out.println(calculateAcceleration(32767));
    }

    // variables for GUI
    private static String timestamp, pitot, barometer, altitude,latitude,longitude,accelx,accely,accelz,temp;
    private static double seconds;

    public static double calculateAcceleration(int value){
        // range: -32768 to 32767

        double answer = value *  0.012;
        return answer;
    }

    public static double calculateTotalAcceleration(double x, double y, double z){
        // total acceleration = sqrt(x^2 + y^2 + z^2)
        double answer = Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
        return answer;
    }

    // TODO formula for altitude
    public static double calculateAltitude(int baro, double temp){
        double answer = 2;
        return answer;
    }

    // parse the string to return a string array to display on GUI
    public static String[] parsing(String unfiltered){
        /*** format of the packet
         0          1      2      3                4    5              6  7  8          9
         <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,    <temp>
         950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     50

         alterFiltered
         <msTick>,<pitot>,<bar>,<gpsAlt>,<accel>,<temp>

         */

        // parsing the packet
        String[] filtered = unfiltered.split(",");

        // modifying the filtered array to properly display on GUI
        String[] alterFiltered = new String[8];

            // timestamp (ms)
            timestamp = filtered[0];
            seconds = Double.parseDouble(timestamp) / 1000.0;
            alterFiltered[0] = Double.toString(seconds);

            // pitot
            pitot = filtered[1];
            alterFiltered[1] = pitot;

            // barometer
            barometer = filtered[2];
            alterFiltered[2] = barometer;

            // acceleration x,y,z
            String[] acceleration = filtered[5].split("#");
            accelx = acceleration[0];
            accely = acceleration[1];
            accelz = acceleration[2];
            // convert to acceleration unit
            double Ax = calculateAcceleration(Integer.parseInt(accelx));
            double Ay = calculateAcceleration(Integer.parseInt(accely));
            double Az = calculateAcceleration(Integer.parseInt(accelz));
            alterFiltered[4] = String.format("%.3f", Ax);
            alterFiltered[5] = String.format("%.3f", Ay);
            alterFiltered[6] = String.format("%.3f", Az);

            // temp
            temp = filtered[6];
            alterFiltered[7] = temp;

            // TODO get alitude with barometer and temp
            altitude = filtered[3];
            alterFiltered[3] = altitude;

            // return string array to display on GUI
            return  alterFiltered;
    }
}

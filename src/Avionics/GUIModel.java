package Avionics;

import java.math.BigDecimal;

/**
 * Creating all the GUI Model for all the view logic, filtering the packets, calculations methods
 */
public class GUIModel {

    public static void main(String[] args) {
        System.out.println(calculateAcceleration(32767));
        System.out.println(calculateGyroscope(32767));
    }

    // variables for GUI
    private static String timestamp, pitot, barometer, altitude,latitude,longitude,accelx,accely,accelz,gyrox,gyroy,gyroz;
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

    public static BigDecimal calculateGyroscope(int value){
        // -32768 to 32767
        // For gyro : (4000/(2^12))*(x+32768)-2000
        BigDecimal answer = new BigDecimal(value+32768).multiply(new BigDecimal("0.9765625")).add(new BigDecimal("-2000"));
        return answer;
    }

    // TODO calculate pitot tube
    public static void calculatePitotTube(int range){
        // ADC 3.3
    }

    // parse the string to return a string array to display on GUI
    public static String[] parsing(String unfiltered){
        /*** format of the packet
         0          1      2      3                4    5              6  7  8          9 10  11
         <msTick>,<pitot>,<bar>,<gpsAlt>,          <gpsPos>,            <accel>,         <gyro>
         950,     2048,   99325, 167.8,   4529.8360#N#7334.74137#W,  101#101#101,     101#101#101
         */

        // parsing the packet
        String[] filtered = unfiltered.split(",");

        // modifying the filtered array to properly display on GUI
        String[] alterFiltered = new String[12];

            // timestamp (ms)
            timestamp = filtered[0];
            seconds = Double.parseDouble(timestamp) / 1000.0;
            alterFiltered[0] = Double.toString(seconds);

            // pitot
            pitot = filtered[1];
            // TODO calculate actual value of pitot
            alterFiltered[1] = pitot;

            // barometer
            barometer = filtered[2];
            alterFiltered[2] = barometer;

            // altitude
            altitude = filtered[3];
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
            double Ax = calculateAcceleration(Integer.parseInt(accelx));
            double Ay = calculateAcceleration(Integer.parseInt(accely));
            double Az = calculateAcceleration(Integer.parseInt(accelz));
            alterFiltered[6] = String.format("%.3f", Ax);
            alterFiltered[7] = String.format("%.3f", Ay);
            alterFiltered[8] = String.format("%.3f", Az);

            // gyro x,y,z
            String[] gyroscope = filtered[6].split("#");
            gyrox = gyroscope[0];
            gyroy = gyroscope[1];
            gyroz = gyroscope[2];
            // TODO calculate actual value of gyro
            BigDecimal Gx = calculateGyroscope(Integer.parseInt(gyrox));
            BigDecimal Gy = calculateGyroscope(Integer.parseInt(gyroy));
            BigDecimal Gz = calculateGyroscope(Integer.parseInt(gyroz));
            alterFiltered[9] = Gx +"";
            alterFiltered[10] = Gy + "";
            alterFiltered[11] = Gz + "";

            // return string array to display on GUI
            return  alterFiltered;
    }
}

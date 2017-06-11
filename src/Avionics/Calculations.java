package Avionics;


import java.math.BigDecimal;

/**
 * calculations for the GUI when parsing the data to display
 */
public class Calculations {

    public static void main(String[] args) {
        calculateAcceleration(32767);
        calculateGyroscope(32767);
    }

    public static double calculateAcceleration(int value){
        // -32768 to 32767
        // For accel : (32/(2^12))*(x+32768)-16

        double answer = value *  0.012;
        return answer;
    }

    public static double calculatetotalAcceleration(double x, double y, double z){
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

    public static void calculatePitotTube(int range){
        // ADC 3.3

    }
}

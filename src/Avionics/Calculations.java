package Avionics;


import java.math.BigDecimal;

/**
 * calculations for the GUI when parsing the data to display
 */
public class Calculations {

    public static void main(String[] args) {

        calculateAcceleration(32767);
        calculateGyroscope(32767);
        System.out.println(0.1+0.1);

    }

    public static BigDecimal calculateAcceleration(int value){
        // -32768 to 32767
        // For accel : (32/(2^16))*(x+32768)-16
        BigDecimal answer = new BigDecimal(value+32768).multiply(new BigDecimal("0.00048828125")).add(new BigDecimal("-16"));
        System.out.println(answer);
        return answer;
    }

    public static BigDecimal calculateGyroscope(int value){
        // -32768 to 32767
        // For gyro : (4000/(2^16))*(x+32768)-2000
        BigDecimal answer = new BigDecimal(value+32768).multiply(new BigDecimal("0.061035156")).add(new BigDecimal("-2000"));
        System.out.println(answer);
        return answer;
    }

    public static void calculatePitotTube(int range){
        // ADC 3.3

    }
}

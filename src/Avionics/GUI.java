package Avionics;


import javax.swing.*;
import java.awt.*;

/**
 * Initializing the GUI
 * Created by Chris Woo 2/10/2017.
 */
public class GUI {

    private JFrame groundStation;
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    private JLabel accelXLabel;
    private JLabel accelYLabel;
    private JLabel accelZLabel;
    private JLabel altitudeLabel;
    private JLabel latitudeLabel;
    private JLabel longitudeLabel;
    private JPanel dataPanel;
    private JPanel altitudeGraph;
    private JPanel accelerationGraph;
    private JPanel gpsPanel;
    private JLabel logoLabel;
    private JLabel pitotLabel;
    private JLabel baroLabel;
    private JLabel canadalogo;
    private JLabel mauriceLogo;
    private JButton resetBtn;
    private JLabel timeLabel;
    private JLabel tempLabel;
    private JPanel logoPanel;
    private JLabel spaceLogo;
    private JLabel rocketLogo;
    private JPanel tempGraph;


    public GUI() {
        initialize();
    }

    private void initialize() {
        groundStation = new JFrame("Ground Station");
        groundStation.getContentPane().add(mainPanel);
        groundStation.setBounds(100, 100, 1250, 850);
        groundStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canadalogo.setIcon(new ImageIcon(getClass().getResource("res/canada.png")));
        spaceLogo.setIcon(new ImageIcon(getClass().getResource("res/logo.png")));
        rocketLogo.setIcon(new ImageIcon(getClass().getResource("res/maurice.png")));
        groundStation.setVisible(true);

    }


    public JButton getResetBtn(){
        return resetBtn;
    }

    public JPanel getAltitudeGraph(){
        return altitudeGraph;
    }

    public JPanel getAccelerationGraph(){
        return accelerationGraph;
    }

    public JPanel getTempGraph(){
        return tempGraph;
    }

    public void updateLabels(String[] alterPacket){

        // time
        timeLabel.setText(alterPacket[0]);
        // pitot
        pitotLabel.setText(alterPacket[1]);
        // baro
        baroLabel.setText(alterPacket[2]);
        // gpsAlt
        altitudeLabel.setText(alterPacket[3]);
        // accel
        accelXLabel.setText(alterPacket[4]);
        accelYLabel.setText(alterPacket[5]);
        accelZLabel.setText(alterPacket[6]);
        // temperature
        tempLabel.setText(alterPacket[7]);
    }
}



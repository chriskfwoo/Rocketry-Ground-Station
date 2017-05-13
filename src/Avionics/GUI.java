package Avionics;


import javax.swing.*;

/**
 * Initializing the GUI
 * Created by Woozzie on 2/10/2017.
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
    private JLabel gyroXLabel;
    private JLabel gyroYLabel;
    private JLabel gyroZLabel;
    private JPanel dataPanel;
    private JPanel altitudeGraph;
    private JPanel velocityGraph;
    private JPanel googleMaps;
    private JLabel logoLabel;
    private JLabel pitotLabel;
    private JLabel baroLabel;


    public GUI() {
        initialize();
    }

    private void initialize() {
        groundStation = new JFrame("Ground Station");
        groundStation.getContentPane().add(mainPanel);
        groundStation.setBounds(100, 100, 1250, 850);
        groundStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logoLabel.setIcon(new ImageIcon(getClass().getResource("res/logo2.png")));
        groundStation.setVisible(true);

    }

    public JPanel getAltitudeGraph(){
        return altitudeGraph;
    }

    public JPanel getVelocityGraphGraph(){
        return velocityGraph;
    }

    public JPanel getGoogleMaps(){
        return googleMaps;
    }

    public void updateLabels(String[] alterPacket){

        // TODO determine array order and handle edge cases
        // pitot
        pitotLabel.setText(alterPacket[1]);
        // baro
        baroLabel.setText(alterPacket[2]);
        // gpsAlt
        altitudeLabel.setText(alterPacket[3]);
        // gpsPos
        latitudeLabel.setText(alterPacket[4]);
        longitudeLabel.setText(alterPacket[5]);
        // accel
        accelXLabel.setText(alterPacket[6]);
        accelYLabel.setText(alterPacket[7]);
        accelZLabel.setText(alterPacket[8]);
        // gyro
        gyroXLabel.setText(alterPacket[9]);
        gyroYLabel.setText(alterPacket[10]);
        gyroZLabel.setText(alterPacket[11]);
    }
}



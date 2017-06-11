package Avionics;


import javax.swing.*;

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
    private JLabel gyroXLabel;
    private JLabel gyroYLabel;
    private JLabel gyroZLabel;
    private JPanel dataPanel;
    private JPanel altitudeGraph;
    private JPanel accelerationGraph;
    private JPanel gpsPanel;
    private JLabel logoLabel;
    private JLabel pitotLabel;
    private JLabel baroLabel;
    private JLabel canadalogo;
    private JButton fitMarkersBtn;
    private JLabel mauriceLogo;
    private JButton resetBtn;
    private JLabel timeLabel;
    private JPanel gpsMapHelper;


    public GUI() {
        initialize();
    }

    private void initialize() {
        groundStation = new JFrame("Ground Station");
        groundStation.getContentPane().add(mainPanel);
        groundStation.setBounds(100, 100, 1250, 850);
        groundStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logoLabel.setIcon(new ImageIcon(getClass().getResource("res/logo.png")));
        canadalogo.setIcon(new ImageIcon(getClass().getResource("res/canada.png")));
        mauriceLogo.setIcon(new ImageIcon(getClass().getResource("res/maurice.png")));
        groundStation.setVisible(true);

    }

    public JButton getFitMarkersBtn(){
        return fitMarkersBtn;
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

    public JPanel getGpsPanel(){
        return gpsPanel;
    }
    public JPanel getGpsMapHelper() {
        return gpsMapHelper;
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



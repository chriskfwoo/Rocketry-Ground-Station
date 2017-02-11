package Avionics;


import javax.swing.*;

/**
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

    public JPanel getVelocityGraphGraph(){return velocityGraph;}

    public JPanel getGoogleMaps(){return googleMaps;}

    public void updateLabels(String[] filterPacket){

        // TODO determine array order and handle edge cases
        altitudeLabel.setText(filterPacket[0]);
        latitudeLabel.setText(filterPacket[1]);
        longitudeLabel.setText(filterPacket[2]);
        accelXLabel.setText(filterPacket[3]);
        accelYLabel.setText(filterPacket[4]);
        accelZLabel.setText(filterPacket[5]);
        gyroXLabel.setText(filterPacket[6]);
        gyroYLabel.setText(filterPacket[7]);
        gyroZLabel.setText(filterPacket[8]);


    }


}



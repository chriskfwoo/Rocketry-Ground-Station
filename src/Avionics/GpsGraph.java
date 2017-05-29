package Avionics;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.*;

/**
 * Created by chris on 5/28/17.
 */
public class GpsGraph extends JFrame {

    private final JMapViewer map = new JMapViewer();


    public JMapViewer getMap() {
        return map;
    }
}

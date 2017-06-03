package Avionics;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;

/**
 * Created by chris on 5/28/17.
 */
public class GpsGraph extends JFrame {

    private final JMapViewer map = new JMapViewer();

    // final JMapViewer map = new JMapViewer(new MemoryTileCache(),4);
    // map.setTileLoader(new OsmFileCacheTileLoader(map));
    // new DefaultMapController(map);

    public JMapViewer getMap() {
        return map;
    }

    public void updateGpsGraph(double lat, double lon){
        map.addMapMarker(new MapMarkerDot(lat,lon));
    }
}

package Avionics.Graphs;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import javax.swing.*;

/**
 * Created by chris on 5/28/17.
 */
public class GpsGraph extends JFrame {

    private final JMapViewer map = new JMapViewer();

    public GpsGraph(){
//        map.setDisplayPosition(45,-73,12);
    }

    // TODO OFFLINE TILES

    // final JMapViewer map = new JMapViewer(new MemoryTileCache(),4);
    // map.setTileLoader(new OsmFileCacheTileLoader(map));
    // new DefaultMapController(map);

    public JMapViewer getMap() {
        return map;
    }

    public void updateGpsGraph(double lat, double lon){
        map.addMapMarker(new MapMarkerDot(lat,lon));
    }

    public void seeMakers(){
        map.setDisplayToFitMapElements(true, true, true);
    }

    public void clear(){
        map.removeAllMapMarkers();
    }
}

package Avionics.graphs;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;

/**
 * Created by chris on 5/28/17.
 */
public class GpsGraph extends JFrame {

    private final JMapViewer map = new JMapViewer();

    public GpsGraph(){
    // map.setDisplayPosition(45,-73,12);
    }

    // TODO OFFLINE TILES

    // final JMapViewer map = new JMapViewer(new MemoryTileCache(),4);
    // map.setTileLoader(new OsmFileCacheTileLoader(map));
    // new DefaultMapController(map);

    public JMapViewer getMap() {
        return map;
    }

    // update gps markers
    public void updateGpsGraph(double lat, double lon){
        map.addMapMarker(new MapMarkerDot(lat,lon));
    }

    // fit all the markers on the graph
    public void seeMakers(){
        map.setDisplayToFitMapElements(true, true, true);
    }

    // clear the graph
    public void clear(){
        map.removeAllMapMarkers();
    }
}

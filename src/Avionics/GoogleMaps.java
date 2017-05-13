//package Avionics;
//
//import com.teamdev.jxmaps.*;
//import com.teamdev.jxmaps.swing.MapView;
//
//public class GoogleMaps extends MapView {
//
//    private Map map;
//
//    public Map getGUIMap() {
//        return map;
//    }
//
//
//    public GoogleMaps(MapViewOptions options) {
//        super(options);
//        setOnMapReadyHandler(new MapReadyHandler() {
//            @Override
//            public void onMapReady(MapStatus status) {
//                if (status == MapStatus.MAP_STATUS_OK) {
//                    map = getMap();
//                    map.setZoom(14.0);
//                    GeocoderRequest request = new GeocoderRequest(map);
//                    request.setAddress("1455 De Maisonneuve Blvd. W. Montreal, Quebec, Canada. H3G 1M8");
//
//
//                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
//                        @Override
//                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
//                            if (status == GeocoderStatus.OK) {
//                                map.setCenter(result[0].getGeometry().getLocation());
//                                Marker marker = new Marker(map);
//
//                                Icon icon = new Icon();
//                                icon.loadFromStream(this.getClass().getResourceAsStream("res/rocketmark.png"), "png");
//                                marker.setIcon(icon);
//
//                                marker.setPosition(result[0].getGeometry().getLocation());
//
//                                //   final InfoWindow window = new InfoWindow(map);
////                                window.setContent("Hello, World!");
//                                //     window.open(map, marker);
//
//                                // TODO a way to continous add markers
//                                Marker marker2 = new Marker(map);
//
//                                // TODO make sure the latlng is accurate
//                                LatLng test = new LatLng(45.496067, -73.569315);
//                                marker2.setPosition(test);
//                                marker2.setIcon(icon);
//                            }
//                        }
//                    });
//                }
//            }
//        });
//
//
//    }
//
////    public void addMarkers() {
////        Marker marker = new Marker(this.getGUIMap());
////
////        Icon icon = new Icon();
////        icon.loadFromStream(this.getClass().getResourceAsStream("res/rocketmark.png"), "png");
////        marker.setIcon(icon);
////
////        LatLng test = new LatLng(45.504784, -73.57715);
////        marker.setPosition(test);
////        marker.setIcon(icon);
////    }
//
//}
//

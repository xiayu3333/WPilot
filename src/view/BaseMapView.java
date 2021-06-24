package view;

import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import data.cartography.*;
import data.cartography.xanthane.*;
import javafx.fxml.FXML;

public class BaseMapView extends Pane {

    @FXML
    public static Pane baseMapView = new Pane();
    static CartographyManagerXanthane cartographyManagerXanthane = new CartographyManagerXanthane();
    static IBaseMap baseMap = cartographyManagerXanthane.loadBaseMap("data_maps/france.xml");
    static List<ISector> sectors = cartographyManagerXanthane.loadSectors("data_maps/sectors.xml", 100); //Slice FL=100.
    private BeaconView beaconView; //this one is useless for getBeaconView method -> rotate!

    private void drawBaseMap(){
        //Draw France outlines.
        List<IZone> outlines = baseMap.getZones();
        for (IZone outline : outlines) {
            List<IPoint> vertexes = outline.getVertexes();
            Polygon outlinePolygon = new Polygon();
            outlinePolygon.setStroke(Color.BLACK);
            outlinePolygon.setFill(Color.TRANSPARENT);
            for (IPoint vertex : vertexes) {
                outlinePolygon.getPoints().addAll(new Double[]{vertex.getX(), vertex.getY()});
            }
            baseMapView.getChildren().add(outlinePolygon);
        }

        //Draw sectors outlines.
        for (ISector sector : sectors) {
            List<IZone> zone = sector.getSlices();
            for (IZone outline : zone) {
                List<IPoint> vertexes = outline.getVertexes();
                Polygon outlinePolygon = new Polygon();
                outlinePolygon.setStrokeWidth(0.4);
                outlinePolygon.setStroke(Color.BLACK);
                outlinePolygon.setFill(Color.TRANSPARENT);

                for (IPoint vertex : vertexes) {
                    //System.out.println(vertex.getX() +", " + vertex.getY());
                    outlinePolygon.getPoints().addAll(new Double[]{vertex.getX(), vertex.getY()});
                }
                baseMapView.getChildren().add(outlinePolygon);
            }
        }
       
        //Layer on beaconView.
        BeaconView beaconView = new BeaconView(); /*Must have this code to re-allocate beacons position? 
        even we created it before at line 18 -> can not rotate beacon
        beaconView is a new variable ont existed inside drawBaseMap!!!
        */
        baseMapView.getChildren().add(beaconView);
        
    }
    
    public BaseMapView() {
        super.getChildren().add(baseMapView);
        baseMapView.setMouseTransparent(true);
        beaconView = new BeaconView();
        drawBaseMap();        
    }
    
    public BeaconView getBeaconView() {
        return beaconView; //this line return beaconView from line 18, not return beaconView from line 53, so rotate beacon wont work
    }   
}

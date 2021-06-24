package view.controls;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;
import static jdk.nashorn.internal.objects.NativeArray.map;
import view.BaseMapView;

public class RotateMap {

    public static void rotateCompass(Node node, double angle) {
        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        node.getTransforms().add(rotate);
    }

    public static void rotateLabelWaypoint(ArrayList<Label> lsLabel, double angle) {
        Rotate rotate = new Rotate();
        rotate.setAngle(-angle);
        for (Label lb : lsLabel) {
            lb.getTransforms().add(rotate);
        }
    }

    public static void rotateBasemap(BaseMapView map, double angle) {
        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        map.getTransforms().add(rotate);
        
        /*for(int i=0;i<map.getChildren().size();i++)
            System.out.println("Children ["+i+"]="+map.getChildren().get(i));*/
        //Rotate beacons to the reverse direction to offset.
        for (Node beacon : map.getBeaconView().getChildren()) {
            Rotate rotateBeacon = new Rotate();
            //System.out.println(beacon. "Rotate ");
            rotateBeacon.setAngle(-angle);
            beacon.getTransforms().add(rotateBeacon);
        }
    }

}

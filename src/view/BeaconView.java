package view;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import data.cartography.IBeacon;
import data.cartography.xanthane.CartographyManagerXanthane;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class BeaconView extends Pane {

    private Group beaconView = new Group();
    CartographyManagerXanthane cartographyManagerXanthane = new CartographyManagerXanthane();
    List<IBeacon> beacons = cartographyManagerXanthane.loadBeacons("data_maps/points.xml");
    private static ArrayList<String> list;

    public BeaconView() {
        super.getChildren().add(beaconView);

        for (IBeacon beacon : beacons) {
            Group currentbeacon = new Group();
            Pane beaconIcon = (Pane) drawBeaconIcon();
            currentbeacon.getChildren().add(beaconIcon);
            beaconIcon.setLayoutX(beacon.getX());
            beaconIcon.setLayoutY(beacon.getY());

            Label beaconLabel = new Label(beacon.getCode());
            beaconLabel.setStyle("-fx-font-size:2px;  -fx-font-color:dimgray; -fx-text-fill:dimgray;");
            currentbeacon.getChildren().add(beaconLabel);
            beaconLabel.layoutXProperty().bind((beaconIcon.layoutXProperty()).subtract(1));
            beaconLabel.layoutYProperty().bind((beaconIcon.layoutYProperty()).add(0));

            beaconView.getChildren().add(currentbeacon);
        }
    }

    static Pane drawBeaconIcon() {
        SVGPath svgPathBeacon = new SVGPath();
        String path = "M1 1 L-1 1 L0 -1 L1 1 Z";
        svgPathBeacon.setContent(path);
        svgPathBeacon.setFill(Color.GRAY);
        Pane beaconIcon = new Pane();
        beaconIcon.getChildren().add(svgPathBeacon);
        return beaconIcon;
    }
    
    public ArrayList<String> getBeaconList() {
        list = new ArrayList<>();
        for (IBeacon beacon : beacons) {
            list.add(beacon.getCode());
        }
        return list;
    }
}

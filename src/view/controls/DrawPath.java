package view.controls;

import data.cartography.Point;
import flightmanager.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

public class DrawPath extends MapController {

    private ArrayList<String> route;
    private Pane pane;

    public DrawPath(Pane pane, ArrayList<String> route) {
        this.pane = pane;
        this.route = route;
    }

    public void doDraw() {
        BeaconList dict = new BeaconList();
        Path path = new Path();
        Point2D p1;
        Point2D p2;

        for (Node node : pane.getChildren()) {
            if (node instanceof Path) {
                pane.getChildren().remove(node);
                break;
            }
        }

        p1 = new Point2D(dict.getBeaconX(route.get(0)), dict.getBeaconY(route.get(0)));

        path.setStrokeWidth(0.5);
        path.getElements().add(new MoveTo(p1.getX(), p1.getY()));

        for (int i = 1; i < route.size(); i++) {
            p2 = new Point2D(dict.getBeaconX(route.get(i)), dict.getBeaconY(route.get(i)));
            path.getElements().add(new LineTo(p2.getX(), p2.getY()));
            p1 = p2;
        }
        path.setStroke(Color.CYAN);
        path.setStrokeWidth(0.3);
        pane.getChildren().add(path);
        path.toBack();
    }

}

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

public class DrawWaypoint extends MapController {

    private ArrayList<String> route;
    private Pane pane;

    public DrawWaypoint(Pane pane, ArrayList<String> route) {
        this.pane = pane;
        this.route = route;
    }

    public void doDraw(ArrayList<Label> lsLabel, ArrayList<Circle> lsCircle) {
        BeaconList dict = new BeaconList();
        Point2D point;

        if (!lsLabel.isEmpty()) {
            for (Label lb : lsLabel) {
                //System.out.println("remove label " + lb.getText());
                pane.getChildren().remove(lb);
            }
            //System.out.println("lsLabel " + lsLabel.size());
            lsLabel.clear();
        }

        if (!lsCircle.isEmpty()) {
            for (Circle c : lsCircle) {
                pane.getChildren().remove(c);
            }
            //System.out.println("lsCirle " + lsCircle.size());
            lsCircle.clear();
        }

        for (int i = 0; i < route.size(); i++) {
            point = new Point2D(dict.getBeaconX(route.get(i)), dict.getBeaconY(route.get(i)));

            Circle circle = new Circle(0.7, Color.web("ffd11a"));
            circle.setLayoutX(point.getX());
            circle.setLayoutY(point.getY());
            lsCircle.add(circle);

            Label label = new Label(route.get(i));
            label.setStyle("-fx-font-size:3px; -fx-text-fill:#ffd11a; -fx-font-weight: bold");
            label.setLayoutX(point.getX() + 1);
            label.setLayoutY(point.getY() + 1);
            lsLabel.add(label);
        }

        for (Label lb : lsLabel) {
            pane.getChildren().add(lb);
        }
        for (Circle c : lsCircle) {
            pane.getChildren().add(c);
        }
    }

}

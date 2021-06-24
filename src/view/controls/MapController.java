/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import flightmanager.FlightInfo;
import java.util.ArrayList;
import java.util.Hashtable;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import view.BaseMapView;
import view.BeaconView;

/**
 *
 * @author Nhut Minh Ngo
 */
public class MapController {

    private static BaseMapView map;
    private double currentHeading, posX, posY, angle;
    private Hashtable<String, FlightInfo> aircraft;
    private ArrayList<Label> lsWaypointName;
    private ArrayList<Circle> lsWaypointShape; 
    private ArrayList<String> route;

    private FlightInfo flight;
    private RotateMap rotate;

    @FXML
    private Group compass;

    @FXML
    private Label lbHeading;

    @FXML
    private ImageView boxHeading;

    @FXML
    public void rotate() {
        angle = currentHeading - flight.getHeading();;
        rotate.rotateLabelWaypoint(lsWaypointName, angle);
        rotate.rotateCompass(compass, angle);
        rotate.rotateBasemap(map, angle);
        currentHeading = flight.getHeading();;
    }

    @FXML
    public void move() {
        MoveTo.MoveToXY(map, posX, posY);
    }

    @FXML
    public void drawRoute() {
        //System.out.println("Draw route with " + route);
        DrawPath draw01 = new DrawPath(map, route);
        draw01.doDraw();
        DrawWaypoint draw02 = new DrawWaypoint(map, route);
        draw02.doDraw(lsWaypointName, lsWaypointShape);
    }

    public void setBaseMap(BaseMapView baseMapView) {
        this.map = baseMapView;
    }

    public void setFlightInfo(Hashtable<String, FlightInfo> aircraft) {
        this.aircraft = aircraft;
        this.flight = aircraft.get("4996");
        this.route = aircraft.get("4996").getRoute();
        this.posX = flight.getPosition().getX();
        this.posY = -flight.getPosition().getY();
        displayHeading();
    }

    @FXML
    public void displayHeading() {
        FlightInfo flight = aircraft.get("4996");
        lbHeading.setText(flight.getHeading() + "º");
    }

    public void initialize() {
        currentHeading = 0;
        lsWaypointName = new ArrayList<>();
        lsWaypointShape = new ArrayList<>();
    }
}

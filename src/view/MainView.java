package view;

import flightmanager.Communication;
import fr.dgac.ivy.IvyException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import view.controls.*;

public class MainView {

    private ControlController controlCtr;
    private MapController mapCtr;
    private InfoController infoCtr;
    private Communication communication;
    private static BaseMapView map;


    public MainView() {
        controlCtr = new ControlController();
        mapCtr = new MapController();
        infoCtr = new InfoController();
    }

    private void runIvy() {
        try {
            communication = new Communication(controlCtr, mapCtr, infoCtr);
            communication.listener();
        } catch (IvyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ivy bus had a failure.");
            alert.setHeaderText(null);
            alert.setContentText("Quitting IvyRejeuJavaFXApplication...");
            alert.showAndWait();
        }
    }

    public Scene createView() {
        StackPane root = new StackPane();
        map = new BaseMapView();
        Scene scene = new Scene(root, 1200, 600);
        //Scene scene = new Scene(root, 1188, 588);
        runIvy();
        //root.setStyle("-fx-background-color:black");
        try {
            FXMLLoader loader = new FXMLLoader(MainView.class.getResource("CompassView.fxml"));
            loader.setController(mapCtr);
            mapCtr.setBaseMap(map);
            Pane paneND = new Pane();
            paneND.setPrefSize(600, 600);
            paneND = (Pane) loader.load();
            paneND.getChildren().add(map);
            map.setTranslateX(300);
            map.setTranslateY(300);
            //paneND.setTranslateY(250);
            root.getChildren().add(paneND);
            root.setAlignment(paneND, Pos.TOP_CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(MainView.class.getResource("ControlView.fxml"));
            loader.setController(controlCtr);
            controlCtr.setBaseMap(map);
            controlCtr.setIvy(communication);
            Pane paneControl = new Pane();
            paneControl.setPrefSize(300, 600);
            paneControl = (Pane) loader.load();
            //paneNDRight.setTranslateX(5);
            root.getChildren().add(paneControl);
            root.setAlignment(paneControl, Pos.TOP_RIGHT);
            //root.getChildren().get(1).toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(MainView.class.getResource("InfoView.fxml"));
            loader.setController(infoCtr);
            Pane paneNDBottom = new Pane();
            paneNDBottom.setPrefSize(600, 40);
            paneNDBottom = (Pane) loader.load();
            root.getChildren().add(paneNDBottom);
            root.setAlignment(paneNDBottom, Pos.BOTTOM_CENTER);
            //root.getChildren().get(2).toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(MainView.class.getResource("ReportView.fxml"));
            loader.setController(controlCtr);
            Pane paneReport = new Pane();
            paneReport.setPrefSize(200, 600);
            paneReport = (Pane) loader.load();
            root.getChildren().add(paneReport);
            root.setAlignment(paneReport, Pos.TOP_LEFT);
            //root.getChildren().get(2).toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //PanMap pan = new PanMap(map);
        ZoomMap zoom = new ZoomMap(map);
        zoom.staticZoom(5.0);
        zoom.dynamicZoom();

        return scene;
    }

}

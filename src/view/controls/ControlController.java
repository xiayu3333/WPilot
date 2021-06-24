package view.controls;

import java.util.Hashtable;
import javafx.fxml.FXML;
import flightmanager.*;
import fr.dgac.ivy.IvyException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import testlistview.ItemListView;
import view.BaseMapView;

public class ControlController {

    private Communication communication;
    private FlightInfo flight;

    private Hashtable<String, FlightInfo> aircraft;
    private static ArrayList<String> route;
    private static ObservableList<String> lsWaypoint, lsHeadBy, lsSpeedType;
    private static ListView<String> lvWaypoint;
    private BaseMapView map;

    @FXML
    private ChoiceBox<String> cbHeadBy, cbSpeedType;

    @FXML
    private TextField txFL, txSpeed, txHeading, txRate;

    @FXML
    private TextArea taRecord, taReport;

    @FXML
    private VBox vbox;

    @FXML
    private Button btCancelSpeed, btCancelFL, btCancelHeading;

    private String lastOrder;

    public void setIvy(Communication communication) {
        this.communication = communication;
    }

    public void setFlightInfo(Hashtable<String, FlightInfo> aircraft) {
        this.aircraft = aircraft;
        this.flight = aircraft.get("4996");
        this.route = flight.getRoute();
        //aircraft.get("4996").showMovement();
    }

    public void setBaseMap(BaseMapView map) {
        this.map = map;
    }

    /*----------------------------- ListView -----------------------------*/
    @FXML
    public void setListWaypoint() {
        //System.out.println("Set Flight 4996 route = " + route);
        vbox.getChildren().remove(lvWaypoint);
        lsWaypoint = FXCollections.observableArrayList(route);
        lvWaypoint = new ListView<>(lsWaypoint);
        lvWaypoint.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                ItemListView item = new ItemListView();
                item.setItemList(lsWaypoint); //send flight route inside of cell to modify route when control
                item.setIvy(communication); //send Ivy to cell to send and update order
                return item;
            }
        });       
        
        lvWaypoint.getSelectionModel().selectedIndexProperty()
                .addListener((observable, oldvalue, newValue) -> {
                    Platform.runLater(() -> {
                        //System.out.println("selection " + lvWaypoint.getSelectionModel().getSelectedItem());
                        lvWaypoint.getSelectionModel().clearSelection();
                    });
                });
               
        disableCell();
        
        //lvWaypoint.setBackground(BACKGROUND);
        lvWaypoint.setStyle("-fx-text-fill:LAVENDERBLUSH; "
                + "-fx-control-inner-background: SLATEGRAY"); //#303030
        //lvWaypoint.setBorder(BORDER);
        vbox.getChildren().add(lvWaypoint);
    }
    
    public void disableCell() {
        //System.out.println("Call disable route " + route);
        if(!flight.getWaypoint().equals("")){
            for(String s:this.route){
                //System.out.println("Call disable node s " + s);
                lvWaypoint.getItems().remove(s);
                lsWaypoint.remove(s);
                if(s.equals(flight.getWaypoint())) break;
            }
        }
    }

    /*----------------------------- Flight orders -----------------------------*/
    private void cancelOrder() {
        System.out.println("btCancel send msg Cancel");
        try {
            if (!lastOrder.equals("")) {
                communication.sender("CancelLastOrder Flight=4996");
                recordOrder(flight.getCurrentTime() + " Cancel previous order: " + lastOrder);
                disableButton();
                lastOrder = "";
            }
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    private void speedOrder() {
        System.out.println("btSpeed send msg " + txSpeed.getText());
        try {
            if (!txSpeed.getText().equals("")) {
                communication.sender("AircraftSpeed Flight=4996 Type="
                        + cbSpeedType.getValue() + " Value=" + txSpeed.getText());
                lastOrder = "AircraftSpeed Flight=4996 Type="
                        + cbSpeedType.getValue() + " Value=" + txSpeed.getText();
                clearAll();
            }
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    private void flOrder() {
        try {
            if (!txFL.getText().equals("")) {
                if (txRate.getText().equals("")) {
                    System.out.println("btFL send msg " + txFL.getText());
                    communication.sender("AircraftLevel Flight=4996 Fl=" + txFL.getText());
                    lastOrder = "AircraftLevel Flight=4996 Fl=" + txFL.getText();
                } else {
                    System.out.println("btFL send msg " + txFL.getText() + " by " + txRate.getText());
                    communication.sender("AircraftLevel Flight=4996 Fl=" + txFL.getText() + " Rate=" + txRate.getText());
                    lastOrder = "AircraftLevel Flight=4996 Fl=" + txFL.getText() + " Rate=" + txRate.getText();
                }
                clearAll();
            }
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    private void headingOrder() {
        try {
            if (!txHeading.getText().equals("")) {
                if (cbHeadBy.getValue().equals("None")) {
                    System.out.println("btHeading send msg " + txHeading.getText());
                    communication.sender("AircraftHeading Flight=4996 To=" + txHeading.getText());
                    lastOrder = "AircraftHeading Flight=4996 To=" + txHeading.getText();
                } else {
                    System.out.println("btHeading send msg turn " + txHeading.getText() + " by " + cbHeadBy.getValue());
                    communication.sender("AircraftHeading Flight=4996 To=" + txHeading.getText() + " By=" + cbHeadBy.getValue());
                    lastOrder = "AircraftHeading Flight=4996 To=" + txHeading.getText() + " By=" + cbHeadBy.getValue();
                }
                clearAll();
            }
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    private void resumePLNOrder() {
        try {
            System.out.println("btResume send msg RESUME_PLN");
            communication.sender("AircraftDirect Flight=4996 Beacon=RESUME_PLN");
            //recordOrder(flight.getCurrentTime() + " Send RESUME_PLN order");
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    private TextArea makeReportAPDLCOrder() {
        TextArea report = new TextArea();
        if (flight != null && !flight.getFlightID().equals("")) {
            report.appendText("APDLC REPORT ===================\n");
            report.appendText("Report at " + flight.getCurrentTime() + "\n");
            report.appendText("FID: " + flight.getFlightID() + "\tModel: " + flight.getACType() + "\n");
            report.appendText("Callsign " + flight.getCallSign() + "\n");
            report.appendText("History orders:\n");
            report.appendText(taRecord.getText());
            report.appendText("END REPORT ===================\n");
        } else {
            report.appendText("Have no data yet!\n");
        }
        return report;
    }

    private TextArea makeReportAAROrder() {
        TextArea report = new TextArea();
        if (flight != null && !flight.getFlightID().equals("")) {
            report.appendText("AAR REPORT ===================\n");
            report.appendText("Report at " + flight.getCurrentTime() + "\n");
            report.appendText("FID: " + flight.getFlightID() + "\tModel: " + flight.getACType() + "\n");
            report.appendText("Callsign " + flight.getCallSign() + "\n");
            report.appendText("From: " + flight.getDep() + "\tTo: " + flight.getArr() + "\n");
            report.appendText("FL PLN: " + lsWaypoint + "\n");
            if (!flight.getWaypoint().equals("")) {
                report.appendText("Position: " + flight.getPosition() + " from Waypoint " + flight.getWaypoint() + "\n");
            } else {
                report.appendText("Position: " + flight.getPosition() + "\n");
            }
            report.appendText("Speed: " + flight.getGroundSpeed() + "kns\nAlt: " + flight.getFL() * 100 + "fts\nHeading: "
                    + flight.getHeading() + "\nRate: " + flight.getRate() + "fpm\n");
            report.appendText("END REPORT ===================\n");
        } else {
            report.appendText("Have no data yet!\n");
        }
        return report;
    }    

    /*----------------------------- display handle orders -----------------------------*/
    @FXML
    public void recordOrder(String s) {
        taRecord.appendText(s + "\n");
        taRecord.setWrapText(true);
    }

    @FXML
    public void reportOrder(TextArea report) {
        taReport.appendText(report.getText() + "\n");
        taReport.setWrapText(true);
    }

    @FXML
    private void clearReportOrder() {
        taReport.clear();
    }

    @FXML
    private void clearAll() {
        txFL.clear();
        txSpeed.clear();
        txHeading.clear();
        txRate.clear();
    }

    @FXML
    private void disableButton() {
        btCancelFL.setDisable(true);
        btCancelHeading.setDisable(true);
        btCancelSpeed.setDisable(true);
    }

    /*-------------------------------- Event handler --------------------------------*/
    @FXML
    public void buttonPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btCancelSpeed":
                cancelOrder();
                break;
            case "btCancelFL":
                cancelOrder();
                break;
            case "btCancelHeading":
                cancelOrder();
                break;
            case "btResume":
                resumePLNOrder();
                break;
            case "btSpeed":
                speedOrder();
                disableButton();
                btCancelSpeed.setDisable(false);
                break;
            case "btFL":
                flOrder();
                disableButton();
                btCancelFL.setDisable(false);
                break;
            case "btHeading":
                headingOrder();
                disableButton();
                btCancelHeading.setDisable(false);
                break;
            case "btAPDLC":
                reportOrder(makeReportAPDLCOrder());
                break;
            case "btAAR":
                reportOrder(makeReportAAROrder());
                break;
            case "btClear":
                clearReportOrder();
                break;
        }
    }

    /*-------------------------------------------------------------------------------*/
    public void initialize() {
        flight = new FlightInfo();

        cbHeadBy.setScaleX(1);
        cbHeadBy.setScaleY(0.85);
        cbSpeedType.setScaleX(1);
        cbSpeedType.setScaleY(0.85);

        lsHeadBy = FXCollections.observableArrayList("None", "Left", "Right", "Shortest");
        cbHeadBy.setItems(lsHeadBy);
        cbHeadBy.setValue("None");

        lsSpeedType = FXCollections.observableArrayList("IAS", "MACH", "PERFO");
        cbSpeedType.setItems(lsSpeedType);
        cbSpeedType.setValue("IAS");

        lastOrder = "";

        disableButton();

        clearAll();
    }

    private final static Background BACKGROUND = new Background(
            new BackgroundFill(
                    Color.web("#303030"),
                    new CornerRadii(5),
                    Insets.EMPTY
            )
    );

    private final static Border BORDER = new Border(
            new BorderStroke(
                    Color.GRAY,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(5),
                    new BorderWidths(2)
            )
    );

}

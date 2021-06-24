/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testlistview;

import flightmanager.Communication;
import fr.dgac.ivy.IvyException;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.BeaconView;

/**
 *
 * @author Nhut Minh Ngo
 */
public class ItemListView extends ListCell<String> {

    private ObservableList<String> lsBeacon;
    private Button btDirect, btAddBefore, btAddAfter, btRemove;
    private ChoiceBox<String> cbWaypoint;
    private GridPane pane;
    private final BeaconView beacons = new BeaconView();

    private String lastItem;
    private ObservableList<String> lsWaypoint;
    private Communication comm;

    public void createItem() {
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setPrefSize(240, 110);

        btAddBefore.setText("ADD BEFORE");
        btAddBefore.setPrefSize(74, 20);
        btAddBefore.setStyle("-fx-background-color:LIGHTGREEN");
        btAddBefore.setFont(new Font(10));
        btAddBefore.setOnAction(buttonHandler);

        btAddAfter.setText("ADD AFTER");
        btAddAfter.setPrefSize(74, 20);
        btAddAfter.setStyle("-fx-background-color:LIGHTGREEN");
        btAddAfter.setFont(new Font(10));
        btAddAfter.setOnAction(buttonHandler);

        btRemove.setText("REMOVE");
        btRemove.setPrefSize(74, 20);
        btRemove.setStyle("-fx-background-color:TOMATO");
        btRemove.setFont(new Font(10));
        btRemove.setTextFill(Color.SNOW);
        btRemove.setOnAction(buttonHandler);

        btDirect.setText("DIRECT TO");
        btDirect.setPrefSize(74, 20);
        btDirect.setStyle("-fx-background-color:LIGHTBLUE");
        btDirect.setFont(new Font(10));
        btDirect.setOnAction(buttonHandler);

        cbWaypoint.setPrefSize(85, 30);
        cbWaypoint.setItems(lsBeacon);
        cbWaypoint.setScaleX(0.91);
        cbWaypoint.setScaleY(0.80);

        //cbWaypoint.setStyle("-fx-prompt-text-fill:LAVENDERBLUSH; -fx-text-fill:LAVENDERBLUSH; -fx-background-color:SLATEGRAY; -fx-font-size:10pt");
        cbWaypoint.setStyle("-fx-text-color:LAVENDERBLUSH; -fx-text-fill:LAVENDERBLUSH; -fx-background-color: ALICEBLUE; -fx-font-size:10pt");

        pane.add(cbWaypoint, 0, 1);
        pane.add(btDirect, 1, 1);
        pane.add(btAddBefore, 2, 0);
        pane.add(btRemove, 2, 1);
        pane.add(btAddAfter, 2, 2);

        pane.setStyle("-fx-border-width:3");
        pane.setStyle("-fx-border-color:DIMGRAY");
        pane.setPadding(new Insets(0, 0, 0, 3));
        pane.setHgap(3);
        pane.setVgap(3);
    }

    private void setDefault(String current) {
        cbWaypoint.setValue(current); //item from outside must already had in lsBeacon to set default!!!
    }

    public ItemListView() {
        super();
        pane = new GridPane();
        btAddBefore = new Button();
        btAddAfter = new Button();
        btRemove = new Button();
        btDirect = new Button();
        cbWaypoint = new ChoiceBox<>();
        lsBeacon = FXCollections.observableArrayList(beacons.getBeaconList()); //loading full beacon from database
        createItem();
    }

    public void setItemList(ObservableList<String> lsWaypoint) {
        this.lsWaypoint = lsWaypoint;
        //System.out.println("item lsWaypoint " + lsWaypoint);
    }

    private void directOrder() {
        try {
            comm.sender("AircraftDirect Flight=4996 Beacon=" + cbWaypoint.getValue());
            //communication.sender("GetPln MsgName=Pln54ForMe Flight=4996 From=" + cbWaypoint.getValue());
            //lsWaypoint.remove(0, lsWaypoint.indexOf(getItem()));
            //updatePlnOrder();
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    private void updatePlnOrder() {
        try {
            String order = "SetPln Flight=4996 Time=Now List=";
            for (String w : lsWaypoint) {
                order += w + " ";
                //System.out.println("---Update order list " + order);
            }
            order = order.trim();
            comm.sender(order);
        } catch (IvyException e) {
            System.out.println(e);
        }
    }

    public void setIvy(Communication comm) {
        this.comm = comm;
    }
  
    /*public void disableCell(){
        this.pane.setDisable(true);
    }*/
    
    //private int i=0;
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        //setText(null);  // No text in label of super class
        int i = 0;
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            setGraphic(pane);
            //System.out.println("Item = " + item + " name=" + lsWaypoint.get(i));
            //System.out.println("Index " + lsWaypoint.indexOf(waypoint) + " waypoint " + waypoint);
            setDefault(item);
            i++;
        }
    }

    final EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            //System.out.println("Button clicked");
            Button bt = (Button) event.getSource();
            switch (bt.getText()) {
                case "ADD AFTER":
                    if (lsWaypoint.indexOf(cbWaypoint.getValue()) == -1) {
                        lsWaypoint.add(lsWaypoint.indexOf(lastItem) + 1, cbWaypoint.getValue());
                        updatePlnOrder();
                    }
                    break;
                case "ADD BEFORE":
                    if (lsWaypoint.indexOf(cbWaypoint.getValue()) == -1) {
                        lsWaypoint.add(lsWaypoint.indexOf(lastItem), cbWaypoint.getValue());
                        updatePlnOrder();
                    }
                    break;
                case "REMOVE":
                    lsWaypoint.remove(getItem());
                    //System.out.println("Route " + lsWaypoint.toString());
                    updatePlnOrder();
                    break;
                case "DIRECT TO":
                    System.out.println("Direct");
                    directOrder();
                    break;
            }
        }
    };
}

/*Platform.runLater(new Runnable() {
    @Override
    public void run() {
    }
});*/

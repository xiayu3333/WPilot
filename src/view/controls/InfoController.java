/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import flightmanager.Communication;
import flightmanager.FlightInfo;
import java.util.Hashtable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Nhut Minh Ngo
 */
public class InfoController {
    private FlightInfo flight;
            
    @FXML
    private Label lbFID, lbCallSign, lbTo, lbAlt, lbSpeed, lbRate;
    
    public void setFlightInfo(Hashtable<String, FlightInfo> aircraft) {
        flight = aircraft.get("4996");
    }
    
    @FXML
    public void displayInfo(){
        lbFID.setText(flight.getFlightID());
        lbCallSign.setText(flight.getCallSign());
        lbTo.setText(flight.getTo());
        lbAlt.setText(Integer.toString(flight.getFL()*100) + "ft");
        lbSpeed.setText(Integer.toString(flight.getGroundSpeed()) + "kns");
        lbRate.setText(Integer.toString(flight.getRate()) + "fpm");      
    }
        
    public void initialize() {
    }
}

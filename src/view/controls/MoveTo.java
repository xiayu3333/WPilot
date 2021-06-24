/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import flightmanager.BeaconList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.transform.Affine;

/**
 *
 * @author Chris
 */
public class MoveTo {
    
       public static void MoveToBeacon(Node node, String s) {
            BeaconList dict = new BeaconList(); 
            System.out.println(dict.getBeaconX(s)+ ", "+ dict.getBeaconY(s));
       
            Affine transform = new Affine();
            Point2D p0 = (node.parentToLocal(300,300));
            Point2D p1 = new Point2D(dict.getBeaconX(s),dict.getBeaconY(s));
            transform.appendTranslation(p0.getX()-p1.getX(),p0.getY()-p1.getY());
            node.getTransforms().add(transform);
            
          
        }
            
        public static void MoveToXY(Node node, double x, double y) {      
            Affine transform = new Affine();
            Point2D p0 = (node.parentToLocal(300,300));
            transform.appendTranslation(p0.getX()-x,p0.getY()-y);
            node.getTransforms().add(transform);
    }   
    
}

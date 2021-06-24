package flightmanager;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import data.cartography.IBeacon;
import data.cartography.xanthane.CartographyManagerXanthane;
import view.BeaconView;

public class BeaconList extends BeaconView {

    private Dictionary beaconDict = new Hashtable();

    public BeaconList() {
        CartographyManagerXanthane cartographyManagerXanthane = new CartographyManagerXanthane();
        List<IBeacon> beacons = cartographyManagerXanthane.loadBeacons("data_maps/points.xml");

        for (IBeacon beacon : beacons) {
            String beaconCode = beacon.getCode();
            ArrayList<Double> beaconXY = new ArrayList<Double>();
            beaconXY.add(beacon.getX());
            beaconXY.add(beacon.getY());
            beaconDict.put(beaconCode, beaconXY);
        }
    }

    public double getBeaconX(String s) {
        ArrayList<Double> xy = new ArrayList<Double>();
        xy = (ArrayList<Double>) beaconDict.get(s);
        double x = xy.get(0);
        return x;
    }

    public double getBeaconY(String s) {
        ArrayList<Double> xy = new ArrayList<Double>();
        xy = (ArrayList<Double>) beaconDict.get(s);
        double y = xy.get(1);
        return y;
    }

}

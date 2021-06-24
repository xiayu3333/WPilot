package data.cartography.xanthane;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import data.cartography.IBeacon;

/**
 * JAXB wrapper for "points" XML element in Xanthane file "points.xml"
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "points")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeaconListXanthane {

    @XmlElement(name = "point")
    private ArrayList<BeaconXanthane> beacons;

    /**
     * Get a copy of the beacon list. 
     * This copy will then be filtered to retain only the published beacons 
     * and the lat/lon coordinates will then be converted to screen format.
     */
    public ArrayList<IBeacon> getBeacons() {
        return new ArrayList<IBeacon>(beacons);
    }

    @Override
    public String toString() {
        String res = "BeaconListXanthane(";
        for (BeaconXanthane beacon : beacons) {
            res += beacon + " ";
        }
        res += ")";
        return res;
    }

}

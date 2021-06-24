package data.cartography.xanthane;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import data.cartography.ISector;

/**
 * JAXB wrapper for "sectors" XML element in Xanthane file "sectors.xml".
 * 
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "sectors")
@XmlAccessorType(XmlAccessType.FIELD)
public class SectorListXanthane {

    @XmlElement(name = "sector")
    private ArrayList<SectorXanthane> sectors;

    /**
     * Get a sectors list copy.  
     * This copy will then be filtered according to the desired FL 
     * and the lat/lon coordinates will then be converted to screen format.
     */
    public ArrayList<ISector> getSectors() {
        return new ArrayList<ISector>(sectors);
    }

}

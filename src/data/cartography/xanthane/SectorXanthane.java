package data.cartography.xanthane;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import data.cartography.ISector;
import data.cartography.IZone;

/**
 * JAXB wrapper for "sector" XML element in Xanthane file "sectors.xml"
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "sector")
@XmlAccessorType(XmlAccessType.FIELD)
public class SectorXanthane implements ISector {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "slice")
    private ArrayList<SliceXanthane> slices;

    @XmlAttribute(name = "floor")
    private String floor;

    @XmlAttribute(name = "ceiling")
    private String ceiling;

    @XmlAttribute(name = "acc")
    private String acc;

    @Override
    public String getAcc() {
        return acc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFloor() {
        return floor;
    }

    @Override
    public String getCeiling() {
        return ceiling;
    }

    @Override
    public List<IZone> getSlices() {
        return new ArrayList<IZone>(slices);
    }

    @Override
    public void setSlices(List<IZone> slices) {
        this.slices.clear();
        for (IZone slice : slices) {
            this.slices.add((SliceXanthane) slice);
        }
    }

    @Override
    public String toString() {
        return "SectorXanthane(name:" + name + ", acc:" + acc + ", floor:" + floor + ", ceiling:" + ceiling + ", slices:" + getSlices() + ")";
    }

}

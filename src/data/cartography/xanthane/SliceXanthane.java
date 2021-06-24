package data.cartography.xanthane;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import data.cartography.IPoint;
import data.cartography.IZone;

/**
 * JAXB wrapper for "slice" XML element in Xanthane file "sectors.xml"
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "slice")
@XmlAccessorType(XmlAccessType.FIELD)
public class SliceXanthane implements IZone {

    @XmlElement(name = "centre")
    private CentreXanthane centre;

    @XmlElement(name = "vertex")
    private ArrayList<VertexXanthane> vertexes;

    @XmlAttribute(name = "floor")
    private String floor;

    @XmlAttribute(name = "ceiling")
    private String ceiling;

    @Override
    public CentreXanthane getCentre() {
        return centre;
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
    public ArrayList<IPoint> getVertexes() {
        return new ArrayList<IPoint>(vertexes);
    }

    @Override
    public Double[] getVertexesXYArray() {
        Double[] result = new Double[vertexes.size() * 2];
        int i = 0;
        for (VertexXanthane vertexCourant : vertexes) {
            result[i] = vertexCourant.getX();
            result[++i] = vertexCourant.getY();
            ++i;
        }
        return result;
    }

    @Override
    public String toString() {
        // to complete
        return "SliceXanthane(floor:" + floor + ", ceiling:" + ceiling + ")";
    }

}

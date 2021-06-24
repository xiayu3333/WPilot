package data.cartography.xanthane;

import data.xmladapters.DoublePropertyAdapter;
import javafx.beans.property.DoubleProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import data.cartography.IPoint;

/**
 * JAXB wrapper for "centre" XML element in Xanthane file "sectors.xml"
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "centre")
public class CentreXanthane implements IPoint {

    @XmlAttribute(name = "lat")
    @XmlJavaTypeAdapter(DoublePropertyAdapter.class)
    private DoubleProperty x;

    @XmlAttribute(name = "lon")
    @XmlJavaTypeAdapter(DoublePropertyAdapter.class)
    private DoubleProperty y;

    /**
     * Constructor without parameters (JAXB constraint)
     */
    public CentreXanthane() {
    }

    @Override
    public DoubleProperty xProperty() {
        return x;
    }

    @Override
    public DoubleProperty yProperty() {
        return y;
    }

    @Override
    public Double getX() {
        return x.get();
    }

    @Override
    public Double getY() {
        return y.get();
    }

    @Override
    public void setX(double x) {
        this.x.set(x);
    }

    @Override
    public void setY(double y) {
        this.y.set(y);
    }

    @Override
    public void set(double x, double y) {
        this.x.set(x);
        this.y.set(y);
    }

    @Override
    public void set(IPoint p) {
        x.set(p.getX());
        y.set(p.getY());
    }

    @Override
    public String toString() {
        return "Centre(" + x.get() + ", y= " + y.get() + ")";
    }
}

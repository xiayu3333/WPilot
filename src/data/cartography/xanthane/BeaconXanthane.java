package data.cartography.xanthane;

import data.xmladapters.DoublePropertyAdapter;
import javafx.beans.property.DoubleProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import data.cartography.IBeacon;
import data.cartography.IPoint;

/**
 * JAXB wrapper for "point" XML element in Xanthane file "points.xml"
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "point")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeaconXanthane implements IBeacon {

    @XmlAttribute(name = "code")
    private String code;

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "lat")
    @XmlJavaTypeAdapter(DoublePropertyAdapter.class)
    private DoubleProperty x;

    @XmlAttribute(name = "lon")
    @XmlJavaTypeAdapter(DoublePropertyAdapter.class)
    private DoubleProperty y;

    /**
     * Constructor without parameters (JAXB constraint)
     */
    public BeaconXanthane() {
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getType() {
        return type;
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
        return "BeaconXanthane(code:" + this.getCode() + ", type:" + type
                + ", x:" + this.getX() + ", y:" + this.getY() + ")";
    }

}

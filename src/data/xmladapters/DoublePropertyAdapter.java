package data.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * JAXB Adapter for String <-> DoubleProperty (used for marshalling and unmarshalling).
 *
 * @author Pierre Rondin
 */
public class DoublePropertyAdapter extends XmlAdapter<String, DoubleProperty> {

    private DoubleProperty x;

    /**
     * Convert a String represenation of a double into a JavaFX DoubleProperty
     * 
     * @throws Exception if conversion is impossible
     */
    @Override
    public DoubleProperty unmarshal(String value) throws Exception {
        this.x = new SimpleDoubleProperty();
        this.x.set(Double.parseDouble(value));
        return x;
    }

    /**
     * Convert a JavaFX DoubleProperty into a String representation of a double
     *
     * @throws Exception if conversion is impossible
     */
    @Override
    public String marshal(DoubleProperty value) throws Exception {
        return Double.toString(value.get());
    }

}

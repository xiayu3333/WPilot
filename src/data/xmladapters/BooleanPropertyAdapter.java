package data.xmladapters;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * JAXB Adapter for String <-> BooleanProperty (used for marshalling and unmarshalling).
 *
 * @author Pierre Rondin
 */
public class BooleanPropertyAdapter extends XmlAdapter<String, BooleanProperty> {

    private BooleanProperty s;

    /**
     * Convert a String representation of a boolean to a JavaFX BooleanProperty
     * 
     * @throws Exception if conversion is impossible
     */
    @Override
    public BooleanProperty unmarshal(String value) throws Exception {
        this.s = new SimpleBooleanProperty();
        this.s.setValue(Boolean.valueOf(value));
        return s;
    }

    /**
     * Convert a BooleanProperty into it's String representation
     *
     * @throws Exception if conversion is impossible
     */
    @Override
    public String marshal(BooleanProperty value) throws Exception {
        return Boolean.toString(value.get());
    }

}

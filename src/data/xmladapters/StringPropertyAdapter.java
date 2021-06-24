package data.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * JAXB Adapter for String <-> StringProperty (used for marshalling and unmarshalling).
 *
 * @author Pierre Rondin
 */
public class StringPropertyAdapter extends XmlAdapter<String, StringProperty> {

    private StringProperty s;

    /**
     * Convert a String into a JavaFX StringProperty
     *
     * @throws Exception if conversion is impossible
     */
    @Override
    public StringProperty unmarshal(String value) throws Exception {
        this.s = new SimpleStringProperty();
        this.s.set(value);
        return s;
    }

    /**
     * Convert a JavaFX StringProperty into a String
     *
     * @throws Exception if conversion is impossible
     */
    @Override
    public String marshal(StringProperty value) throws Exception {
        return value.get();
    }

}

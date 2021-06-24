package data.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javafx.scene.paint.Color;

/**
 * JAXB Adapter for String <-> Color (used for marshalling and unmarshalling).
 *
 * @author Pierre Rondin
 */
public class ColorAdapter extends XmlAdapter<String, Color> {

    /**
     * Convert a String representing a color into a JavaFX Color
     *
     * @throws Exception if conversion is impossible
     */
    @Override
    public Color unmarshal(String value) throws Exception {
        return Color.valueOf(value);
    }

    /**
     * Convert a JavaFX Color into it's String representation
     * 
     * @throws Exception if conversion is impossible
     */
    @Override
    public String marshal(Color value) throws Exception {
        return value.toString();
    }

}

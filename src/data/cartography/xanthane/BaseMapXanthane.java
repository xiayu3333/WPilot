package data.cartography.xanthane;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;
import data.cartography.IBaseMap;
import data.cartography.IZone;

/**
 * JAXB wrapper for "map" XML element in Xanthane file "france.xml"
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseMapXanthane implements IBaseMap {

    @XmlElement(name = "outline")
    ArrayList<OutlineXanthane> outlines;

    @Override
    public List<IZone> getZones() {
        return new ArrayList<IZone>(outlines);
    }

}

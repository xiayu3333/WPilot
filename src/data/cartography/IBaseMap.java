package data.cartography;

import java.util.List;

/**
 * Interface specifying the available data on a base map. 
 * Serves as a bridge between the description files (Input/Ouput) and the JavaFX
 * (User interface) parts of the application, allowing the use of the same objects.
 * The current basemap is reduced to a list of zones (outlines) 
 * and therefore does not require an interface but it is possible to enrich it 
 * with rivers, cities...
 *
 * @author Pierre Rondin
 *
 */
public interface IBaseMap {

    /**
     * Get a copy of the zone list
     */
    public List<IZone> getZones();
}

package data.cartography;

import java.util.List;



/**
 * Interface for the cartography managers.
 * Serves as a bridge between the description files (Input/Ouput) and the JavaFX
 * (User interface) parts of the application, allowing the use of the same objects.
 *
 * @author Nicolas Saporito - ENAC
 */
public interface ICartographyManager {

    /**
     * Load beacons.
     *
     * @param file URL of the beacons description file
     * @return a list of the published beacons with screen coordinates
     */
    public List<IBeacon> loadBeacons(String file);

    /**
     * Load sectors.
     *
     * @param file URL of the sectors description file
     * @param fl Flight level to select the sectors and slices to display
     * @return a list of the the sectors filtered for the specified flight level
     * and with screen coordinates
     */
    public List<ISector> loadSectors(String file, int fl);

    /**
     * Load base map.
     *
     * @param file URL of the base map description file
     * @return an instance of IBaseMap describing a base map directly dispayable on the screen
     */
    public IBaseMap loadBaseMap(String file);
}

package data.cartography;

/**
 * Interface specifying the available data for a beacon.
 * Serves as a bridge between the description files (Input/Ouput) and the JavaFX
 * (User interface) parts of the application, allowing the use of the same objects.
 *
 * @author Pierre Rondin, Nicolas Saporito
 *
 */
public interface IBeacon extends IPoint {

    /**
     * Get the beacon code (name)
     */
    String getCode();

    /**
     * Get the beacon type: published or unpublished
     */
    public String getType();

}

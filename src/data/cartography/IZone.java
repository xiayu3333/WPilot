package data.cartography;

import java.util.List;

/**
 * Interface for a convex zone 
 * (for example the slices of a sector or the outlines of a map).
 * Serves as a bridge between the description files (Input/Ouput) and the JavaFX
 * (User interface) parts of the application, allowing the use of the same objects.
 *
 * @author Pierre Rondin
 */
public interface IZone {

    /**
     * Get the center of the zone
     */
    public IPoint getCentre();

    /**
     * Get the floor flight level
     */
    public String getFloor();

    /**
     * Get the ceiling flight level
     */
    public String getCeiling();

    /**
     * Get a copy of the list of the points composing this sector
     */
    public List<IPoint> getVertexes();

    /**
     * Get an array of doubles containing all the points of this zone.
     * Formated as [x0, y0, x1, y1,...]
     */
    public Double[] getVertexesXYArray();

}

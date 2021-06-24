package data.cartography;

import java.util.List;

/**
 * Interface for a Sector.
 * Serves as a bridge between the description files (Input/Ouput) and the JavaFX
 * (User interface) parts of the application, allowing the use of the same objects.
 *
 * @author Pierre Rondin, Nicolas Saporito
 *
 */
public interface ISector {

    /**
     * Get the floor flight level
     */
    public String getFloor();

    /**
     * Get the ceiling flight level
     */
    public String getCeiling();

    /**
     * Get the sectors name
     */
    public String getName();

    /**
     * Get the name of the ATC center in charge of this sector
     */
    public String getAcc();

    /**
     * Get a copy of the list of the slices composing this sector
     */
    public List<IZone> getSlices();

    /**
     * Set the list of the slices composing this sector
     *
     * @param slices slice list (must create a new list, the slices can be kepts as is)
     */
    public void setSlices(List<IZone> slices);

}

package data.cartography;

import javafx.beans.property.DoubleProperty;

/**
 * Interface for a point data model. 
 * Serves as a bridge between the description files (Input/Ouput) and the JavaFX
 * (User interface) parts of the application, allowing the use of the same objects.
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
public interface IPoint {

    /**
     * Get the Property encapsulating x.
     */
    public DoubleProperty xProperty();

    /**
     * Get the Property encapsulating y.
     */
    public DoubleProperty yProperty();

    /**
     * Get x value.
     */
    public Double getX();

    /**
     * Get y value.
     */
    public Double getY();

    /**
     * Set x value.
     */
    public void setX(double x);

    /**
     * Set y value.
     */
    public void setY(double y);

    /**
     * Set new coordinates.
     * @param x
     * @param y 
     */
    public void set(double x, double y);

    /**
     * Set new coordinates.
     * @param p Point whose coordinates are to be copied from
     */
    public void set(IPoint p);

}

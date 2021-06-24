package data.cartography;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Data model for a point
 *
 * @author Benjamin Deslandes, Nicolas Saporito
 */
public class Point implements IPoint {

    private DoubleProperty x;
    private DoubleProperty y;

    /**
     * Create a point from 2 coordinates.
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }

    /**
     * Create a point from another point.
     * @param p point to clone
     */
    public Point(Point p) {
        this(p.getX(), p.getY());
    }

    @Override
    public DoubleProperty xProperty() {
        return x;
    }

    @Override
    public DoubleProperty yProperty() {
        return y;
    }

    @Override
    public Double getX() {
        return x.get();
    }

    @Override
    public Double getY() {
        return y.get();
    }

    @Override
    public void setY(double y) {
        this.y.set(y);
    }

    @Override
    public void setX(double x) {
        this.x.set(x);
    }

    @Override
    public void set(double x, double y) {
        this.x.set(x);
        this.y.set(y);
    }

    @Override
    public void set(IPoint p) {
        x.set(p.getX());
        y.set(p.getY());
    }

    @Override
    public String toString() {
        return "(" + x.get() + ", " + y.get() + ")";
    }
}

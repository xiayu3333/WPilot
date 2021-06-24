package data.cartography.utilities;

import java.util.List;
import data.cartography.IBaseMap;
import data.cartography.IBeacon;
import data.cartography.IPoint;
import data.cartography.ISector;
import data.cartography.IZone;

/**
 * Coordinates conversion from lat/lon to a format directly displayable on screen
 * (Cautra with inverted y axis).
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
public class CoordinatesConversionToScreen {

    private static void latLonToScreen(IPoint p) {
        // Cautra conversion
        IPoint res = CoordinatesConversionToCautra.latLonToCautra(p.getX(), p.getY());
        // invert y axis
        res.setY(-res.getY());
        p.set(res);
    }

    /**
     * Convert the coordinates of all the elements of a beacon list.
     *
     * @param beacons beacon list (the list is modified)
     */
    public static void beaconsLatLonToScreen(List<IBeacon> beacons) {
        for (IBeacon beacon : beacons) {
            latLonToScreen(beacon);
        }
    }

    /**
     * Convert the coordinates of all the elements of a sector list.
     *
     * @param sectors sector list (the list is modified)
     */
    public static void sectorsLatLonToScreen(List<ISector> sectors) {
        for (ISector sector : sectors) {
            for (IZone slice : sector.getSlices()) {
                latLonToScreen(slice.getCentre());
                for (IPoint vertex : slice.getVertexes()) {
                    latLonToScreen(vertex);
                }
            }
        }
    }

    /**
     * Convert all the point of an IBaseMap.
     *
     * @param baseMap implementation of IBaseMap to convert
     */
    public static void baseMapLatLonToScreen(IBaseMap baseMap) {
        for (IZone zone : baseMap.getZones()) {
            for (IPoint vertex : zone.getVertexes()) {
                latLonToScreen(vertex);
            }
        }
    }

}

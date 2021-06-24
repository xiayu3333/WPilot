package data.cartography.utilities;

import data.cartography.IPoint;
import data.cartography.Point;

/**
 * Coordinates conversion from lat/lon to Cautra coordinates system.
 * (ported from a C utility by S. Roux et C. Bousquet, CENA/RAD 30/06/03)
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
public class CoordinatesConversionToCautra {

    // Geometric constants
    private static final double PI_4 = Math.PI / 4.0;
    private static final double DEG_EN_RAD = Math.PI / 180.0;

    // WGS84 ellipsoid characteristics
    private static final double MILLE_N = 1852.0;
    private static final double DEMI_GRAND_AXE = 6378137.0;
    private static final double A_WGS84 = DEMI_GRAND_AXE / MILLE_N;
    private static final double E_WGS84 = 0.0818191908426;
    private static final double E2_WGS84 = E_WGS84 * E_WGS84;

    // Projection characteristics
    private static final double LATC_D = 47.0;                                      // Latitude de la projection Cautra en degres
    private static final double LATC_R = degToRad(LATC_D);                          // Latitude de la projection Cautra en radians
    private static final double SINL = Math.sin(LATC_R);
    private static final double N = A_WGS84 / Math.sqrt(1 - E2_WGS84 * SINL * SINL); // grande normale
    private static final double L0 = lat_conforme(LATC_R);                          // latitude conforme de l'origine en radian
    private static final double R0 = N * Math.cos(LATC_R) / Math.cos(L0);           // rayon de la sphere conforme

    private static double degToRad(double x) {
        return x * DEG_EN_RAD;
    }

    private static double lat_conforme(double lat) {
        double coef = Math.pow((1.0 - E_WGS84 * Math.sin(lat)) / (1.0 + E_WGS84 * Math.sin(lat)), E_WGS84 / 2.0);
        double lc = 2.0 * (Math.atan(coef * Math.tan(PI_4 + lat / 2.0)) - PI_4);
        return lc;
    }

    /**
     * Translate coordinates from lat/lon to Cautra.
     */
    public static IPoint latLonToCautra(double lat, double lon) {
        // latitude and longitude in radians
        double lat_r = degToRad(lat);
        double lon_r = degToRad(lon);

        // conform latitude
        double lc = lat_conforme(lat_r);

        // scale factor
        double kp = 2.0 / (1.0 + Math.sin(L0) * Math.sin(lc) + Math.cos(L0) * Math.cos(lc) * Math.cos(lon_r));

        // stereo coordinates
        double x_res = kp * R0 * Math.cos(lc) * Math.sin(lon_r);
        double y_res = kp * R0 * (Math.cos(L0) * Math.sin(lc) - Math.sin(L0) * Math.cos(lc) * Math.cos(lon_r));
        return new Point(x_res, y_res);
    }

}

package data.cartography;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utilities to filter airspace data
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
public class AirspaceFilters {

    /**
     * Filter the sectors by flight level
     *
     * @param sectors sector list to be filtered
     * @param fl flight level 
     * @return a list containing the sectors having a slice at the specified flight level
     * (returns a new list but composed of the existing sectors in which remain only the selected layer
     * -> switch to a non destructive mechanism in need of an interactive filtering)
     */
    public static List<ISector> getSectorsFilteredByFL(List<ISector> sectors, int fl) {
        // Copy the sector list
        List<ISector> result = new ArrayList<>(sectors);
        Iterator<ISector> i = result.iterator();
        while (i.hasNext()) {
            // Read through all the sectors
            ISector sector = i.next();
            int floor = Integer.parseInt(sector.getFloor());
            int ceiling = Integer.parseInt(sector.getCeiling());
            if (!(floor <= fl && ceiling >= fl)) {
                // If the sector doesn't contain a slice at the specified level, remove it from the list
                i.remove();
            } else {
                // Copy the slice list
                List<IZone> slices = sector.getSlices();
                Iterator<IZone> j = slices.iterator();
                while (j.hasNext()) {
                    // read through all the slices
                    IZone slice = j.next();
                    floor = Integer.parseInt(slice.getFloor());
                    ceiling = Integer.parseInt(slice.getCeiling());
                    if (!(floor <= fl && ceiling >= fl)) {
                        // If the slice doesn't contain the specified flight level, remove it
                        j.remove();
                    }
                }
                sector.setSlices(slices);
            }
        }
        return result;
    }

    /**
     * Filter the published beacons
     *
     * @param beacons beacon list to be filtered
     * @return the filtered list
     * (returns a new list but composed of the existing sectors in which remain only the selected layer
     * -> switch to a non destructive mechanism in need of an interactive filtering)
     */
    public static List<IBeacon> getPublishedBeacons(List<IBeacon> beacons) {
        List<IBeacon> result = new ArrayList<>();
        for (IBeacon beacon : beacons) {
            if (beacon.getType().equals("published")) {
                result.add(beacon);
            }
        }
        return result;
    }

}

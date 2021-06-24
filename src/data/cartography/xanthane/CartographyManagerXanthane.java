package data.cartography.xanthane;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import data.cartography.AirspaceFilters;
import data.cartography.utilities.CoordinatesConversionToScreen;
import data.cartography.IBaseMap;
import data.cartography.IBeacon;
import data.cartography.ICartographyManager;
import data.cartography.ISector;

/**
 * Xanthane format cartography manager to load the map and airspace description.
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
public class CartographyManagerXanthane implements ICartographyManager {

    @Override
    public List<IBeacon> loadBeacons(String fileName) {
        try {
            // Load beacons description
            JAXBContext jaxbContextBalise = JAXBContext.newInstance(BeaconListXanthane.class);
            Unmarshaller jaxbUnmarshaller = jaxbContextBalise.createUnmarshaller();
            BeaconListXanthane balisesXanthane = (BeaconListXanthane) jaxbUnmarshaller.unmarshal(new File(fileName));
            // Filter to keep only published beacons
            List<IBeacon> result = balisesXanthane.getBeacons();
            result = AirspaceFilters.getPublishedBeacons(result);
            // Convert coordinates to screen coordinate system
            CoordinatesConversionToScreen.beaconsLatLonToScreen(result);
            return result;
        } catch (JAXBException je) {
            throw new ExceptionInInitializerError("Impossible to load beacons");
        }
    }

    @Override
    public List<ISector> loadSectors(String fileName, int fl) {
        try {
            // Load sectors description
            JAXBContext jaxbContextSecteurs = JAXBContext.newInstance(SectorListXanthane.class);
            Unmarshaller jaxbUnmarshaller = jaxbContextSecteurs.createUnmarshaller();
            SectorListXanthane sectorListXanthane = (SectorListXanthane) jaxbUnmarshaller.unmarshal(new File(fileName));
            // Filter to keep only the desired flight level
            List<ISector> result = sectorListXanthane.getSectors();
            result = AirspaceFilters.getSectorsFilteredByFL(result, fl);
            // Convert coordinates to screen coordinate system
            CoordinatesConversionToScreen.sectorsLatLonToScreen(result);
            return result;
        } catch (JAXBException je) {
            throw new ExceptionInInitializerError("Impossible to load sectors");
        }
    }

    @Override
    public IBaseMap loadBaseMap(String fileName) {
        try {
            // Load base map description
            JAXBContext jaxbContextFondCarte = JAXBContext.newInstance(BaseMapXanthane.class);
            Unmarshaller jaxbUnmarshaller = jaxbContextFondCarte.createUnmarshaller();
            BaseMapXanthane map = (BaseMapXanthane) jaxbUnmarshaller.unmarshal(new File(fileName));
            // Convert coordinates to screen coordinate system
            CoordinatesConversionToScreen.baseMapLatLonToScreen(map);
            return map;
        } catch (JAXBException je) {
            throw new ExceptionInInitializerError("Impossible to load base map");
        }
    }

}

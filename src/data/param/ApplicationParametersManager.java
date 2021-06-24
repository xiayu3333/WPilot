package data.param;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Manager to load/save the application's visual parameters (in file "param.xml")
 *
 * @author Nicolas Saporito - ENAC
 */
public class ApplicationParametersManager {

    /**
     * Load the last saved parameters.
     */
    public static ApplicationParameters load() {
        JAXBContext jaxbContextSecteurs;
        Unmarshaller jaxbUnmarshaller;
        try {
            jaxbContextSecteurs = JAXBContext.newInstance(ApplicationParameters.class);
            jaxbUnmarshaller = jaxbContextSecteurs.createUnmarshaller();
            return (ApplicationParameters) jaxbUnmarshaller.unmarshal(new File("param.xml"));
        } catch (JAXBException ex) {
            throw new ExceptionInInitializerError("Impossible to load parameters");
        }
    }

    /**
     * Save the parameters.
     *
     * @throws JAXBException
     */
    public static void save(ApplicationParameters param) throws JAXBException {
        JAXBContext jaxbContextSecteurs = JAXBContext.newInstance(ApplicationParameters.class);
        Marshaller jaxbMarshaller = jaxbContextSecteurs.createMarshaller();
        jaxbMarshaller.setProperty("jaxb.formatted.output", true); // human readable indented xml data
        jaxbMarshaller.marshal(param, new File("param.xml"));
    }

}

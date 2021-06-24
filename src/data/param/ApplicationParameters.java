package data.param;

import data.xmladapters.ColorAdapter;
import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * JAXB wrapper for the content of "param.xml" file. 
 * This class groups together all the visual parameters of the application 
 * (colors used, line thickness...). 
 * Some parameters can also be modified by the user. 
 * The whole set is thus saved when the application is quit. 
 * JavaFX naming conventions are not respected in this class because of a conflict 
 * with JAXB conventions for marshalling/unmarshalling.
 *
 * @author Pierre Rondin, Nicolas Saporito
 */
@XmlRootElement(name = "param")
public class ApplicationParameters {

    private boolean beaconsAreDisplayed;
    private int baseMapStrokeWidth,
            beaconSize, beaconStrokeWidth, beaconTextOffsetX, beaconTextOffsetY,
            cometSize, cometSizeReduction,
            cometStrokeWidth, flightLevel,
            labelOffsetX, labelOffsetY, labelStrokeWidth,
            menuBarSize,
            sectorStrokeWidth,
            speedVectorStrokeWidth,
            timeHorizon;
    private String fileBaseMap, fileBeacons, fileSectors,
            ivyBus;
    private Color baseMapFillColor, baseMapStrokeColor,
            beaconFillColor, beaconStrokeColor, beaconTextFillColor,
            cometStrokeColor, cometFillColor,
            labelArrowStrokeColor, labelFillColor,
            sceneBackgroundColor,
            sectorStrokeColor, sectorFillColor, sectorTextFillColor,
            speedVectorColor;

    /**
     * Get background color for the base map
     */
    public Color getBaseMapFillColor() {
        return baseMapFillColor;
    }

    /**
     * Set background color for the base map (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setBaseMapFillColor(Color baseMapFillColor) {
        this.baseMapFillColor = baseMapFillColor;
    }

    /**
     * Get stroke color for the base map
     */
    public Color getBaseMapStrokeColor() {
        return baseMapStrokeColor;
    }

    /**
     * Set stroke color for the base map 
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setBaseMapStrokeColor(Color baseMapStrokeColor) {
        this.baseMapStrokeColor = baseMapStrokeColor;
    }

    /**
     * Get stroke width for the base map (pixels)
     */
    public int getBaseMapStrokeWidth() {
        return baseMapStrokeWidth;
    }

    /**
     * Set stroke width for the base map (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setBaseMapStrokeWidth(int baseMapStrokeWidth) {
        this.baseMapStrokeWidth = baseMapStrokeWidth;
    }

    /**
     * Get the x-offset of the text with respect to the beacon symbols (pixels)
     */
    public int getBeaconTextOffsetX() {
        return beaconTextOffsetX;
    }

    /**
     * Set the x-offset of the text with respect to the beacon symbols (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setBeaconTextOffsetX(int beaconTextOffsetX) {
        this.beaconTextOffsetX = beaconTextOffsetX;
    }

    /**
     * Get the y-offset of the text with respect to the beacon symbols (pixels)
     */
    public int getBeaconTextOffsetY() {
        return beaconTextOffsetY;
    }

    /**
     * Set the y-offset of the text with respect to the beacon symbols (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setBeaconTextOffsetY(int beaconTextOffsetY) {
        this.beaconTextOffsetY = beaconTextOffsetY;
    }

    /**
     * Get the color for the beacon's text
     */
    public Color getBeaconTextFillColor() {
        return beaconTextFillColor;
    }

    /**
     * Set the color for the beacon's text 
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setBeaconTextFillColor(Color beaconTextFillColor) {
        this.beaconTextFillColor = beaconTextFillColor;
    }

    /**
     * Get the color for the beacon's fill
     */
    public Color getBeaconFillColor() {
        return beaconFillColor;
    }

    /**
     * Set the color for the beacon's fill
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setBeaconFillColor(Color beaconFillColor) {
        this.beaconFillColor = beaconFillColor;
    }

    /**
     * Get the color for the beacon's stroke
     */
    public Color getBeaconStrokeColor() {
        return beaconStrokeColor;
    }

    /**
     * Set the color for the beacon's stroke
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setBeaconStrokeColor(Color beaconStrokeColor) {
        this.beaconStrokeColor = beaconStrokeColor;
    }

    /**
     * Get the thickness for beacon's stroke (pixels)
     */
    public int getBeaconStrokeWidth() {
        return beaconStrokeWidth;
    }

    /**
     * Set the thickness for beacon's stroke (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setBeaconStrokeWidth(int beaconStrokeWidth) {
        this.beaconStrokeWidth = beaconStrokeWidth;
    }

    /**
     * Get the beacon's symbol size (pixels)
     */
    public int getBeaconSize() {
        return beaconSize;
    }

    /**
     * Set the beacon's symbol size (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setBeaconSize(int beaconSize) {
        this.beaconSize = beaconSize;
    }

    /**
     * Check if the beacons are displayed/hidden
     *
     * @return true if beacons are displayed
     */
    public boolean getBeaconsAreDisplayed() {
        return beaconsAreDisplayed;
    }

    /**
     * Display/hide the beacons 
     * (used by JAXB during unmarshalling, potentially modified by the user)
     *
     * @param beaconsAreDisplayed true si les balises doivent être affichées
     */
    @XmlElement
    public void setBeaconsAreDisplayed(boolean beaconsAreDisplayed) {
        this.beaconsAreDisplayed = beaconsAreDisplayed;
    }

    /**
     * Get the size of the biggest comet symbol (pixels)
     */
    public int getCometSize() {
        return cometSize;
    }

    /**
     * Set the size of the biggest comet symbol (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setCometSize(int cometSize) {
        this.cometSize = cometSize;
    }

    /**
     * Get the size reduction between 2 comet symbols (pixels)
     */
    public int getCometSizeReduction() {
        return cometSizeReduction;
    }

    /**
     * Set the size reduction between 2 comet symbols (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setCometSizeReduction(int cometSizeReduction) {
        this.cometSizeReduction = cometSizeReduction;
    }

    /**
     * Get the thickness of comet's symbols stroke (pixels)
     */
    public int getCometStrokeWidth() {
        return cometStrokeWidth;
    }

    /**
     * Set the thickness of comet's symbols stroke (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setCometStrokeWidth(int cometStrokeWidth) {
        this.cometStrokeWidth = cometStrokeWidth;
    }

    /**
     * Get the color of comet's stroke
     */
    public Color getCometStrokeColor() {
        return cometStrokeColor;
    }

    /**
     * Set the color of comet's stroke
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setCometStrokeColor(Color cometStrokeColor) {
        this.cometStrokeColor = cometStrokeColor;
    }

    /**
     * Get the color of comet's fill
     */
    public Color getCometFillColor() {
        return cometFillColor;
    }

    /**
     * Set the color of comet's fill
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setCometFillColor(Color cometFillColor) {
        this.cometFillColor = cometFillColor;
    }

    /**
     * Get the base map file URL
     */
    public String getFileBaseMap() {
        return fileBaseMap;
    }

    /**
     * Set the base map file URL
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setFileBaseMap(String fileBaseMap) {
        this.fileBaseMap = fileBaseMap;
    }

    /**
     * Get the beacons file URL
     */
    public String getFileBeacons() {
        return fileBeacons;
    }

    /**
     * Set the beacons file URL
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setFileBeacons(String fileBeacons) {
        this.fileBeacons = fileBeacons;
    }

    /**
     * Get the sectors file URL
     */
    public String getFileSectors() {
        return fileSectors;
    }

    /**
     * Set the sectors file URL
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setFileSectors(String fileSectors) {
        this.fileSectors = fileSectors;
    }

    /**
     * Get the flight level used to filter sectors
     */
    public int getFlightLevel() {
        return flightLevel;
    }

    /**
     * Set the flight level used to filter sectors
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setFlightLevel(int flightLevel) {
        this.flightLevel = flightLevel;
    }

    /**
     * Get the Ivy bus domain to use
     */
    public String getIvyBus() {
        return ivyBus;
    }

    /**
     * Set the used Ivy bus domain
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setIvyBus(String ivyBus) {
        this.ivyBus = ivyBus;
    }

    /**
     * Get the x-offset of the labels with respect to the comet head symbol (pixels)
     */
    public int getLabelOffsetX() {
        return labelOffsetX;
    }

    /**
     * Set the x-offset of the labels with respect to the comet head symbol (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setLabelOffsetX(int labelOffsetX) {
        this.labelOffsetX = labelOffsetX;
    }

    /**
     * Get the y-offset of the labels with respect to the comet head symbol (pixels)
     */
    public int getLabelOffsetY() {
        return labelOffsetY;
    }

    /**
     * Set the y-offset of the labels with respect to the comet head symbol (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setLabelOffsetY(int labelOffsetY) {
        this.labelOffsetY = labelOffsetY;
    }

    /**
     * Get the fill color for the labels
     */
    public Color getLabelFillColor() {
        return labelFillColor;
    }

    /**
     * Set the fill color for the labels
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setLabelFillColor(Color labelFillColor) {
        this.labelFillColor = labelFillColor;
    }

    /**
     * Get the color of the arrow between the labels and the comet head symbol
     */
    public Color getLabelArrowStrokeColor() {
        return labelArrowStrokeColor;
    }

    /**
     * Set the color of the arrow between the labels and the comet head symbol
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setLabelArrowStrokeColor(Color labelArrowStrokeColor) {
        this.labelArrowStrokeColor = labelArrowStrokeColor;
    }

    /**
     * Get the thickness of label's stroke (pixels)
     */
    public int getLabelStrokeWidth() {
        return labelStrokeWidth;
    }

    /**
     * Set the thickness of label's stroke (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setLabelStrokeWidth(int labelStrokeWidth) {
        this.labelStrokeWidth = labelStrokeWidth;
    }

    /**
     * Get the size of the menu bar (pixels)
     */
    public int getMenuBarSize() {
        return menuBarSize;
    }

    /**
     * Set the size of the menu bar (pixels)
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setMenuBarSize(int menuBarSize) {
        this.menuBarSize = menuBarSize;
    }

    /**
     * Get the background color for the application's scene
     */
    public Color getSceneBackgroundColor() {
        return sceneBackgroundColor;
    }

    /**
     * Set the background color for the application's scene
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setSceneBackgroundColor(Color sceneBackgroundColor) {
        this.sceneBackgroundColor = sceneBackgroundColor;
    }

    /**
     * Get the background color for sectors
     */
    public Color getSectorFillColor() {
        return sectorFillColor;
    }

    /**
     * Set the background color for sectors
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setSectorFillColor(Color sectorFillColor) {
        this.sectorFillColor = sectorFillColor;
    }

    /**
     * Get the stroke color for sectors
     */
    public Color getSectorStrokeColor() {
        return sectorStrokeColor;
    }

    /**
     * Set the stroke color for sectors
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setSectorStrokeColor(Color sectorStrokeColor) {
        this.sectorStrokeColor = sectorStrokeColor;
    }

    /**
     * Get the thickness of sector's stroke
     */
    public int getSectorStrokeWidth() {
        return sectorStrokeWidth;
    }

    /**
     * Set the thickness of sector's stroke
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    public void setSectorStrokeWidth(int sectorStrokeWidth) {
        this.sectorStrokeWidth = sectorStrokeWidth;
    }

    /**
     * Get the color for sector's name
     */
    public Color getSectorTextFillColor() {
        return sectorTextFillColor;
    }

    /**
     * Set the color for sector's name
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setSectorTextFillColor(Color sectorTextFillColor) {
        this.sectorTextFillColor = sectorTextFillColor;
    }

    /**
     * Get the color for speed vectors
     */
    public Color getSpeedVectorColor() {
        return speedVectorColor;
    }

    /**
     * Set the color for speed vectors
     * (used by JAXB during unmarshalling)
     */
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    public void setSpeedVectorColor(Color speedVectorColor) {
        this.speedVectorColor = speedVectorColor;
    }

    /**
     * Get the thickness of speed vectors (pixels)
     */
    public int getSpeedVectorStrokeWidth() {
        return this.speedVectorStrokeWidth;
    }

    /**
     * Set the thickness of speed vectors (pixels)
     * (used by JAXB during unmarshalling)
     * 
     */
    @XmlElement
    public void setSpeedVectorStrokeWidth(int speedVectorStrokeWidth) {
        this.speedVectorStrokeWidth = speedVectorStrokeWidth;
    }

    /**
     * Get time horizon for speed vectors (minutes)
     */
    public int getTimeHorizon() {
        return timeHorizon;
    }

    /**
     * Set time horizon for speed vectors (minutes)
     * (used by JAXB during unmarshalling, potentially modified by the user)
     */
    @XmlElement
    public void setTimeHorizon(int timeHorizon) {
        this.timeHorizon = timeHorizon;
    }

}

package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 * The class LagerstatusConverter is a converter class 
 * to convert String values to Lagerstatus class objects
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@FacesConverter(value = "lagerstatusConverter", forClass = Lagerstatus.class)
public class LagerstatusConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(LagerstatusConverter.class.getName());
    
    /**
     * Converts a String value into a Object of the Lagerstatus class.
     * For the conversion the Lagerstatus id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param value the Value which should be converted
     * @return the Lagerstatus class Objekt in which the String converts
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0)
            return null;
        
        Lagerstatus lagerStatus = null;
        try {
            Produkte produkte = (Produkte) facesContext.getApplication()
                    .getELResolver()
                    .getValue(facesContext.getELContext(), null, "produkte");
            lagerStatus = produkte.findLagerstatusMitId(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            LOG.info("[ERROR:LagerStatusConverter] " + e.getMessage());
        }
        return lagerStatus;
    }

    /**
     * Converts a Object of the Lagerstatus class into a String value.
     * For the conversion the Lagerstatus id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param object the Object which should be converted
     * @return the String value in which the Lagerstatus class converts
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || !(object instanceof Lagerstatus)) {
            return null;
        }
        Lagerstatus lagerStatus = (Lagerstatus) object;
        return Integer.toString(lagerStatus.getId());
    }
    
}
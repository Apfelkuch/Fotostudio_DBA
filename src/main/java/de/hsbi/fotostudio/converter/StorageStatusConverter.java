package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 * The class StorageStatusConverter is a converter class 
 to convert String values to StorageStatus class objects
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@FacesConverter(value = "storageStatusConverter", forClass = StorageStatus.class)
public class StorageStatusConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(StorageStatusConverter.class.getName());
    
    /**
     * Converts a String value into a Object of the StorageStatus class.
     * For the conversion the StorageStatus id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param value the Value which should be converted
     * @return the StorageStatus class Objekt in which the String converts
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0)
            return null;
        
        StorageStatus storageStatus = null;
        try {
            Products produkte = (Products) facesContext.getApplication()
                    .getELResolver()
                    .getValue(facesContext.getELContext(), null, "products");
            storageStatus = produkte.findStorageStatusWithId(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            LOG.info("[ERROR:StorageStatusConverter] " + e.getMessage());
        }
        return storageStatus;
    }

    /**
     * Converts a Object of the StorageStatus class into a String value.
     * For the conversion the StorageStatus id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param object the Object which should be converted
     * @return the String value in which the StorageStatus class converts
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || !(object instanceof StorageStatus)) {
            return null;
        }
        StorageStatus storageStatus = (StorageStatus) object;
        return Integer.toString(storageStatus.getId());
    }
    
}
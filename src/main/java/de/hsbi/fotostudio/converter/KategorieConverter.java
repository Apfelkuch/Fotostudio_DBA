package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 * The class KategorieConverter is a converter class 
 * to convert String values to Kategorie class objects
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@FacesConverter(value = "kategorieConverter", forClass = Kategorie.class)
public class KategorieConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(KategorieConverter.class.getName());
    
    /**
     * Converts a String value into a Object of the Kategorie class.
     * For the conversion the Kategorie id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param value the Value which should be converted
     * @return the Kategorie class Objekt in which the String converts
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0)
            return null;
        
        Kategorie kategorie = null;
        try {
            Produkte produkte = (Produkte) facesContext.getApplication()
                    .getELResolver()
                    .getValue(facesContext.getELContext(), null, "produkte");
            kategorie = produkte.findKategorieMitId(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            LOG.info("[ERROR:KategorieConverter] " + e.getMessage());
        }
        return kategorie;
    }

    /**
     * Converts a Object of the Kategorie class into a String value.
     * For the conversion the Kategorie id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param object the Object which should be converted
     * @return the String value in which the Kategorie class converts
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || !(object instanceof Kategorie)) {
            return null;
        }
        Kategorie kategorie = (Kategorie) object;
        return Integer.toString(kategorie.getId());
    }
    
}
package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 *
 * @author Janis Wiegräbe
 */
@FacesConverter(value = "kategorieConverter", forClass = Kategorie.class)
public class KategorieConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(KategorieConverter.class.getName());
    
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

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || !(object instanceof Kategorie)) {
            return null;
        }
        Kategorie kategorie = (Kategorie) object;
        return Integer.toString(kategorie.getId());
    }
    
}
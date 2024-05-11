package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 *
 * @author Apfel
 */
@FacesConverter(value = "lagerstatusConverter", forClass = Lagerstatus.class)
public class LagerstatusConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(LagerstatusConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0)
            return null;
        
        Lagerstatus lagerStatus = null;
        try {
            Produkte produkte = (Produkte) facesContext.getApplication()
                    .getELResolver()
                    .getValue(facesContext.getELContext(), null, "produkte");
            lagerStatus = produkte.findLagerStatusMitId(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            LOG.info("[ERROR:LagerStatusConverter] " + e.getMessage());
        }
        return lagerStatus;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || !(object instanceof Lagerstatus)) {
            return null;
        }
        Lagerstatus lagerStatus = (Lagerstatus) object;
        return Integer.toString(lagerStatus.getId());
    }
    
}
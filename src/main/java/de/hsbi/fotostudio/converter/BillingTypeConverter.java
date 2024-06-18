package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.BillingType;
import de.hsbi.fotostudio.modul.Category;
import de.hsbi.fotostudio.modul.Products;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 * The class BillingTypeConverter is a converter class 
 to convert String values to BillingType class objects
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@FacesConverter(value = "billingTypeConverter", forClass = BillingType.class)
public class BillingTypeConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(BillingTypeConverter.class.getName());
    
    /**
     * Converts a String value into a Object of the BillingType class.
     * For the conversion the Category id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param value the Value which should be converted
     * @return the BillingType class Objekt in which the String converts
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0)
            return null;
        
        BillingType billingType = null;
        try {
            Products produkte = (Products) facesContext.getApplication()
                    .getELResolver()
                    .getValue(facesContext.getELContext(), null, "products");
            billingType = produkte.findBillingTypeWithId(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            LOG.info("[ERROR:CategoryConverter] " + e.getMessage());
        }
        return billingType;
    }

    /**
     * Converts a Object of the Category class into a String value.
     * For the conversion the Category id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param object the Object which should be converted
     * @return the String value in which the Category class converts
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || !(object instanceof BillingType)) {
            return null;
        }
        BillingType billingType = (BillingType) object;
        return Integer.toString(billingType.getId());
    }
    
}

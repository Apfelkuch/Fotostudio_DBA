package de.hsbi.fotostudio.converter;

import de.hsbi.fotostudio.modul.*;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.logging.Logger;

/**
 * The class ProductCategoryConverter is a converter class 
 to convert String values to Category class objects
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@FacesConverter(value = "productCategoryConverter", forClass = Category.class)
public class ProductCategoryConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(ProductCategoryConverter.class.getName());
    
    /**
     * Converts a String value into a Object of the Category class.
     * For the conversion the Category id is used.
     * 
     * @param facesContext the context in which the Methode is used
     * @param component the component on which the converter is used
     * @param value the Value which should be converted
     * @return the Category class Objekt in which the String converts
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0)
            return null;
        
        Category category = null;
        try {
            Products produkte = (Products) facesContext.getApplication()
                    .getELResolver()
                    .getValue(facesContext.getELContext(), null, "products");
            category = produkte.findProductCategoryWithId(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            LOG.info("[ERROR:CategoryConverter] " + e.getMessage());
        }
        return category;
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
        if (object == null || !(object instanceof Category)) {
            return null;
        }
        Category category = (Category) object;
        return Integer.toString(category.getId());
    }
    
}
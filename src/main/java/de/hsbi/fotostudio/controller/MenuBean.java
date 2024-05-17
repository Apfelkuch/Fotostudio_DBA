package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Products;
import de.hsbi.fotostudio.util.ProductData;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

/**
 * The class MenuBean is the Backing-Bean for the menu.xhtml page.
 * This class links the xhtml page and the underlying logic.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "menuBean")
@ViewScoped
public class MenuBean implements Serializable{

    @Inject
    private Products products;
    
    @Inject
    private ProductData productData;
    
    private static final Logger LOG = Logger.getLogger(MenuBean.class.getName());
    
    /** 
     * Creates new instance of MenuBean
     */
    public MenuBean() {
    }
    
    /**
     * Methode to change Category to the Category with the given
     * categoryId, after that the ProduktView is updated.
     * 
     * @param categoryId 
     */
    public void changeCategory(int categoryId) {
        products.selectCategory(categoryId);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }

}

package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Products;
import de.hsbi.fotostudio.modul.Services;
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
    private Services services;
    
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
     * @param categoryId the id of the new Category
     * @return the path to the product view
     */
    public String changeProductCategory(int categoryId) {
        products.selectProductCategory(categoryId);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
        return productView();
    }
        
    /**
     * Methode to change Category to the Category with the given
     * categoryId, after that the ServiceView is updated.
     * 
     * @param categoryId the id of the new Category
     * @return the path to the service view
     */
    public String changeServiceCategory(int categoryId) {
        services.selectServiceCategory(categoryId);
        PrimeFaces.current().ajax().update("form-service-view:data-view");
        return serviceView();
    }
    
    /**
     * Methode that returns the path to the ProductView xhtml webpage
     * 
     * @return the path to the ProductView xhtml webpage
     */
    public String productView() {
        return "ProductView";
    }
    
    /**
     * Methode that returns the path to the ServiceView xhtml webpage
     * 
     * @return the path to the ServiceView xhtml webpage
     */
    public String serviceView() {
        return "ServiceView";
    }
}

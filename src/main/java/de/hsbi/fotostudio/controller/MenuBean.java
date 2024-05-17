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
     * Methode to change Category to "Alles" and update the ProduktView.
     */
    public void changeCategoryToAlles() {
        products.selectCategory(productData.getCategory_list().get(0).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    /**
     * Methode to change Category to "Innerhaus" and update the ProduktView.
     */
    public void changeCategoryToInnerhaus() {
        products.selectCategory(productData.getCategory_list().get(1).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    /**
     * Methode to change Category to "Ausserhaus" and update the ProduktView.
     */
    public void changeCategoryToAusserhaus() {
        products.selectCategory(productData.getCategory_list().get(2).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    /**
     * Methode to change Category to "Equipment" and update the ProduktView.
     */
    public void changeCategoryToEquipment() {
        products.selectCategory(productData.getCategory_list().get(3).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }

}

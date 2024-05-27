package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Basket;
import de.hsbi.fotostudio.modul.BasketItem;
import de.hsbi.fotostudio.modul.Item;
import de.hsbi.fotostudio.modul.Product;
import de.hsbi.fotostudio.modul.Products;
import de.hsbi.fotostudio.modul.Service;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

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
    private Basket basket;
    
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
//        PrimeFaces.current().ajax().update("form-product-view:data-view");
        return "ProductView?faces-redirect=true";
    }
        
    /**
     * Methode to change Category to the Category with the given
     * categoryId, after that the ServiceView is updated.
     * 
     * @param categoryId the id of the new Category
     * @return the path to the service view
     */
    public String changeServiceCategory(int categoryId) {
        products.selectServiceCategory(categoryId);
//        PrimeFaces.current().ajax().update("form-service-view:data-view");
        return "ServiceView?faces-redirect=true";
    }
    
    /**
     * Checks if a user is logged in
     * 
     * @return true if user is logged in, otherwise false
     */
    public boolean isLoggedIn() {
        return true;
    }
    
    /**
     * This Methode does the checkout and
     * returns the path to go to the checkout view (contract)
     * 
     * @return the path to the contract view
     */
    public String checkout() {
        LOG.info("[MenuBean] checkout of basket");
        showMassage(new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Bestellung ist eingegangen",
                "Bestellung bearbeitet, gesamtpreis " + getBasketPrice() + "€"
        ));
        for (BasketItem basketItem : basket.getBasket()) {
            Item item = basketItem.getItem();
            if (item instanceof Service) { // edit service on checkout
            } else if (item instanceof Product) { // edit product on checkout
                Product product = (Product) item;
                product.setAmount(product.getAmount() - basketItem.getCount());
                if(!products.updateProduct(product.getId(), product)) {
                    LOG.info("[MenuBean] product could not be updated");
                }
            } else {
                LOG.info("[MenuBean] invalid item in basket");
            }
        }
        basket.clearBasket();
        
        return "contract?faces-redirect=true";
    }
    
    public float getBasketPrice() {
        return basket.getTotalPrice();
    }
    
    /**
     * This Methode adds a growl with a given message
     * 
     * @param message the message which is displayed
     */
    private void showMassage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    // GETTER && SETTER
}

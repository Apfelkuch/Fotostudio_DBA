package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Basket;
import de.hsbi.fotostudio.modul.Product;
import de.hsbi.fotostudio.modul.Products;
import de.hsbi.fotostudio.util.Util;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

/**
 * The class ProduktViewBean is the Backing-Bean for the ProduktView.xhtml page.
 * This class links the xhtml page and the underlying logic.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "productViewBean")
@ViewScoped
public class ProductViewBean implements Serializable{
    
    @Inject
    private Products products;
    
    @Inject
    private Basket basket;
    
    private Product currentProduct;
    
    private static final Logger LOG = Logger.getLogger(ProductViewBean.class.getName());
    
    /**
     * Creates instance of ProductViewBean
     */
    ProductViewBean() {
    }
    
    /**
     * Methode to clear the Multiviewstate.
     * The Multiviewstate is used hold the same state of the page if the page is reloaded.
     */
    public void clearMultiViewState() {
        LOG.info("clearMultiViewState");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showViewStateMessage);
    }

    /**
     * This Methode adds a growl to visualize that the Multiviewstate has been cleared.
     * 
     * @param id the id of the user, where the multistate View has been cleared.
     */
    private void showViewStateMessage(String id) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, id 
                                + "multiview state has been cleared out", null));
    }
    
    /**
     * Methode is called to show the ProductDialog of a Product.
     * 
     * @param product new Product which is shown in the ProductDialog.
     */
    public void info(Product product) {
        LOG.info("[ProductViewBean] info: " + product.getName());
        products.setAddNewItem(false);
        products.setCurrentProduct(product);
        PrimeFaces.current().ajax().update(":form-product-dialog");
    }
    
    /**
     * Methode to create a new Product
     */
    public void createProduct() {
        LOG.info("[ProductViewBean] add Product");
        products.setAddNewItem(true);
        products.setCurrentProduct(new Product());
        PrimeFaces.current().ajax().update(":form-product-dialog");
        LOG.info("[ProductViewBean] add Product : "
                + products.getCurrentProduct().toString());
    }
    
    /**
     * Methode to add a product to the basket
     * 
     * @param product the product to be added 
     */
    public void addProductToBasket(Product product) {
        LOG.info("[ProductViewBean] add Product to basket");
        int count = basket.incrementBasketItem(product);
        showMassage(new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Produkt in Warenkorb hinzugefügt",
                product.getName() + " ist " + count + " mal im Warenkorb"
        ));
        LOG.info("[ProductViewBean] Product (" + product.getName() + ") is "
                + count + " times in the basket");
    }
    
    /**
     * This Methode adds a growl with a given message
     * 
     * @param message the message which is displayed
     */
    private void showMassage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Returns true if the logged in user is admin or developer
     * 
     * @return true if logged in user is admin or developer
     */
    public boolean isAdmin() {
        return Util.getUserRole() >= 1;
    }

    /**
     * Returns true if the logged in user is developer
     * 
     * @return true if logged in user is admin or developer
     */
    public boolean isDeveloper() {
        return Util.getUserRole() >= 2;
    }

    // GETTER && SETTER

    /**
     * Wrapper Methode to get the Liste of current Products
     * 
     * @return the value of products.getCurrentProducts
     */
    public List<Product> getCurrentProducts() {
        return products.getCurrentProducts();
    }

    /**
     * Wrapper Methode to get the current Product
     * 
     * @return the value of products.getCurrentProduct
     */
    public Product getCurrentProduct() {
        return products.getCurrentProduct();
    }

    /**
     * Wrapper Methode to set the current Product
     * 
     * @param currentProduct the new value of the current Product
     */
    public void setCurrentProduct(Product currentProduct) {
        products.setCurrentProduct(currentProduct);
    }
    
}

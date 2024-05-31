package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Basket;
import de.hsbi.fotostudio.modul.Products;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * The class BannerBean is the Backing-Bean for the banner.xhtml page.
 * This class links the xhtml page and the underlying logic.
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "bannerBean")
@ViewScoped
public class BannerBean implements Serializable {

    private String searchContent;
    
    @Inject
    private Products products;
    
    @Inject
    private Basket basket;
    
    private static final Logger LOG = Logger.getLogger(BannerBean.class.getName());
    
    /**
     * Creates a new instance of BannerBean
     */
    public BannerBean() {
    }

    /**
     * Search in the list of products or services, according to the current page
     */
    public void search() {
        LOG.info("[BannerBean] search");
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        LOG.info("[BannerBean] page" + url);
        
        if (searchContent == null || searchContent.isBlank())
            return;
        
        boolean result = false;
        if (url.contains("ProductView.xhtml")) {
            result = products.findProductWithNamefragment(searchContent);
        } else if (url.contains("ServiceView.xhtml")) {
            result = products.findServiceWithNamefragment(searchContent);
        }
        LOG.info("[BannerBean] search: " + (result ? "Items found" : "No Items found"));
    }
    
    /**
     * Returns the path to the basket view
     * @return the path to the basket view
     */
    public String openBasket() {
        return "Basket?faces-redirect=true";
    }
    
    /**
     * Returns the current amount of items in the basket,
     * only the amount of different items is counted
     * 
     * @return the amount of different items in the basket
     */
    public int getBasketSize() {
        return this.basket.getBasketSize();
    }

    // GETTER && SETTER

    /**
     * Get Value of searchContent
     * 
     * @return the value of searchContent
     */
    public String getSearchContent() {
        return searchContent;
    }

    /**
     * Set Value of searchContent
     * 
     * @param searchContent the new value of searchContent
     */
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
    
}

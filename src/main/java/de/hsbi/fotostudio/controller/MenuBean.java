package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Products;
import de.hsbi.fotostudio.util.ProductData;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
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

    private String searchContent;
    
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
     * @param categoryId the id of the new Category
     * @return the path to the product view
     */
    public String changeProductCategory(int categoryId) {
        products.selectProductCategory(categoryId);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
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
        PrimeFaces.current().ajax().update("form-service-view:data-view");
        return "ServiceView?faces-redirect=true";
    }

    public void search() {
        LOG.info("[MenuBean] search");
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        LOG.info("[MenuBean] page" + url);
        
        if (searchContent == null || searchContent.isBlank())
            return;
        
        boolean result = false;
        if (url.contains("ProductView.xhtml")) {
            result = products.findProductWithNamefragment(searchContent);
        } else if (url.contains("ServiceView.xhtml")) {
            result = products.findServiceWithNamefragment(searchContent);
        }
        LOG.info("[MenuBean] search: " + (result ? "Items found" : "No Items found"));
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

package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Basket;
import de.hsbi.fotostudio.modul.Service;
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
 * The class ServiceViewBean is the Backing-Bean for the ServiceView.xhtml page.
 * This class links the xhtml page and the underlying logic.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "serviceViewBean")
@ViewScoped
public class ServiceViewBean implements Serializable{
    
    @Inject
    private Products products;
    
    @Inject
    private Basket basket;
    
    private Service currentService;
    
    private static final Logger LOG = Logger.getLogger(ServiceViewBean.class.getName());
    
    /**
     * Creates instance of ServiceViewBean
     */
    ServiceViewBean() {
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
     * Methode is called to show the ServiceDialog of a Service.
     * 
     * @param service new Service which is shown in the ServiceDialog.
     */
    public void info(Service service) {
        LOG.info("[ServiceViewBean] info: " + service.getName());
        products.setAddNewItem(false);
        products.setCurrentService(service);
        PrimeFaces.current().ajax().update(":form-service-dialog");
    }
    
    /**
     * Method to create a new Servies
     */
    public void createService() {
        LOG.info("[ServiceViewBean] add Service");
        products.setAddNewItem(true);
        products.setCurrentService(new Service());
        PrimeFaces.current().ajax().update(":form-service-dialog");
        LOG.info("[ServiceViewBean] add Service : "
                + products.getCurrentService().toString());
    }
    
    /**
     * Methode to add a service to the basket
     * 
     * @param service the service to be added 
     */
    public void addServiceToBasket(Service service) {
        LOG.info("[ServiceViewBean] add Service to basket");
        int count = basket.incrementBasketItem(service);
        showMassage(new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Service in Warenkorb hinzugefügt",
                service.getName() + " ist " + count + " mal im Warenkorb"
        ));
        LOG.info("[ServiceViewBean] Service (" + service.getName() + ") is "
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
    public List<Service> getCurrentServices() {
        return products.getCurrentServices();
    }

    /**
     * Wrapper Methode to get the current Product
     * 
     * @return the value of products.getCurrentProduct
     */
    public Service getCurrentService() {
        return this.products.getCurrentService();
    }

    /**
     * Wrapper Methode to set the current Service
     * 
     * @param currentService the new value of the current Service
     */
    public void setCurrentService(Service currentService) {
        this.products.setCurrentService(currentService);
    }
    
}

package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Service;
import de.hsbi.fotostudio.modul.Services;
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
    private Services services;
    
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
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showMessage);
    }

    /**
     * This Methode adds a Message to visualize that the Multiviewstate has been cleared.
     * 
     * @param id the id of the user, where the multistate View has been cleared.
     */
    private void showMessage(String id) {
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
        services.setCurrentService(service);
        PrimeFaces.current().ajax().update(":form-service-dialog");
    }

    // GETTER && SETTER

    /**
     * Wrapper Methode to get the Liste of current Products
     * 
     * @return the value of products.getCurrentProducts
     */
    public List<Service> getCurrentServices() {
        return services.getCurrentServices();
    }

    /**
     * Wrapper Methode to get the current Product
     * 
     * @return the value of products.getCurrentProduct
     */
    public Service getCurrentService() {
        return this.services.getCurrentService();
    }

    /**
     * Wrapper Methode to set the current Service
     * 
     * @param currentService the new value of the current Service
     */
    public void setCurrentService(Service currentService) {
        this.services.setCurrentService(currentService);
    }
    
}

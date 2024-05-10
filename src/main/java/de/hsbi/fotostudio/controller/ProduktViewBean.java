package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Produkt;
import de.hsbi.fotostudio.modul.Produkte;
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
 *
 * @author Janis Wiegräbe
 */
@Named(value = "produktViewBean")
@ViewScoped
public class ProduktViewBean implements Serializable{

    private Produkt aktuelles_produkt;
    
    @Inject
    private Produkte produkte;
    
    private static final Logger LOG = Logger.getLogger(ProduktViewBean.class.getName());
    
    ProduktViewBean() {
    }
    
    public void clearMultiViewState() {
        LOG.info("clearMultiViewState");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showMessage);
    }

    private void showMessage(String id) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, id + "multiview state has been cleared out", null));
    }

    // GETTER && SETTER

    public Produkt getAktuelles_produkt() {
        return aktuelles_produkt;
    }

    public void setAktuelles_produkt(Produkt aktuelles_produkt) {
        this.aktuelles_produkt = aktuelles_produkt;
    }

    public List<Produkt> getProdukt_liste() {
        return produkte.getProdukt_liste();
    }
    
}

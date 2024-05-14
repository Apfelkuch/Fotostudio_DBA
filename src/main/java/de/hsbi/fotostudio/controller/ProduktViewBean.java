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
 * The class ProduktViewBean is the Backing-Bean for the ProduktView.xhtml page.
 * This class links the xhtml page and the underlying logic.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "produktViewBean")
@ViewScoped
public class ProduktViewBean implements Serializable{
    
    @Inject
    private Produkte produkte;
    
    private Produkt aktuellesProdukt;
    
    private static final Logger LOG = Logger.getLogger(ProduktViewBean.class.getName());
    
    /**
     * Creates instance of ProduktViewBean
     */
    ProduktViewBean() {
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
     * This Methode adds a Message to visualize that the multiviewstate has been cleared.
     * 
     * @param id the id of the user, where the multistate View has been cleared.
     */
    private void showMessage(String id) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, id + "multiview state has been cleared out", null));
    }
    
    /**
     * Methode is called to show the ProduktDialog of a Produkt.
     * 
     * @param produkt new Produkt which is shown in the ProduktDialog.
     */
    public void info(Produkt produkt) {
        LOG.info("[ProduktViewBean] info: " + produkt.getName());
        produkte.setAktuellesProdukt(produkt);
        PrimeFaces.current().ajax().update(":form-produkt-dialog");
    }

    // GETTER && SETTER

    /**
     * Wrapper Methode to get the Liste of "aktuelleProdukte"
     * 
     * @return the value of produkte.getAktuelleProdukte
     */
    public List<Produkt> getAktuelleProdukte() {
        return produkte.getAktuelleProdukte();
    }

    /**
     * Wrapper Methode to get the "aktuellesProdukt"
     * 
     * @return the value of produkte.getAktuellesProdukt
     */
    public Produkt getAktuellesProdukt() {
        return produkte.getAktuellesProdukt();
    }

    /**
     * Wrapper Methode to set the "aktuellesProdukte"
     * 
     * @param aktuellesProdukt the new value of "aktuellesProdukte"
     */
    public void setAktuellesProdukt(Produkt aktuellesProdukt) {
        produkte.setAktuellesProdukt(aktuellesProdukt);
    }
    
}

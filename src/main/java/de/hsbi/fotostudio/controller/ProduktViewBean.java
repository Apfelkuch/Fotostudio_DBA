package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Produkt;
import de.hsbi.fotostudio.modul.Produkte;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
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
    
    @Inject
    private Produkte produkte;
    
    private Produkt aktuellesProdukt;
    
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
    
    public void info(Produkt produkt) {
        LOG.info("[ProduktViewBean] info: " + produkt.getName());
        produkte.setAktuelles_produkt(produkt);
        PrimeFaces.current().ajax().update(":form-produkt-dialog");
    }
    
    public void infoListener(ActionEvent event) {
        LOG.info("[ProduktViewBean] info Listener");
    }

    // GETTER && SETTER

    public List<Produkt> getProdukt_liste() {
        return produkte.getProdukt_liste();
    }

    public Produkt getAktuellesProdukt() {
        return produkte.getAktuelles_produkt();
    }

    public void setAktuellesProdukt(Produkt aktuellesProdukt) {
        produkte.setAktuelles_produkt(aktuellesProdukt);
    }
    
}

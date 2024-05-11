package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Produkte;
import de.hsbi.fotostudio.util.ProduktDaten;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Janis Wiegräbe
 */
@Named(value = "menuBean")
@ViewScoped
public class MenuBean implements Serializable{

    @Inject
    private Produkte produkte;
    
    @Inject
    private ProduktDaten produktDaten;
    
    private static final Logger LOG = Logger.getLogger(MenuBean.class.getName());
    
    public MenuBean() {
    }
    
    public void kategorieWechselnZuAlles() {
        produkte.selectCategory(produktDaten.getKategorien().get(0).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    public void kategorieWechselnZuInnerhaus() {
        produkte.selectCategory(produktDaten.getKategorien().get(1).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    public void kategorieWechselnZuAusserhaus() {
        produkte.selectCategory(produktDaten.getKategorien().get(2).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    public void kategorieWechselnZuEquipment() {
        produkte.selectCategory(produktDaten.getKategorien().get(3).getId());
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }

}

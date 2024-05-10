package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Kategorien;
import de.hsbi.fotostudio.modul.Produkte;
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
    
    private static final Logger LOG = Logger.getLogger(MenuBean.class.getName());
    
    public MenuBean() {
    }
    
    public void kategorieWechselnZuAlles() {
        produkte.selectCategory(Kategorien.ALLES);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    public void kategorieWechselnZuInnerhaus() {
        produkte.selectCategory(Kategorien.INNERHAUS);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    public void kategorieWechselnZuAusserhaus() {
        produkte.selectCategory(Kategorien.AUSSERHAUS);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    public void kategorieWechselnZuEquipment() {
        produkte.selectCategory(Kategorien.EQUIPMENT);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }

}

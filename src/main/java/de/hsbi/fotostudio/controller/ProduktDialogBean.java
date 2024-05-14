package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Abrechnungsart;
import de.hsbi.fotostudio.modul.Kategorie;
import de.hsbi.fotostudio.modul.Lagerstatus;
import de.hsbi.fotostudio.modul.Produkt;
import de.hsbi.fotostudio.modul.Produkte;
import de.hsbi.fotostudio.util.ProduktDaten;
import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

/**
 * The class ProduktDialogBean is the Backing-Bean for the ProduktDialog.xhtml page.
 * This class links the xhtml page and the underlying logic.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "produktDialogBean")
@ViewScoped
public class ProduktDialogBean implements Serializable {
    
    private boolean editing = true;
    
    private static final Logger LOG = Logger.getLogger(ProduktDialogBean.class.getName());
    
    private String aktuellekategorieName;
    private List<Kategorie> kategorien;
    
    private String aktuelleAbrechnungsartName;
    private Abrechnungsart aktuelleAbrechnungsart;
    private List<Abrechnungsart> abrechnungsarten;
    
    private String aktuellerLagerStatusName;
    private Lagerstatus aktuellerLagerStatus;
    private List<Lagerstatus> lagerstatusse;
    
    private Produkt aktuellesProdukt;
    
    @Inject
    private Produkte produkte;
    
    @Inject
    private ProduktDaten produktDaten;
    
    /**
     * Creates new instance of ProduktDialogBean
     */
    public ProduktDialogBean() {
    }
    
    /**
     * Initalization of the ProduktDialogBean is called directly after the Konstruktor
 Initalizes the variables
     */
    @PostConstruct
    public void init() {
        kategorien = produktDaten.getKategorien();
        abrechnungsarten = produktDaten.getAbrechnungsarten();
        lagerstatusse = produktDaten.getLagerstatusse();
        aktuellesProdukt = new Produkt();
    }

    /**
     * Methode to save a Produkt to the produkte - Class.
     * When the Class is called the Produkt is saved to the produkt-Class.
     * After the saving of the Produkt the ProduktView is updated.
     */
    public void saveEdits() {
        aktuellesProdukt.setId(produkte.getAktuellesProdukt().getId());
        LOG.info("[ProduktDialogBean] save: " + aktuellesProdukt.toString());
        produkte.updateProdukt(aktuellesProdukt.getId(), aktuellesProdukt);
        PrimeFaces.current().ajax().update("form-product-view:data-view");
    }
    
    /**
     * The ValueChangeListener-Methode to change the Kategorie.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */
    public void getKategorie(ValueChangeEvent event) {
        LOG.info("[ProduktDialogBean] getKategorie");
        Kategorie neueKategorie = (Kategorie) event.getNewValue();
        
        for (Kategorie k : kategorien) {
            if (k.equals(aktuellekategorieName)) {
                aktuellesProdukt.setKategorie(k);
                break;
            }
        }
        if (aktuellesProdukt.getKategorie() != null) {
            LOG.info("[ProduktDialogBean] Kategorie gewählt: "
                    + aktuellesProdukt.getKategorie().getName());
//            FacesContext.getCurrentInstance().
//                    addMessage(null,new FacesMessage("Kategorie gültig!"));
        }
    }
    
    /**
     * The ValueChangeListener-Methode to change the Abrechnungsart.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */

    public void getAbrechnungsart(ValueChangeEvent event) {
        aktuelleAbrechnungsartName = (String) event.getNewValue();
        for (Abrechnungsart abrechnungsart : abrechnungsarten) {
            if (abrechnungsart.getName().equals(aktuelleAbrechnungsartName)) {
                aktuelleAbrechnungsart = abrechnungsart;
                break;
            }
        }
        if (aktuelleAbrechnungsart != null) {
            LOG.info("[ProduktDialogBean] Abrechnungsart gewählt");
//            FacesContext.getCurrentInstance().
//                    addMessage(null,new FacesMessage("Abrechnungsart gültig!"));
        }
    }
    
    
    /**
     * The ValueChangeListener-Methode to change the Lagerstatus.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */
    public void getLagerstatus(ValueChangeEvent event) {
        LOG.info("[ProduktDialogBean] Lagerstatus");
        Lagerstatus neuerLagerStatus = (Lagerstatus) event.getNewValue();
        
        for (Lagerstatus l : lagerstatusse) {
            if (l.equals(aktuellerLagerStatusName)) {
                aktuellesProdukt.setLagerstatus(l);
                break;
            }
        }
        if (aktuellerLagerStatus != null) {
            LOG.info("[ProduktDialogBean] Lagerstatus gewählt");
//            FacesContext.getCurrentInstance().
//                    addMessage(null,new FacesMessage("Lagerstatus gültig!"));
        }
    }
    
    
    /**
     * Methode to initalise the Produkt Dialog by updating the
     * "aktuellesProdukt"-Variable.
     * 
     * @return the id of the "aktuellesProdukt"-Variable
     */
    public int getHeader() {
        this.aktuellesProdukt = produkte.getAktuellesProdukt();
        LOG.info("[ProduktDialogBean] load: " + aktuellesProdukt.getId());
        
        return aktuellesProdukt.getId();
    }
        
    // GETTER && SETTER

    /**
     * Get Value of editing
     * 
     * @return the value of editing
     */
    public boolean isEditing() {
        return editing;
    }

    /**
     * Set Value of editing
     * 
     * @param editing the new value of editing
     */
    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    /**
     * Get Value of produkte
     * 
     * @return the value of produkte
     */
    public Produkte getProdukte() {
        return produkte;
    }
    
    /**
     * Set Value of produkte
     * 
     * @param produkte the new value of produkte
     */
    public void setProdukte(Produkte produkte) {
        this.produkte = produkte;
    }

    /**
     * Get Value of kategorien
     * 
     * @return the value of kategorien
     */
    public List<Kategorie> getKategorien() {
        return kategorien;
    }

    /**
     * Set Value of kategorien
     * 
     * @param kategorien the new value of kategorien
     */
    public void setKategorien(List<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }

    /**
     * Get Value of aktuelleAbrechnungsartName
     * 
     * @return the value of aktuelleAbrechnungsartName
     */
    public String getAktuelleAbrechnungsartName() {
        return aktuelleAbrechnungsartName;
    }

    /**
     * Set Value of aktuelleAbrechnungsartName
     * 
     * @param aktuelleAbrechnungsartName the new value of aktuelleAbrechnungsartName
     */
    public void setAktuelleAbrechnungsartName(String aktuelleAbrechnungsartName) {
        this.aktuelleAbrechnungsartName = aktuelleAbrechnungsartName;
    }

    /**
     * Get Value of aktuelleAbrechnungsart
     * 
     * @return the value of aktuelleAbrechnungsart
     */
    public Abrechnungsart getAktuelleAbrechnungsart() {
        return aktuelleAbrechnungsart;
    }

    /**
     * Set Value of aktuelleAbrechnungsart
     * 
     * @param aktuelleAbrechnungsart the new value of aktuelleAbrechnungsart
     */
    public void setAktuelleAbrechnungsart(Abrechnungsart aktuelleAbrechnungsart) {
        this.aktuelleAbrechnungsart = aktuelleAbrechnungsart;
    }

    /**
     * Get Value of abrechnungsarten
     * 
     * @return the value of abrechnungsarten
     */
    public List<Abrechnungsart> getAbrechnungsarten() {
        return abrechnungsarten;
    }

    /**
     * Set Value of abrechnungsarten
     * 
     * @param abrechnungsarten the new value of abrechnungsarten
     */
    public void setAbrechnungsarten(List<Abrechnungsart> abrechnungsarten) {
        this.abrechnungsarten = abrechnungsarten;
    }

    /**
     * Get Value of aktuellerLagerStatus
     * 
     * @return the value of aktuellerLagerStatus
     */
    public Lagerstatus getAktuellerLagerStatus() {
        return aktuellerLagerStatus;
    }

    /**
     * Set Value of aktuellerLagerStatus
     * 
     * @param aktuellerLagerStatus the new value of aktuellerLagerStatus
     */
    public void setAktuellerLagerStatus(Lagerstatus aktuellerLagerStatus) {
        this.aktuellerLagerStatus = aktuellerLagerStatus;
    }

    /**
     * Get Value of lagerstatusse
     * 
     * @return the value of lagerstatusse
     */
    public List<Lagerstatus> getLagerstatusse() {
        return lagerstatusse;
    }

    /**
     * Set Value of lagerstatusse
     * 
     * @param lagerstatusse the new value of lagerstatusse
     */
    public void setLagerstatusse(List<Lagerstatus> lagerstatusse) {
        this.lagerstatusse = lagerstatusse;
    }

    /**
     * Get Value of aktuellekategorieName
     * 
     * @return the value of aktuellekategorieName
     */
    public String getAktuellekategorieName() {
        return aktuellekategorieName;
    }

    /**
     * Set Value of aktuellekategorieName
     * 
     * @param aktuellekategorieName the new value of aktuellekategorieName
     */
    public void setAktuellekategorieName(String aktuellekategorieName) {
        this.aktuellekategorieName = aktuellekategorieName;
    }

    /**
     * Get Value of aktuellerLagerStatusName
     * 
     * @return the value of aktuellerLagerStatusName
     */
    public String getAktuellerLagerStatusName() {
        return aktuellerLagerStatusName;
    }

    /**
     * Set Value of aktuellerLagerStatusName
     * 
     * @param aktuellerLagerStatusName the new value of aktuellerLagerStatusName
     */
    public void setAktuellerLagerStatusName(String aktuellerLagerStatusName) {
        this.aktuellerLagerStatusName = aktuellerLagerStatusName;
    }

    /**
     * Get Value of aktuellesProdukt
     * 
     * @return the value of aktuellesProdukt
     */
    public Produkt getAktuellesProdukt() {
        return aktuellesProdukt;
    }

    /**
     * Set Value of aktuellesProdukt
     * 
     * @param aktuellesProdukt the new value of aktuellesProdukt
     */
    public void setAktuellesProdukt(Produkt aktuellesProdukt) {
        this.aktuellesProdukt = aktuellesProdukt;
    }
    
}

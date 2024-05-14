package de.hsbi.fotostudio.modul;

import de.hsbi.fotostudio.util.ProduktDaten;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The class Produkt is the connecting class between the Backing-Beans and
 * the data of the shop.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "produkte")
@ApplicationScoped
public class Produkte {

    private static final Logger LOG = Logger.getLogger(Produkte.class.getName());
    
    private List<Produkt> aktuelleProdukte;
    
    private Produkt aktuellesProdukt;
    
    @Inject
    private ProduktDaten produktDaten;
    
    /**
     * Creates a new instance of Produkte
     */
    public Produkte() {
    }
    
    /**
     * Initalization of the Produkte is called directly after the Konstruktor
     * Initalizes the variables and sets a default Kategorie
     */
    @PostConstruct
    public void init() {
        aktuelleProdukte = new ArrayList<>();
        selectCategory(0);
        aktuellesProdukt = new Produkt();
    }
    
    /**
     * This Methode updates the list aktuelleProdukte to only contain 
     * Produkte, which have the same kategorie as the given kategorieId
     * 
     * @param kategorieId new id of the selected kategorie
     */
    public void selectCategory(int kategorieId) {
        Kategorie kategorie = produktDaten.getKategorien().get(kategorieId);
        LOG.info("[Produkte] Produkte durch Kategorie angepasst, neue Kategorie ist " + kategorie.getName());
        List<Produkt> immutableCopy = List.copyOf(produktDaten.getProdukte());
        aktuelleProdukte = null;
        switch (kategorieId) {
            case 0:
                aktuelleProdukte = immutableCopy;
                break;
            case 1:
                aktuelleProdukte = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(kategorie))
                        .collect(Collectors.toList());
                break;
            case 2:
                aktuelleProdukte = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(kategorie))
                        .collect(Collectors.toList());
                break;
            case 3:
                aktuelleProdukte = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(kategorie))
                        .collect(Collectors.toList());
                break;
            default:
                LOG.info("[Produkte] Keine Kategory gefunden");
                aktuelleProdukte = new ArrayList<>();
        }
//        if (!aktuelle_produkt_liste.isEmpty())
//            LOG.info("[Produkte] aktuell erstes Produkt nach selektion: " + aktuelle_produkt_liste.get(0).getName());
    }
        
    /**
     * Updates a Produkt in produkte and reselects the kategorie to update
     * the changes in the ProduktView
     * 
     * @param id the id of the Produkt which should be updated
     * @param produkt the Produkt with the new data
     * @return true if the Produkt is found and updated, otherwise false
     */
    public boolean updateProdukt(int id, Produkt produkt) {
        if (id < 0 || id >= produktDaten.getProdukte().size()){
            return false;
        }
        
        // update Produkt daten in Liste (Datenbank)
        boolean returnValue = produktDaten.updateProdukte(id, produkt);
        
        // Kategorie setzen damit die Produktliste neu geladen wird
        selectCategory(aktuellesProdukt.getKategorie().getId());
        
        return returnValue;
    }
    
    /**
     * This Methode finds the Kategorie to a given id
     * 
     * @param id the id of the Kategorie this Methode searchs for 
     * @return the kategorie to the given id
     */
    public Kategorie findKategorieMitId(int id) {
        LOG.info("[Produkte] findKategorieMitId => id: " + id);
        for (Kategorie k : produktDaten.getKategorien()) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
    }
        
    /**
     * This Methode finds the Lagerstatus to a given id
     * 
     * @param id the id of the Lagerstatus this Methode searchs for 
     * @return the Lagerstatus to the given id
     */
    public Lagerstatus findLagerstatusMitId(int id) {
        LOG.info("[Produkte] findLagerstatusMitId => id: " + id);
        for (Lagerstatus l : produktDaten.getLagerstatusse()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
    
    // GETTER && SETTER

    /**
     * Get Value of id
     * 
     * @return the value of id
     */
    public List<Produkt> getAktuelleProdukte() {
//        LOG.info("[Produkte] aktuell erstes Produkt: " + aktuelle_produkt_liste.get(0).getName());
        return aktuelleProdukte;
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
        LOG.info("[Produkte] Aktuelles Produkt: " + aktuellesProdukt.getName());
        this.aktuellesProdukt = aktuellesProdukt;
    }
}

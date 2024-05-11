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
 *
 * @author Janis Wiegräbe
 */
@Named(value = "produkte")
@ApplicationScoped
public class Produkte {

    private static final Logger LOG = Logger.getLogger(Produkte.class.getName());
    
    private List<Produkt> aktuelle_produkt_liste;
    
    private Produkt aktuelles_produkt;
    
    @Inject
    private ProduktDaten produktDaten;
    
    /**
     * Creates a new instance of Produkte
     */
    public Produkte() {
    }
    
    @PostConstruct
    public void init() {
        aktuelle_produkt_liste = new ArrayList<>();
        selectCategory(0);
        aktuelles_produkt = new Produkt();
    }
    
    public void selectCategory(int kategorieId) {
        Kategorie kategorie = produktDaten.getKategorien().get(kategorieId);
        LOG.info("[Produkte] Produkte durch Kategorie angepasst, neue Kategorie ist " + kategorie.getName());
        List<Produkt> immutableCopy = List.copyOf(produktDaten.getProdukte());
        aktuelle_produkt_liste = null;
        switch (kategorieId) {
            case 0:
                aktuelle_produkt_liste = immutableCopy;
                break;
            case 1:
                aktuelle_produkt_liste = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(kategorie))
                        .collect(Collectors.toList());
                break;
            case 2:
                aktuelle_produkt_liste = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(kategorie))
                        .collect(Collectors.toList());
                break;
            case 3:
                aktuelle_produkt_liste = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(kategorie))
                        .collect(Collectors.toList());
                break;
            default:
                LOG.info("[Produkte] Keine Kategory gefunden");
                aktuelle_produkt_liste = new ArrayList<>();
        }
//        if (!aktuelle_produkt_liste.isEmpty())
//            LOG.info("[Produkte] aktuell erstes Produkt nach selektion: " + aktuelle_produkt_liste.get(0).getName());
    }
    
    public boolean updateProdukt(int id, Produkt produkt) {
        if (id < 0 || id >= produktDaten.getProdukte().size()){
            return false;
        }
        
        // update Produkt daten in Liste (Datenbank)
        boolean returnValue = produktDaten.updateProdukte(id, produkt);
        
        // Kategorie setzen damit die Produktliste neu geladen wird
        selectCategory(aktuelles_produkt.getKategorie().getId());
        
        return returnValue;
    }
    
    public Kategorie findKategorieMitId(int id) {
        LOG.info("[Produkte] findKategorieMitId => id: " + id);
        for (Kategorie k : produktDaten.getKategorien()) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
    }
    
    public Lagerstatus findLagerStatusMitId(int id) {
        LOG.info("[Produkte] findLagerStatusMitId => id: " + id);
        for (Lagerstatus l : produktDaten.getLagerstatusse()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
    
    // GETTER && SETTER
    
    public List<Produkt> getProdukt_liste() {
//        LOG.info("[Produkte] aktuell erstes Produkt: " + aktuelle_produkt_liste.get(0).getName());
        return aktuelle_produkt_liste;
    }

    public Produkt getAktuelles_produkt() {
        return aktuelles_produkt;
    }

    public void setAktuelles_produkt(Produkt aktuelles_produkt) {
        LOG.info("[Produkte] Aktuelles Produkt: " + aktuelles_produkt.getName());
        this.aktuelles_produkt = aktuelles_produkt;
    }
}

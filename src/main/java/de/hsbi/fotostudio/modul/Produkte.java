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
        selectCategory(Kategorien.EQUIPMENT);
    }
    
    public void selectCategory(Kategorien kategorien) {
        LOG.info("[Produkte] Produkte durch Kategorie angepasst, neue Kategorie ist " + kategorien.getText());
        List<Produkt> immutableCopy = List.copyOf(produktDaten.getProdukte());
        aktuelle_produkt_liste = null;
        switch (kategorien) {
            case ALLES:
                aktuelle_produkt_liste = immutableCopy;
                break;
            case AUSSERHAUS:
                aktuelle_produkt_liste = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(Kategorien.AUSSERHAUS))
                        .collect(Collectors.toList());
                break;
            case INNERHAUS:
                aktuelle_produkt_liste = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(Kategorien.INNERHAUS))
                        .collect(Collectors.toList());
                break;
            case EQUIPMENT:
                aktuelle_produkt_liste = immutableCopy.stream()
                        .filter(produkt -> produkt.inKategorie(Kategorien.EQUIPMENT))
                        .collect(Collectors.toList());
                break;
            default:
                LOG.info("[Produkte] Keine Kategory gefunden");
                aktuelle_produkt_liste = new ArrayList<>();
        }
//        if (!aktuelle_produkt_liste.isEmpty())
//            LOG.info("[Produkte] aktuell erstes Produkt nach selektion: " + aktuelle_produkt_liste.get(0).getName());
    }
    
    // GETTER && SETTER
    
    public List<Produkt> getProdukt_liste() {
//        LOG.info("[Produkte] aktuell erstes Produkt: " + aktuelle_produkt_liste.get(0).getName());
        return aktuelle_produkt_liste;
    }
}

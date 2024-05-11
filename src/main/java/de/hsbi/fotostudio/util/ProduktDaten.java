package de.hsbi.fotostudio.util;

import de.hsbi.fotostudio.modul.*;
import java.util.List;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Janis Wiegräbe
 */
public class ProduktDaten implements Serializable{
    
    private List<Produkt> produkte;
    private List<Abrechnungsart> abrechnungsarten;
    private List<Kategorie> kategorien;
    private List<Lagerstatus> lagerstatusse;
    
    public ProduktDaten() {
        
    }
    
    @PostConstruct
    public void init() {
        kategorien = new ArrayList<>();
        kategorien.add(new Kategorie(0, "Alles"));
        kategorien.add(new Kategorie(1, "Innerhaus"));
        kategorien.add(new Kategorie(2, "Außerhaus"));
        kategorien.add(new Kategorie(3, "Equipment"));
        
        lagerstatusse = new ArrayList<>();
        lagerstatusse.add(new Lagerstatus(0, "In Stock"));
        lagerstatusse.add(new Lagerstatus(1, "Low Stock"));
        lagerstatusse.add(new Lagerstatus(2, "Out of Stock"));
        
        abrechnungsarten = new ArrayList<>();
        abrechnungsarten.add(new Abrechnungsart(0, "Pro Bild"));
        abrechnungsarten.add(new Abrechnungsart(1, "Pro Person"));
        abrechnungsarten.add(new Abrechnungsart(2, "Pro Objekt"));
        abrechnungsarten.add(new Abrechnungsart(3, "Pro Familie"));
        
        produkte = new ArrayList<>();
        produkte.add(new Produkt(0, "Analogbilder Drucken", "Drucken von einem eigenen Bildes als Analogbild", kategorien.get(1), abrechnungsarten.get(0), 8f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(1, "Kalender Drucken", "Drucken von einem eigenen Kalender", kategorien.get(1), abrechnungsarten.get(2), 12.3f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(2, "Bilder Drucken", "Drucken von einem einem Bild", kategorien.get(1), abrechnungsarten.get(0), 2.5f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(3, "Poster Drucken", "Drucken von einem einem Poster", kategorien.get(1), abrechnungsarten.get(0), 5.5f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(4, "Passbilder", "Erstellen von 4 Passbildern", kategorien.get(1), abrechnungsarten.get(1), 6f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(5, "Familienbilder", "Familienbilder im eigenen Fotostudio erstellen", kategorien.get(1), abrechnungsarten.get(3), 32.5f, -1, lagerstatusse.get(1)));
        produkte.add(new Produkt(6, "Hochzeitsbilder", "Hochzeitbilder im eigenen Fotostudio erstellen", kategorien.get(1), abrechnungsarten.get(3), 50f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(7, "Hochzeitsbilder", "Hochzeitbilder erstellen. Der Ort ist frei wählbar", kategorien.get(2), abrechnungsarten.get(3), 70, -1, lagerstatusse.get(1)));
        produkte.add(new Produkt(8, "Auftragsbilder Ort", "Professionelle Bilder von einem Ort", kategorien.get(2), abrechnungsarten.get(2), 60f, -1, lagerstatusse.get(0)));
        produkte.add(new Produkt(9, "Auftragsbilder Person", "Professionelle Bilder von einer Person", kategorien.get(1), abrechnungsarten.get(1), 60f, -1, lagerstatusse.get(2)));
        produkte.add(new Produkt(10, "Canon EOS 2000D", "Die Canon EOS 2000D Gehäuse ist eine vielseitige und benutzerfreundliche Spiegelreflexkamera, die sich perfekt für den angehenden Fotografen eignet.", kategorien.get(3), abrechnungsarten.get(2), 500.0f, 1, lagerstatusse.get(0)));
        produkte.add(new Produkt(11, "Objektive 18-55mm DC", "Canon EF-S 18–55 mm DC III ƒ/3,5-5,6:\n - Brennweite 29-88 mm\n - Kreisförmige Blende für gute Hintergrundunschärfe", kategorien.get(3), abrechnungsarten.get(2), 150.0f, 3, lagerstatusse.get(0)));
        produkte.add(new Produkt(12, "Objektive 75-300mm III", "Canon EF 75–300 mm ƒ/4-5,6 III:\n - Telezoomobjektiv\n - Minimale Blende: 32–45", kategorien.get(3), abrechnungsarten.get(2), 150.0f, 2, lagerstatusse.get(0)));
        produkte.add(new Produkt(13, "Canon EOS 250D", "Canon EOS 250D:\n - ISO 100-25.600 (erweiterbar auf 51.200)\n - Objektivanschluss: EF / EF-S", kategorien.get(3), abrechnungsarten.get(2), 450.0f, 0, lagerstatusse.get(2)));
        
    }
    
    public boolean updateProdukte(int id, Produkt produkt) {
        if (id >= 0 && id < produkte.size()) {
            return this.produkte.set(id, produkt).equals(produkt);
        }
        return false;
    }
    
    public boolean updateKategorien(int id, Kategorie kategorie) {
        if (id >= 0 && id < kategorien.size()) {
            return this.kategorien.set(id, kategorie).equals(kategorie);
        }
        return false;
    }
    
    public boolean updateLagerstatus(int id, Lagerstatus lagerstatus) {
        if (id >= 0 && id < this.lagerstatusse.size()) {
            return this.lagerstatusse.set(id, lagerstatus).equals(lagerstatus);
        }
        return false;
    }
    
    public boolean updateAbrechnungsart(int id, Abrechnungsart abrechnungsart) {
        if (id >= 0 && id < abrechnungsarten.size()) {
            return this.abrechnungsarten.set(id, abrechnungsart).equals(abrechnungsart);
        }
        return false;
    }
    

    // GETTER && SETTER
    
    public List<Produkt> getProdukte() {
        return produkte;
    }

    public List<Kategorie> getKategorien() {
        return kategorien;
    }

    public List<Abrechnungsart> getAbrechnungsarten() {
        return abrechnungsarten;
    }

    public List<Lagerstatus> getLagerstatusse() {
        return lagerstatusse;
    }
    
}

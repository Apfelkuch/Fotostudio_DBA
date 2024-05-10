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
    
    public ProduktDaten() {
        
    }
    
    @PostConstruct
    public void init() {
        produkte = new ArrayList<>();
        produkte.add(new Produkt(0, "Analogbilder Drucken", "Drucken von einem eigenen Bildes als Analogbild", Kategorien.INNERHAUS, 3, 8f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Kalender Drucken", "Drucken von einem eigenen Kalender", Kategorien.INNERHAUS, 3, 12.3f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Bilder Drucken", "Drucken von einem einem Bild", Kategorien.INNERHAUS, 3, 2.5f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Poster Drucken", "Drucken von einem einem Poster", Kategorien.INNERHAUS, 3, 5.5f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Passbilder", "4 Passbilder erstellen für eine Person", Kategorien.INNERHAUS, 3, 6f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Familienbilder", "Familienbilder im eigenen Fotostudio erstellen", Kategorien.INNERHAUS, 3, 32.5f, -1, LagerStatus.LOWSTOCK));
        produkte.add(new Produkt(0, "Hochzeitsbilder", "Hochzeitbilder im eigenen Fotostudio erstellen", Kategorien.INNERHAUS, 3, 50f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Hochzeitsbilder", "Hochzeitbilder erstellen. Der Ort ist frei wählbar", Kategorien.AUSSERHAUS, 3, 70, -1, LagerStatus.LOWSTOCK));
        produkte.add(new Produkt(0, "Auftragsbilder Ort", "Professionelle Bilder von einem Ort", Kategorien.AUSSERHAUS, 3, 60f, -1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Auftragsbilder Person", "Professionelle Bilder von einer Person", Kategorien.INNERHAUS, 3, 60f, -1, LagerStatus.OUTOFSTOCK));
        produkte.add(new Produkt(0, "Canon EOS 2000D", "Die Canon EOS 2000D Gehäuse ist eine vielseitige und benutzerfreundliche Spiegelreflexkamera, die sich perfekt für den angehenden Fotografen eignet.", Kategorien.EQUIPMENT, 3, 500.0f, 1, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Objektive 18-55mm DC", "Canon EF-S 18–55 mm DC III ƒ/3,5-5,6:\n - Brennweite 29-88 mm\n - Kreisförmige Blende für gute Hintergrundunschärfe", Kategorien.EQUIPMENT, 3, 150.0f, 3, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Objektive 75-300mm III", "Canon EF 75–300 mm ƒ/4-5,6 III:\n - Telezoomobjektiv\n - Minimale Blende: 32–45", Kategorien.EQUIPMENT, 3, 150.0f, 2, LagerStatus.INSTOCK));
        produkte.add(new Produkt(0, "Canon EOS 250D", "Canon EOS 250D:\n - ISO 100-25.600 (erweiterbar auf 51.200)\n - Objektivanschluss: EF / EF-S", Kategorien.EQUIPMENT, 3, 450.0f, 0, LagerStatus.OUTOFSTOCK));
    }
    

    // GETTER && SETTER
    
    public List<Produkt> getProdukte() {
        return produkte;
    }
    
}

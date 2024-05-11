package de.hsbi.fotostudio.modul;

import java.util.logging.Logger;

/**
 *
 * @author Janis Wiegräbe
 */
public class Produkt {
    
    private int id;
    private String name;
    private String beschreibung;
    private Kategorie kategorie;
    private Abrechnungsart abrechnungsart;
    private float preis;
    /**
     * <= 0 : Menge vorhanden
     *  -1  : Menge unbegrenzt
     */
    private int menge;
    private Lagerstatus lagerStatus;
    
    private static final Logger LOG = Logger.getLogger(Produkt.class.getName());
    
    public Produkt() {
        kategorie = null;
        abrechnungsart = new Abrechnungsart();
        lagerStatus = new Lagerstatus();
    }

    public Produkt(int id, String name, String beschreibung, Kategorie kategorie, Abrechnungsart abrechnungsart, float preis, int menge, Lagerstatus lagerStatus) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.abrechnungsart = abrechnungsart;
        this.preis = preis;
        this.menge = menge;
        this.lagerStatus = lagerStatus;
    }

    @Override
    public int hashCode() {
        return 31 + ((id == 0) ? 0 : (String.valueOf(id) + name).hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass()!= obj.getClass())
            return false;
        Produkt other = (Produkt) obj;
        return (this.id == other.id);
    }

    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + beschreibung 
                + ", " + kategorie.getName() + ", " + abrechnungsart.getName() + ", " 
                + menge + ", " + preis + ", " + lagerStatus.getName()+ "]";
    }
    
    public boolean inKategorie(Kategorie kategorie) {
        return this.kategorie.equals(kategorie);
    }
    
    // GETTER && SETTER
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Abrechnungsart getAbrechnungsart() {
        return abrechnungsart;
    }

    public void setAbrechnungsart(Abrechnungsart abrechnungsart) {
        this.abrechnungsart = abrechnungsart;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Lagerstatus getLagerStatus() {
        return lagerStatus;
    }

    public void setLagerStatus(Lagerstatus lagerStatus) {
        this.lagerStatus = lagerStatus;
    }
    
    
}

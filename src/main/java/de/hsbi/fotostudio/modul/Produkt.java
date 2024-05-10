package de.hsbi.fotostudio.modul;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Janis Wiegräbe
 */
public class Produkt implements Serializable{
    
    private int id;
    private String name;
    private String beschreibung;
    private Kategorien kategorie;
    private int lieferzeit;
    private float preis;
    /**
     * <= 0 : Menge vorhanden
     *  -1  : Menge unbegrenzt
     */
    private int menge;
    private LagerStatus lagerStatus;
    
    private static final Logger LOG = Logger.getLogger(Produkt.class.getName());
    
    public Produkt() {
        
    }

    public Produkt(int id, String name, String beschreibung, Kategorien kategorie, int lieferzeit, float preis, int menge, LagerStatus lagerStatus) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.lieferzeit = lieferzeit;
        this.preis = preis;
        this.menge = menge;
        this.lagerStatus = lagerStatus;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Produkt(getId(),getName(),getBeschreibung(),getKategorie(),getLieferzeit(),getPreis(),getMenge(),getLagerStatus());
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
    
    public boolean inKategorie(Kategorien kategorien) {
        return this.kategorie.equals(kategorien);
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

    public Kategorien getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorien kategorie) {
        this.kategorie = kategorie;
    }

    public int getLieferzeit() {
        return lieferzeit;
    }

    public void setLieferzeit(int lieferzeit) {
        this.lieferzeit = lieferzeit;
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

    public LagerStatus getLagerStatus() {
        return lagerStatus;
    }

    public void setLagerStatus(LagerStatus lagerStatus) {
        this.lagerStatus = lagerStatus;
    }
    
    
}
